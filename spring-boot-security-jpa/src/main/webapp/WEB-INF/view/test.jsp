<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>테스트 ${data }
<form action="/signout" method="post">
	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
	<button type="submit">logout</button>
</form>
</body>
</html>