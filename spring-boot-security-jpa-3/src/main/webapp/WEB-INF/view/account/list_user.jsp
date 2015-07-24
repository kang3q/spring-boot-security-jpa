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
	table td:FIRST-CHILD {
		width: 150px;
		border: 1px solid;
	}
</style>
<script type="text/javascript">
$(function(){
	$("#delete").on("click", function(){
		if(confirm("지울꺼야?")){
			common.ajax("${pageContext.request.contextPath}/user/account_delete", "POST", $("form").serialize(), function(data){
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
	<a href="${pageContext.request.contextPath}/user/account_create">클라이언트 생성</a>
	<a href="${pageContext.request.contextPath}/signout">로그아웃</a>
	${sRole}
	${sRole_LC}
	<br/>
	<br/>
	클라이언트 목록
	<br/>
	<form>
		<table>
			<thead>
				<tr>
					<td>name</td>
					<td>email</td>
					<td>role</td>
					<td>create time</td>
					<td>access token</td>
					<td>광고보기</td>
					<td><input type="button" id="delete" value="삭제"></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clientList }" var="client">
					<tr>
						<td><a href="${pageContext.request.contextPath}/user/account_create?email=${client.email}">${client.name }</a></td>
						<td>${client.email }</td>
						<td>${client.role }</td>
						<td>${client.createTime }</td>
						<td>${client.accessToken }</td>
						<td><input type="button" id="adList" value="광고리스트"></td>
						<td><input type="checkbox" name="accountId" value="${client.id}"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>