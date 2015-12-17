<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		border: 2px solid black;
	}
	table{
		border-collapse: collapse;
	}
</style>
</head>
<body>
	<h2>更新用户</h2>
	<!-- 此时没有写action会直接提交给当前路径 -->
	<!-- Springmvc直接把数据封装成一个名为user的User对象 -->
	<sf:form method="post" modelAttribute="user">
		<sf:hidden path="id"/>
		<table>
			<tr>
				<td>username:</td>
				<!-- path值对应user对象的属性 -->
				<td><sf:input path="username"/><sf:errors path="username"/></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><sf:input path="password"/><sf:errors path="password"/></td>
			</tr>
			<tr>
				<td>nickname:</td>
				<td><sf:input path="nickname"/></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><sf:input path="email"/><sf:errors path="email"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="更新"/></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>