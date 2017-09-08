<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Available users:
	<br>
	<br>
	<c:forEach items="${users}" var="user">
		<c:out value="${user.login }" />
		<br>
		<c:url var="deleteLink" value="/user/delete">
			<c:param name="userId" value="${user.id }"/>
		</c:url>
		<c:url var="updateLink" value="/user/showFormForUpdate">
			<c:param name="userId" value="${user.id }"/>
		</c:url>
		
		<a href="${ deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
		<a href="${ updateLink}">Update</a>
        <br>
        <br>
	</c:forEach>
</body>
</html>