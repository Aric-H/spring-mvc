package com.hj.springmvc.controller;



import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hj.springmvc.exception.UserException;
import com.hj.springmvc.model.Pager;
import com.hj.springmvc.model.User;
import com.hj.springmvc.service.IUserService;


@Controller
@RequestMapping(value="user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/start",method=RequestMethod.GET)
	public ModelAndView start(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("start");
		return mv;
	}
	@RequestMapping(value="/pager",method=RequestMethod.GET)
	public ModelAndView pager(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pager");
		Pager<User> pager = userService.findByPage();
		mv.addObject("pager", pager);
		mv.addObject("currentPage", pager.getCurrentPage());
		mv.addObject("pageCount", pager.getPageCount());
		return mv;
	}
	//测试json
	@RequestMapping(value="/pager",method=RequestMethod.GET,params="hj")
	@ResponseBody
	public Pager<User> pagerForJson(){
		Pager<User> pager = userService.findByPage();
		return pager;
	}
	//跳转到添加用户编辑页面
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add");
		//开启ModelDriven
		mv.addObject(new User());
		return mv;
	}
	//添加用户
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user,BindingResult br,
					  @RequestParam(value="file",required=false) MultipartFile[] files,
					  HttpServletRequest req) throws IOException{//紧跟Validate之后写验证结果
		if(br.hasErrors()){
			//如果有错误,直接在add页面显示
			return "add";
		}
		//获取绝对路径
		String realPath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realPath);
		for(MultipartFile file:files){
			if(file.isEmpty()) continue;
			File f = new File(realPath+"/"+file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), f);
			System.out.println(file.getName()+","+file.getOriginalFilename()+","+file.getContentType());
		}
		userService.add(user);
		return "redirect:/user/pager";
	}
	//跳转到更新页面,@PathVariable Integer id意思是把{id}中的内容存进整型id中,注意名称要一致
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public ModelAndView update(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView();
		mv.addObject(userService.load(id));
		mv.setViewName("update");
		return mv;
	}
	//更新
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public ModelAndView update(@PathVariable Integer id,@Validated User user,BindingResult br){
		if(br.hasErrors()){
			return new ModelAndView("update");
		}
		userService.update(user);
		return pager();
	}
	//删除
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id){
		userService.delete(id);
		return pager();
	}
	
	//跳转到登录页面
		@RequestMapping(value="/login",method=RequestMethod.GET)
		public ModelAndView login(){
			return new ModelAndView("login");
		}
		
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="username",required=false) String username,
							  @RequestParam(value="password",required=false) String password){
		ModelAndView mv = new ModelAndView();
		if(username==""||password==""){
			throw new UserException("用户名或密码不能为空!");
		}else{
			User user = userService.getUserByNameAndPassword(new Object[]{username,password});
			if(user==null){
				throw new UserException("用户名或密码输入有误!");
			}else{
				mv.addObject("user", user);
			}
		}
		mv.setViewName("index");
		return mv;
	}
}
