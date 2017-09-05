<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>Users Confirmation</title>
</head>

<body>

<c:forEach items="${user}" var="user">
	<c:out value="${user.login }"/>
</c:forEach>

</body>

</html>