<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signin</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="/signin" method="POST">
		<a href="?lang=ko_KR">한글</a> <a href="?lang=en_US">영어</a> <a href="?lang=zh_TW">중궈</a><br/>
		<!--  -->
		${error}<br/>
		<!--  -->
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
		<!--  -->
		<input type="text" name="email"><spring:message code="name" text="name"/><br />
		<!--  -->
		<input type="password" name="password"><spring:message code="password" text="password"/><br />
		<!--  -->
		<input type="checkbox" name="remember-me">remember-me<br />
		<!--  -->
		<button type="submit">signin1</button>
	</form>
</body>
</html>