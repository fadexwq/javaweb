<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>My JSP 'classlist.jsp' starting page</title>


</head>

<body>
<ul>
<a href="index.jsp">返回</a>
<hr/>
	<c:forEach items="${cList}" var="each">
		<li><a href="showClass?cmd=findStudent&clazz_id=${each.id}">${each.clazzName}</a></li>
	</c:forEach>
</ul>
</body>
</html>
