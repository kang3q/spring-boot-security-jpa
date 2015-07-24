<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
 	${data }<br/>
	<spring:message code="1" text="테스트 메인 페이지"/>
	<sec:authorize access="isAnonymous()">
		<a href="${pageContext.request.contextPath}/signin">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="${pageContext.request.contextPath}/signout">로그아웃</a> <br/>
		<a href="${pageContext.request.contextPath}/${sRole_LC}/account_list">account list</a>
	</sec:authorize>
</body>
</html>