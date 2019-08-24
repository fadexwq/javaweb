<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'studentlist.jsp' starting page</title>
</head>

<body>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>性别</td>
			<td>电话</td>
			<td>班级id</td>
		</tr>
		<c:forEach items="${studentList}" var="stu">
		<tr>
			<td>${stu.id}</td>
			<td>${stu.studentName}</td>
			<td>${stu.studentAge}</td>
			<td>${stu.studentGender}</td>
			<td>${stu.studentPhone}</td>
			<td>${stu.clazz_id}</td>
		</tr>
		</c:forEach>
	</table>
	<a href="showClass?cmd=findClazz">返回</a>
</body>
</html>
