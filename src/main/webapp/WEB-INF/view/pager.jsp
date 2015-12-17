<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse; 
	}
	td,th{
		border: 2px black solid;
	}
</style>
</head>
<body>
	<h2>分页显示用户:</h2>
	<table>
		<tr>
			<th>用户名</th>
			<th>密码</th>
			<th>昵称</th>
			<th>邮箱</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pager.datas }" var="user" begin="0" varStatus="status">
			<tr>
				<td>${user.username }</td>
				<td>${user.password }</td>
				<td>${user.nickname }</td>
				<td>${user.email }</td>
				<td><a href="/user/${user.id }/delete?currentPage=${currentPage}">删除</a>|<a href="/user/${user.id }/update?currentPage=${currentPage}">更新</a></td>
			</tr>
		</c:forEach>
		<tr>
			<c:choose>
				<c:when test="${pageCount==1 }">
					<td colspan="5" align="center">
						<a style="text-decoration: line-through;">首页</a>
						<a style="text-decoration: line-through;">上一页</a>
						共${pageCount }页,当前是第${currentPage }页
						<a style="text-decoration: line-through;">下一页</a>
						<a style="text-decoration: line-through;">尾页</a>
					</td>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${currentPage==pageCount }">
							<td colspan="5" align="center">
								<a href="/user/pager?currentPage=1">首页</a>
								<a href="/user/pager?currentPage=${currentPage-1 }">上一页</a>
								共${pageCount }页,当前是第${currentPage }页
								<a style="text-decoration: line-through;">下一页</a>
								<a style="text-decoration: line-through;">尾页</a>
							</td>
						</c:when>
						<c:when test="${currentPage==1 }">
							<td colspan="5" align="center">
								<a style="text-decoration: line-through;">首页</a>
								<a style="text-decoration: line-through;">上一页</a>
								共${pageCount }页,当前是第${currentPage }页
								<a href="/user/pager?currentPage=${currentPage+1 }">下一页</a>
								<a href="/user/pager?currentPage=${pageCount }">尾页</a>
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="5" align="center">
								<a href="/user/pager?currentPage=1">首页</a>
								<a href="/user/pager?currentPage=${currentPage-1 }">上一页</a>
								共${pageCount }页,当前是第${currentPage }页
								<a href="/user/pager?currentPage=${currentPage+1 }">下一页</a>
								<a href="/user/pager?currentPage=${pageCount }">尾页</a>
							</td>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td colspan="5" align="center"><a href="/user/add">添加用户</a></td>
		</tr>
	</table>
</body>
</html>