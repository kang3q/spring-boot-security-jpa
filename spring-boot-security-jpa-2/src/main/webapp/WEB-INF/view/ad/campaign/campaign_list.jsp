<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고 수정</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<ul>
		<c:forEach items="${campaignSetList }" var="campaignGroupList">
			<li data-id="${campaignGroupList.id}">
				<span  onclick="showChildList($(this).parent());">
					 ${campaignGroupList.name }
					 <b><c:if test="${campaignGroupList.campaignGroupStatus eq 0 }">ACTIVED</c:if>
						<c:if test="${campaignGroupList.campaignGroupStatus eq 1 }">PAUSED</c:if></b>
				</span>
				<ul data-show="false" style="display:none;">	
					<c:forEach items="${campaignGroupList.campaigns }" var="campaignList">
						<c:if test="${campaignList.campaignStatus ne 2 }">
							<li data-id="${campaignList.id}">
								<span onclick="showChildList($(this).parent());">
									${ campaignList.name}
									<b><c:if test="${campaignList.campaignStatus eq 0 }">ACTIVED</c:if>
									<c:if test="${campaignList.campaignStatus eq 1 }">PAUSED</c:if></b>
								</span>
								<ul data-show="false" style="display:none;">	
									<c:forEach items="${campaignList.campaignAds }" var="adList">
										<c:if test="${adList.adStatus ne 2 }">
											<li data-id="${adList.id}">
												<span onclick="showChildList($(this).parent());">
													${ adList.name}
													<b><c:if test="${adList.adStatus eq 0 }">ACTIVED</c:if>
													<c:if test="${adList.adStatus eq 1 }">PAUSED</c:if></b>
												</span>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</ul>	
			</li>
		</c:forEach>
	</ul>
	
	
	<script type="text/javascript">
		function showChildList(ele){
			var childrenUl = $(ele).find('> ul');
			if($(childrenUl).attr('data-show') == 'false'){
				$(childrenUl).attr('data-show', 'true');
				$(childrenUl).show();
			}else{
				$(childrenUl).attr('data-show', 'false');
				$(childrenUl).hide();
			}
		}
	</script>
</body>
</html>