<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고 수정</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/campaign_update" method="post">
		<c:if test="${not empty campaignGroup}">
			<input name="campaignGroupStatus" id="campaign_group_status" type="hidden" value="${campaignGroup.id}" />
		</c:if>
		campaign group<br/>
		<span>
			<c:if test="${campaignGroup.objective eq 1 }">Website Click</c:if>
			<c:if test="${campaignGroup.objective eq 2 }">Mobile App Install</c:if>
			<c:if test="${campaignGroup.objective eq 3 }">Mobile App Engagement</c:if>
		</span>
		<br/>
		<select id="campaign_group_status" name="campaignGroupStatus">
			<option value="0" <c:if test="${campaignGroup.campaignGroupStatus eq 0}">selected="selected"</c:if>>ACTIVED</option>
			<option value="1" <c:if test="${campaignGroup.campaignGroupStatus eq 1}">selected="selected"</c:if>>PAUSED</option>
			<option value="2" <c:if test="${campaignGroup.campaignGroupStatus eq 2}">selected="selected"</c:if>>DELETED</option>
		</select>		
		<br/>
		<input type="text" id="spend_cap" name="spendCap" value="${campaignGroup.spendCap}"/>
		<br/>
		Campaign Group 이름 <input type="text" name="campaignGoupName" value="${campaignGroup.name }"/>
		<br/>
		<table border="1">
			<tr>
				<c:forEach items="${campaignList }" var="list">
					<td data-campaign="${list.id }" data-campaign-group="${list.campaignGroupId }">
						<ul>
							<li>name <input type="text" name="campaignName" value="${list.name }" /></li>
							<li><select name="campaignBidType">
								<option value="CPC" <c:if test="${list.bidType eq CPC }">selected="selected"</c:if>>CPC</option>
								<option value="CPA" <c:if test="${list.bidType eq CPA }">selected="selected"</c:if>>CPA</option>
								</select>
							</li>
						</ul>
					</td>
				</c:forEach>
			</tr>
		</table>
		<button>저장</button>
	</form>
</body>
</html>