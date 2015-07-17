<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/util/common.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name=role]").on("change", function(){
			var $this = $(this);
			var v = $this.val();
			
			if(v == "CLIENT"){
				getUserList();
			}else{
				$("#user_list").hide();
			}
		});
		
	});// end func
	
	var getUserList = function(cb){
		$.ajax({
			type : "POST",
			url : "/admin/getUserList",
			dataType : "json",
			data : $("form").serialize(),
			beforeSend : function() {
			},
			success : function(data) {
				console.log(data);
				
				$("#user_list").empty().show();
				for(var i in data){
					$("#user_list").append(common.parse('<label><input type="radio" name="agency_id" value="%s">%s</label><br/>', data[i].id, data[i].name));
				}
				if(typeof cb == "function"){
					cb();
				}
			},
			complete : function() {
			},
			error : function(request, status, error) {
			}
		});
	}
	
</script>

<c:if test="${not empty account}">
	<script type="text/javascript">
	$(function(){
		$("input[name=role][value=${account.role}]").prop("checked", true);
		if("${account.role}" == "CLIENT"){
			getUserList(function(){
				$("input[name=agency_id][value=${account.agencyId}]").prop("checked", true);
			});
		}
	});
	</script>
</c:if>

</head>
<body>
	<form action="${pageContext.request.contextPath}/admin/account_create" method="post">
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
		<label><input name="role" type="radio" value="ADMIN" />ADMIN </label>
		<label><input name="role" type="radio" value="USER" />USER </label>
		<label><input name="role" type="radio" value="CLIENT" />CLIENT </label>
		<label><input name="role" type="radio" value="REVIEWER" />REVIEWER </label>
		<%-- --%>
		<div id="user_list" style="display:none;"></div>
		<%-- --%>
		<br/>
		
		<%-- --%>
		<input name="phone" id="phone" placeholder="phone" value="${account.phone}" /> <br />
		<%-- --%>
		<input name="zipcode" id="zipcode" placeholder="우편번호" value="${account.zipcode}" /> <br />
		<%-- --%>
		<input name="address" id="address" placeholder="address" value="${account.address}" /> <br />
		<%-- --%>
		<input name="locale" id="locale" placeholder="locale" value="${account.locale}" /> <br />
		<%-- --%>
		<br/>
		<c:if test="${not empty account}">
			<button>저장</button>
		</c:if>
		<c:if test="${empty account}">
			<button>생성</button>
		</c:if>
	</form>
</body>
</html>