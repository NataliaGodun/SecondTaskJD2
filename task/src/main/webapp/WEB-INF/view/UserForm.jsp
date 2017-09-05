<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<style>
.error {
	color: red
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="processUserForm" modelAttribute="user">
	
		Login: <form:input path="login" />
		<form:errors path="login" cssClass="error" />
		<br>
		<br>
		
		Password : <form:input path="password" />
		<form:errors path="password" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Submit" />

	</form:form>
		<br>
		<br>
	 Or you want registration?
		<br>
		<br>
	<form:form action="showRegistrationForm" modelAttribute="user">

		<input type="submit" value="Registration" />

	</form:form>
	

	
	

</body>

</html>