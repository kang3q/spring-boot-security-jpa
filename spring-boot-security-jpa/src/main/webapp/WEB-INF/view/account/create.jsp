<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/v1.0/member" method="post">
		<c:if test="${not empty member}">
			<input name="_method" type="hidden" value="put" />
			<input name="id" id="id" type="hidden" value="${member.id}" />
		</c:if>
		<c:if test="${empty member}">
			<input name="_method" type="hidden" value="post" />
		</c:if>
		<%-- --%>
		<input name="memberId" id="memberId" placeholder="로그인 아이디" maxlength="24" required="required" value="${member.memberId}" /> <br />
		<%-- --%>
		<input name="name" placeholder="이름" maxlength="24" required="required" value="${member.name}" /> <br />
		<%-- --%>
		<input name="cb" type="checkbox" value="아아아" />아아아 <input name="cb" type="checkbox" value="어어어" />어어어 <input name="cb" type="checkbox" value="껄껄껄" />껄껄껄 <input name="cb" type="checkbox" value="낄낄낄" />낄낄낄
		<%-- --%>
		<select name="role">
			<option selected="selected">USER</option>
			<option>ADMIN</option>
		</select>
		<button>저장</button>
	</form>
</body>
</html>