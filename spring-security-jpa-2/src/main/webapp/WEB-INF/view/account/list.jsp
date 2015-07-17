<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<a href="${pageContext.request.contextPath}/admin/account_create">유저생성</a>
	<a href="${pageContext.request.contextPath}/signout">로그아웃</a>
	<form>
		<table>
			<thead>
				<tr>
					<td>name</td>
					<td>email</td>
					<td>role</td>
					<td>create time</td>
					<td>access token</td>
					<td><input type="button" id="delete" value="삭제"></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountList }" var="account">
					<tr>
						<td><a href="${pageContext.request.contextPath}/admin/account_create?email=${account.email}">${account.name }</a></td>
						<td>${account.email }</td>
						<td>${account.role }</td>
						<td>${account.createTime }</td>
						<td>${account.accessToken }</td>
						<td><input type="checkbox" name="accountId" value="${account.id}"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>