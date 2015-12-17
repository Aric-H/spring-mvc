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
	//����json
	@RequestMapping(value="/pager",method=RequestMethod.GET,params="hj")
	@ResponseBody
	public Pager<User> pagerForJson(){
		Pager<User> pager = userService.findByPage();
		return pager;
	}
	//��ת������û��༭ҳ��
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add");
		//����ModelDriven
		mv.addObject(new User());
		return mv;
	}
	//����û�
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user,BindingResult br,
					  @RequestParam(value="file",required=false) MultipartFile[] files,
					  HttpServletRequest req) throws IOException{//����Validate֮��д��֤���
		if(br.hasErrors()){
			//����д���,ֱ����addҳ����ʾ
			return "add";
		}
		//��ȡ����·��
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
	//��ת������ҳ��,@PathVariable Integer id��˼�ǰ�{id}�е����ݴ������id��,ע������Ҫһ��
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public ModelAndView update(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView();
		mv.addObject(userService.load(id));
		mv.setViewName("update");
		return mv;
	}
	//����
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public ModelAndView update(@PathVariable Integer id,@Validated User user,BindingResult br){
		if(br.hasErrors()){
			return new ModelAndView("update");
		}
		userService.update(user);
		return pager();
	}
	//ɾ��
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id){
		userService.delete(id);
		return pager();
	}
	
	//��ת����¼ҳ��
		@RequestMapping(value="/login",method=RequestMethod.GET)
		public ModelAndView login(){
			return new ModelAndView("login");
		}
		
	//��¼
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="username",required=false) String username,
							  @RequestParam(value="password",required=false) String password){
		ModelAndView mv = new ModelAndView();
		if(username==""||password==""){
			throw new UserException("�û��������벻��Ϊ��!");
		}else{
			User user = userService.getUserByNameAndPassword(new Object[]{username,password});
			if(user==null){
				throw new UserException("�û�����������������!");
			}else{
				mv.addObject("user", user);
			}
		}
		mv.setViewName("index");
		return mv;
	}
}
