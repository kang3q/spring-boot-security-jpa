<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signin</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="/signin" method="POST">
		${error}<br/>
		<!--  -->
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
		<!--  -->
		<input type="text" name="email">name<br />
		<!--  -->
		<input type="password" name="password">password<br />
		<!--  -->
		<input type="checkbox" name="remember-me">remember-me<br />
		<!--  -->
		<button type="submit">signin1</button>
	</form>
</body>
</html>