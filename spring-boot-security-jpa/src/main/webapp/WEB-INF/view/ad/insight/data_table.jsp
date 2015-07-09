<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고 인사이트</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<table border="1">
		<thead>
			<tr role="row">
				<th>Id</th>
				<th>Name</th>
				<th>Date Start</th>
				<th>Date Stop</th>
				<th>Impressions</th>
				<th>Clicks</th>
				<th>Spend</th>
				<th>CPC</th>
				<th>CTR</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adInsights}" var="adInsight">
				<tr>
					<td>${adInsight.campaignGroupId}</td>
					<td>${adInsight.campaignGroupName}</td>
					<td>${adInsight.dateStart}</td>
					<td>${adInsight.dateStop}</td>
					<td>${adInsight.impressions}</td>
					<td>${adInsight.clicks}</td>
					<td>${adInsight.spend}</td>
					<td>${adInsight.cpc}</td>
					<td>${adInsight.ctr}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>