<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
	Available users:

	<br>
	<div id="wrapper">
		<div id="header">
			<h2>Available users:</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<table>
				<tr>
					<th>Login</th>
					<th>Action</th>
				</tr>


				<c:forEach items="${users}" var="user">
					
					
					<c:url var="deleteLink" value="/user/delete">
						<c:param name="userId" value="${user.id }" />
					</c:url>
					<c:url var="updateLink" value="/user/showFormForUpdate">
						<c:param name="userId" value="${user.id }" />
					</c:url>

					<tr>
						<td>${user.login}</td>

						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>
				</c:forEach>
			</table>
			

		</div>

	</div>

</body>
</html>