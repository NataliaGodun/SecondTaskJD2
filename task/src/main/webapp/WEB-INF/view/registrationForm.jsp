<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.error {
	color: red
}
</style>
</head>
<body>
	<h1>Please, for successful registration fill the following fields:
	</h1>
	<br>
	<br>
	<form:form action="registration" modelAttribute="user">
	
		Login: <form:input path="login" />
		<form:errors path="login" cssClass="error" />
		<br>
		<br>
		
		Password : <form:input  type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<br>
		<br>
		<br>

		<input type="submit" value="Submit" />

	</form:form>
</body>
</html>