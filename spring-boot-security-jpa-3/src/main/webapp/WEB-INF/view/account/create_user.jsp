<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/util/common.js"></script>
</head>
<body>
	클라이언트 생성
	<form action="${pageContext.request.contextPath}/user/account_create" method="post">
		<c:if test="${not empty account}">
			<!-- <input name="_method" type="hidden" value="put" /> -->
			<input name="id" id="id" type="hidden" value="${account.id}" />
		</c:if>
		<%-- <c:if test="${empty account}">
			<input name="_method" type="hidden" value="post" />
		</c:if> --%>
		<%-- --%>
		<input name="email" id="email" placeholder="로그인 아이디" maxlength="24" required="required" value="${account.email}" /> <br />
		<%-- --%>
		<input name="password" id="password" placeholder="비밀번호" required="required" /> <br />
		<%-- --%>
		<input name="name" placeholder="이름" maxlength="24" required="required" value="${account.name}" /> <br />
		<%-- --%>
		<input type="hidden" name="role" value="CLIENT" />
		<input type="hidden" name="agency_id" value="${sAccountId}">
		<%-- --%>
		<br />
		<%-- --%>
		<input name="phone" id="phone" placeholder="phone" value="${account.phone}" /> <br />
		<%-- --%>
		<input name="zipcode" id="zipcode" placeholder="우편번호" value="${account.zipcode}" /> <br />
		<%-- --%>
		<input name="address" id="address" placeholder="address" value="${account.address}" /> <br />
		<%-- --%>
		<input name="locale" id="locale" placeholder="locale" value="${account.locale}" /> <br />
		<%-- --%>
		<br />
		<c:if test="${not empty account}">
			<button>저장</button>
		</c:if>
		<c:if test="${empty account}">
			<button>생성</button>
		</c:if>
	</form>
</body>
</html>