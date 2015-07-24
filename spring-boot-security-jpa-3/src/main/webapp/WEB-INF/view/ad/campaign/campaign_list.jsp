<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고 수정</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form id="search_data_form" action="${pageContext.request.contextPath}/campaign_list" method="get">
		<c:if test="${userAccountId ne null }">
			<input type="hidden" id="user_account_id" name="userAccountId" value="${userAccountId }" />
		</c:if>
		<select id="search_campaign_group_status" name="searchCampaignGroupStatus" onchange="searchCampaignGroupStatusFunc();">
			<option value="-1" <c:if test="${campaignSetStatus eq '-1' }">selected="selected"</c:if>>ALL</option>
			<option value="0" <c:if test="${campaignSetStatus eq '0' }">selected="selected"</c:if>>ACTIVED</option>
			<option value="1" <c:if test="${campaignSetStatus eq '1' }">selected="selected"</c:if>>PAUSED</option>
			<option value="2" <c:if test="${campaignSetStatus eq '2' }">selected="selected"</c:if>>ARCHIVED</option>	
		</select>
	</form>
	<ul>
		<c:forEach items="${campaignSetList }" var="campaignGroupList">
			<li data-id="${campaignGroupList.id}">
				<button type="button" onclick="showChildList($(this).parent());">└</button>
				<input type="hidden" name="campaignGroupId" value="${campaignGroupList.id }" />
				<input type="hidden" name="adAccountId" value="${campaignGroupList.adAccountId }" />
				 <div ondblclick="modifyElementData(this);" data-id="${campaignGroupList.id }" >
					 <input type="text" id="campaign_group_name_${campaignGroupList.id}" class="modifyEle_${campaignGroupList.id}" name="campaignGoupName" value="${campaignGroupList.name }" readonly="readonly" />
					 / ${campaignGroupList.objectiveText } / 
					 <input id="campaign_spend_cap_${campaignGroupList.id}" class="modifyEle_${campaignGroupList.id}" type="text" name="campaignGroupSpendCap" value="${ campaignGroupList.spendCap}" readonly="readonly" />
					 <select id="campaign_group_status_${campaignGroupList.id}" class="modifyEle_${campaignGroupList.id}" name="campaignGroupStatus" disabled="disabled">
					 	<option value="0" <c:if test="${campaignGroupList.campaignGroupStatus eq 0 }">selected="selected"</c:if>>ACTIVE</option>
					 	<option value="1" <c:if test="${campaignGroupList.campaignGroupStatus eq 1 }">selected="selected"</c:if>>PAUSED</option>
					 	<option value="2" <c:if test="${campaignGroupList.campaignGroupStatus eq 2 }">selected="selected"</c:if>>ARCHIVED</option>
					 </select>
					 <button type="button" id="modify_${campaignGroupList.id }" value="${campaignGroupList.id}" data-type="CG" style="display:none;" onclick="modifyCampaignData(this);">수정</button>
				 </div>
				<ul data-show="false" style="display:none;">	
					<c:forEach items="${campaignGroupList.campaigns }" var="campaignList">
						<c:if test="${campaignList.campaignStatus ne 2 }">
							<li data-id="${campaignList.id}">
								<span onclick="showChildList($(this).parent());">
									${ campaignList.name}
								</span>
								<b>${campaignList.campaignStatusText}</b>
								<ul data-show="false" style="display:none;">	
									<c:forEach items="${campaignList.campaignAds }" var="adList">
										<c:if test="${adList.adStatus ne 2 }">
											<li data-id="${adList.id}">
												<span onclick="showChildList($(this).parent());">
													${ adList.name}
													<b>${adList.adStatusText }</b>
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
		function searchCampaignGroupStatusFunc(){
			//console.log('123123');
			$('#search_data_form').submit();
		}
		
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
		function modifyElementData(ele){
			$('.modifyEle_'+$(ele).attr('data-id')).removeAttr('disabled');
			$('.modifyEle_'+$(ele).attr('data-id')).removeAttr('readonly');
			$('#modify_'+$(ele).attr('data-id')).show();
		}
		function modifyCampaignData(ele){
			var groupId = $(ele).val();
			$.ajax({
				type : "POST",
				url : "/campaign_update",
				dataType : "json",
				data : {
					group_name : $('#campaign_group_name_'+groupId).val(),
					group_status : $('#campaign_group_status_'+groupId).val(),
					spend_cap : $('#campaign_spend_cap_'+groupId).val(),
					id : groupId
				},
				success : function(data) {
					console.log(data);
				}
			});
		}
	</script>
</body>
</html>