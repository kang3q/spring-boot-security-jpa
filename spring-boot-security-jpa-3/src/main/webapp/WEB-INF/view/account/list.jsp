<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/util/common.js"></script>
<style type="text/css">
	table td {
		width: 100px;
		border: 1px solid;
	}
	table td:FIRST-CHILD {
		width: 150px;
		border: 1px solid;
	}
</style>
<script type="text/javascript">
$(function(){
	$("#delete").on("click", function(){
		if(confirm("지울꺼야?")){
			common.ajax("${pageContext.request.contextPath}/admin/account_delete", "POST", $("form").serialize(), function(data){
				console.log(data);
				$.each(data.accountIdList, function(idx, accountId){
					$("input[name=accountId][value="+accountId+"]").closest("tr").remove();
				});
			});
		}
	});
});
</script>
</head>
<body>
	<a href="?lang=ko_KR">한글</a> <a href="?lang=en_US">영어</a> <a href="?lang=zh_TW">중궈</a><br/>
	<a href="${pageContext.request.contextPath}/admin/account_create">유저생성</a>
	<a href="${pageContext.request.contextPath}/signout">로그아웃</a>
	${sRole}
	${pageContext.response.locale}
	<spring:message code="locale" text="aaaaaa"/>
	<form>
		<table>
			<thead>
				<tr>
					<td><spring:message code="name" text="name"/></td>
					<td><spring:message code="email" text="email"/></td>
					<td><spring:message code="role" text="role"/></td>
					<td><spring:message code="create.time" text="create time"/></td>
					<td><spring:message code="access.token" text="access token"/></td>
					<td>광고보기</td>
					<td><input type="button" id="delete" value="삭제"></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountList }" var="account">
					<c:if test="${account.role != 'CLIENT' }">
						<tr>
							<td><a href="${pageContext.request.contextPath}/admin/account_create?email=${account.email}">${account.name }</a></td>
							<td>${account.email }</td>
							<td>${account.role }</td>
							<td>${account.createTime }</td>
							<td>${account.accessToken }</td>
							<td><input type="button" id="adList" value="광고리스트"></td>
							<td><input type="checkbox" name="accountId" value="${account.id}"></td>
						</tr>
					</c:if>
					<%-- client를 user 밑에 그리기 --%>
					<c:if test="${account.role == 'USER' }">
						<c:forEach items="${accountList }" var="account2">
							<c:if test="${account2.role == 'CLIENT' and account.id == account2.agencyId }">
								<tr>
									<td> ⇒ <a href="${pageContext.request.contextPath}/admin/account_create?email=${account2.email}">${account2.name }</a></td>
									<td>${account2.email }</td>
									<td>${account2.role }</td>
									<td>${account2.createTime }</td>
									<td>${account2.accessToken }</td>
									<td><input type="button" id="adList" value="광고리스트"></td>
									<td><input type="checkbox" name="accountId" value="${account2.id}"></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>