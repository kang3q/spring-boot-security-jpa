(function($){
    $.fn.extend({
        centerLayer: function () {
        	this.css({
                "z-index" : "99",
        		"position": "absolute",
                "top": ((($(parent).height() - $(this).outerHeight()) / 2) + $(parent).scrollTop() + "px"),
                "left": ((($(parent).width() - $(this).outerWidth()) / 2) + $(parent).scrollLeft() + "px")
            });
        	return this;
        }
    });
})(jQuery);

var init_ajax_cnt = 0;

var common = (function() {
	return {
		getPageSize : function() {
			var xScroll, yScroll;
			if (window.innerHeight && window.scrollMaxY) {
				xScroll = window.innerWidth + window.scrollMaxX;
				yScroll = window.innerHeight + window.scrollMaxY;
			} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
				xScroll = document.body.scrollWidth;
				yScroll = document.body.scrollHeight;
			} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
				xScroll = document.body.offsetWidth;
				yScroll = document.body.offsetHeight;
			}
			var windowWidth, windowHeight;
			if (self.innerHeight) {	// all except Explorer
				if(document.documentElement.clientWidth){
					windowWidth = document.documentElement.clientWidth;
				} else {
					windowWidth = self.innerWidth;
				}
				windowHeight = self.innerHeight;
			} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
				windowWidth = document.documentElement.clientWidth;
				windowHeight = document.documentElement.clientHeight;
			} else if (document.body) { // other Explorers
				windowWidth = document.body.clientWidth;
				windowHeight = document.body.clientHeight;
			}
			var pageWidth, pageHeight; 
			// for small pages with total height less then height of the viewport
			if(yScroll < windowHeight){
				pageHeight = windowHeight;
			} else {
				pageHeight = yScroll;
			}
			// for small pages with total width less then width of the viewport
			if(xScroll < windowWidth){
				pageWidth = xScroll;
			} else {
				pageWidth = windowWidth;
			}
			var arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
			return arrayPageSize;
		},
		
		
		createOverLay : function(overLayId) {
			//var arrPageSizes = common.getPageSize();
			//$('body').append('<div id="div_createOverLay" style="position:absolute; top:0px; left:0px; background-color:black; opacity:0.5; width:100%;height:'+arrPageSizes[1]+'px;"></div>');
			if($('div[id^=div_createOverLay]').length < 1) {
				if(overLayId == undefined) {
					$('body').append('<div id="div_createOverLay" style="position:absolute; top:0px; left:0px; background-color:black; opacity:0.5; width:100%; height:100%;"></div>');
				} else {
					$('body').append('<div id="div_createOverLay_' + overLayId + '" style="position:absolute; top:0px; left:0px; background-color:black; opacity:0.5; width:100%; height:100%;"></div>');
				}
			}
		},
		
		
		removeOverLay : function(overLayId) {
			if(overLayId == undefined) {
				$("#div_createOverLay").remove();
			} else {
				$("#div_createOverLay_" + overLayId).remove();
			}
		},
		
		
		createAjaxLoading : function(overLayId) {
			//if(_gubn == undefined){common.createOverLay();}
			//if($('#div_createAjaxLoading').length < 1) {
			//	$('body').append('<div id="div_createAjaxLoading" class="ajaxlodingimg" style="width:168px; height:124px;overflow:hidden;text-indent:-999em;background:url(../images/common/ajax_loading.gif) no-repeat">Loding...</div>');
			//	$('#div_createAjaxLoading').centerLayer();
			//}
			common.createOverLay(overLayId);
			if($('#div_createAjaxLoading').length < 1) {
				$('body').append('<img id="div_createAjaxLoading" src="../images/common/ajax_loading.gif" style="width:12%; height:8.85%; position:absolute; top:45%; left:45%;">');
			}
		},
		
		
		removeAjaxLoading : function(overLayId){
			common.removeOverLay(overLayId);
			$("#div_createAjaxLoading").remove();
		},
		
		
		parse : function(str) {
			var args = [].slice.call(arguments, 1), i = 0;
			return str.replace(/%s/g, function() {
				return args[i++];
			});
		},
		
		
		ajax: function(url, type, data, successCallack, errorCallback) {
			$.ajax({
				url : url,
				type : type,
				data : data,
				dataType : "json",
				beforeSend : function() {
					if(++init_ajax_cnt && init_ajax_cnt == 1) {
						common.createAjaxLoading();
					}
				},
				success : function(data) {
					// 어떠한 리턴값도 없을때
					if(data == undefined) {
						successCallack();
					}
					// success 여부 확인 안할때
					else if (data.success == undefined){
						successCallack(data);
					}
					// 완료
					else if(data.success == true) {
						successCallack(data);
					}
					// 에러
					else if (data.success == false) {
						console.log(data);
						errorCallback(data);
					}
					// 이 부분이 있을까
					else {}
				},
				// 통신에러
				error : function(x, e) {
					console.log(x, e);
					// common.errorReload();
				}
				, complete  : function() {
					if(--init_ajax_cnt <= 0) {
						common.removeAjaxLoading();
					}
				}
			});
		}
		
	}
})();
