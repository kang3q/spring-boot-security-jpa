<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고 생성</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/campaign_create" method="post">
		<div id="create_campaign_step_1" class="create_campaign_step" data-step="1">
			campaign group<br/>
			<select name="objective" onchange="chooseCampaignGroupObjective(this);">
				<option value="1">Website Click</option>
				<option value="2">Website Link Click</option>
				<option value="10">Mobile App Install</option>
				<option value="11">Mobile App Engagement</option>
			</select>
			<br/>
			Spend Cap<input type="text" id="campaign_group_spend_cap" name="campaignGroupSpendCap" /> <br />
			Campaign Group 이름 <input type="text" name="campaignGoupName" />
			<br/>
			<button type="button" onclick="showNextStep($(this).parent());">Next</button>
		</div>
		<div id="create_campaign_step_2" class="create_campaign_step" data-step="2" style="display:none;">
			campaign<br/>
			<div id="campagin_data">
				<div id="campaign_data_1" data-campaign="1" class="campaign_data">
					<input type="hidden" name="campaignId" value="0" />
					Campaign 이름 <input type="text" name="campaignName" />
					<br/>
					<select name="campaignBidType">
						<option value="CPC">CPC</option>
						<option value="CPA">CPA</option>
					</select>
					<br/>
					budget <input type="text" name="campaignBudget" required="required" /><br/>
					<select name="campaignBudgetType">
						<option value="Daily">Daily</option>
						<option value="LifeTime">LifeTime</option>
					</select>
					<br/>
					<select name="campaignTarget">
						<option value="0">All</option>
						<option value="1">Male</option>
						<option value="2">Female</option>
					</select>
					<br/>
					시작 시간 <input type="text" name="campaignStartTime" /><br/>
					종료 시간 <input type="text" name="campaignEndTime" />
					<hr>
				</div>
				<button type="button" id="campaign_add_btn" onclick="campaignInputAdd($(this).prev());" >추가</button>
			</div>
			<br/>
			<button type="button" onclick="showPrevStep($(this).parent());">Prev</button>
			<button type="button" onclick="showNextStep($(this).parent());">Next</button>
		</div>
		<div id="create_campaign_step_3" class="create_campaign_step" data-step="3" style="display:none;">
			Creative
			<div id="creative_data">
				<div id="creative_data_1" data-creative="1" class="creative_data">
					<input type="hidden" id="creative_id_1" name="creativeId" value="0">
					<select name="selectCreative" onchange="selectCreateCreative(this);">
						<option value="0" selected="selected">Select Creative</option>
						<c:forEach items="${adCreative }" var="list">
							<option value="${list.id}">${list.name}</option>
						</c:forEach>
					</select>
					<div id="create_creative_div_1">
						Body <input type="text" name="creativeBody" />
						<br/>
						Image Url
						<input type="file" id="file_1" onchange="changeSelectImage(this);" />
						<input type="hidden" id="hidden_file_1" name="imageUrl" value=" " />
						<br/>
						Link Url
						<input type="text" name="linkUrl" />
						<br/>
						name
						<input type="text" name="creativeName" />
						<br/>
						<div class="link_data_div" style="display:none;">
							Call to Action Type
							<select name="creativeCallToActionType">
								<option value="0" selected="selected">No Button</option>
								<option value="1">Now Install</option>
								<option value="2">Book Travel</option>
								<option value="3">Shop Now</option>
								<option value="4">Play Game</option>
								<option value="5">Listen Music</option>
								<option value="6">Watch Video</option>
								<option value="7">Use App</option>
							</select>
							<br/>
							Link Title
							<input type="text" name="linkTitle" />
							Link Body
							<input type="text" name="linkBody" />
							Link Caption
							<input type="text" name="linkCaption" />
						</div>
						<hr>
					</div>
				</div>
				<button type="button" id="creative_add_btn" onclick="creativeInputAdd($(this).prev());" >추가</button>
			</div>
			<button type="button" onclick="showPrevStep($(this).parent());">Prev</button>
			<button>저장</button>
		</div>
	</form>

	<div id="creative_data_hidden" data-creative="1" class="creative_data" style="display:none;">
		<input type="hidden" id="creative_id_1" name="creativeId" value="0">
		<select name="selectCreative" onchange="selectCreateCreative(this);">
			<option value="0" selected="selected">Select Creative</option>
			<c:forEach items="${adCreative }" var="list">
				<option value="${list.id}">${list.name}</option>
			</c:forEach>
		</select>
		<div id="create_creative_div_1">
			Body <input type="text" name="creativeBody" />
			<br/>
			Image Url
			<input type="file" id="file_1" onchange="changeSelectImage(this);" />
			<input type="hidden" id="hidden_file_1" name="imageUrl" value=" " />
			<br/>
			Link Url
			<input type="text" name="linkUrl" />
			<br/>
			name
			<input type="text" name="creativeName" />
			<div class="link_data_div" style="display:none;">
				Call to Action Type
				<select name="creativeCallToActionType">
					<option value="0">Now Install</option>
					<option value="1">Book Travel</option>
					<option value="2">Shop Now</option>
					<option value="3">Play Game</option>
					<option value="4">Listen Music</option>
					<option value="5">Watch Video</option>
					<option value="6">Use App</option>
				</select>
				<br/>
				Link Title
				<input type="text" name="linkTitle" />
				Link Body
				<input type="text" name="linkBody" />
				Link Caption
				<input type="text" name="linkCaption" />
			</div>
			<hr>
		</div>
	</div>
		
	
	
	<script type="text/javascript">
		function chooseCampaignGroupObjective(ele){
			if($(ele).val() == 1){
				$('.link_data_div').hide();
			}else{
				$('.link_data_div').show();
			}
		}
		function changeSelectImage(ele){
			var index = $(ele).attr('id').split('_');
			$('#hidden_file_'+index[1]).val($(ele).val().replace(/.*(\/|\\)/, ''));
			if($('#hidden_file_'+index[1]).val() == ''){
				$('#hidden_file_'+index[1]).val(' ');
			}
		}
		function showNextStep(ele){
			$('.create_campaign_step').hide();
			var nextStep = parseInt($(ele).attr('data-step'))+1;
			$('#create_campaign_step_'+nextStep).show();
		}
		function showPrevStep(ele){
			$('.create_campaign_step').hide();
			var prevStep = parseInt($(ele).attr('data-step'))-1;
			$('#create_campaign_step_'+prevStep).show();
		}
		function campaignInputAdd(ele){
			var nextCampaign = parseInt($(ele).attr('data-campaign'))+1;
			$('#campaign_data_1').clone().appendTo($('#campagin_data'));
			var newDiv = $('#campagin_data').find('>div:last-child');
			$(newDiv).attr('id', 'campaign_data_'+nextCampaign);
			$(newDiv).attr('data-campaign', nextCampaign);
			$('#campaign_data_'+nextCampaign).append('<button type="button" onclick="campaignInputDelete($(this).parent());">삭제</button>');
			$('#campagin_data').append($('#campaign_add_btn'));
		}
		function campaignInputDelete(ele){
			$(ele).remove();
		}
		function selectCreateCreative(ele){
			var currentIndex = $(ele).parent().attr('data-creative');
			$('#creative_id_'+currentIndex).val($(ele).val());
			if($(ele).val() == 0){
				$('#create_creative_div_'+currentIndex).show();
			}else{
				$('#create_creative_div_'+currentIndex).hide();				
			}
		}
		function creativeInputAdd(ele){
			var nextCreative = parseInt($(ele).attr('data-creative'))+1;
			$('#creative_data_hidden').clone().appendTo($('#creative_data'));
			var newDiv = $('#creative_data').find('>div:last-child');
			$(newDiv).attr('id', 'creative_data_'+nextCreative);
			$(newDiv).attr('data-creative', nextCreative);
			$(newDiv).show();
			$('#creative_data_'+nextCreative).append('<button type="button" onclick="campaignInputDelete($(this).parent());">삭제</button>');
			$('#creative_data').append($('#creative_add_btn'));
			$('#creative_data_'+nextCreative).children('div').attr('id', 'create_creative_div_'+nextCreative);
			$('#creative_data_'+nextCreative).children('input[name="creativeId"]').attr('id', 'creative_id_'+nextCreative);
			$('#creative_data_'+nextCreative).children('div').children('#file_1').attr('id', 'file_'+nextCreative);
			$('#creative_data_'+nextCreative).children('div').children('#hidden_file_1').attr('id', 'hidden_file_'+nextCreative); 
		}
	</script>
</body>
</html>