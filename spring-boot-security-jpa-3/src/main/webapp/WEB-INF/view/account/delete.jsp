<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/v1.0/member" method="post">
		<input name="_method" type="hidden" value="delete" /> 
		<input name="memberId" id="member_id" placeholder="로그인 아이디" maxlength="24" required="required" /> <br />
		<button>삭제</button>
	</form>
</body>
</html>