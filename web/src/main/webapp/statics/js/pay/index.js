$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function back() {
	loading();
	top.location.href = appUrl + "/trade/index.htm";
}

function detail(recordId) {
	loading();
	top.location.href = appUrl + "/record/detail.htm?recordId=" + recordId
			+ "&tradeNo=" + tradeNo;
}

function pay(pt) {
	payType = pt;

	$('#' + payType).button('loading');

	var form = window.document.forms[0];
	if (payType == 'alipay') {
		form.action = appUrl + "/alipay/pay.htm?payType=" + payType;
	} else {
		form.action = appUrl + "/wxpay/pay.htm?payType=" + payType;
	}

	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		error(failResult);
	} else if (successResult != undefined) {
		success("正在处理数据, 请稍候...");

		if (payType == 'alipay') {
			$("#3rd_pay").html(successResult);
		} else if (payType == 'wxpay') {
			getBrandWCPayRequest(successResult);
		} else {

		}
	}

	$('#alipay').button('reset');
	$('#wxpay').button('reset');
}

function getBrandWCPayRequest(data) {
	data = data.replace(/&quot;/g, "\"");
	var obj = JSON.parse(data);

	try {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : obj.appId,
					"timeStamp" : obj.timeStamp,
					"nonceStr" : obj.nonceStr,
					"package" : obj.packageValue,
					"signType" : obj.signType,
					"paySign" : obj.paySign
				}, function(res) {
					WeixinJSBridge.log(res.err_msg);

					if (res.err_msg == 'get_brand_wcpay_request:ok') {
						top.location.href = appUrl;
					} else if (res.err_msg == 'get_brand_wcpay_request:fail') {
						alert(res.err_code + res.err_desc + res.err_msg);
					}
				});
	} catch (e) {
		alert(e);
	}
}

function success(message) {
	$('#failMessage').html();
	$('#failMsgDiv').hide();

	$('#successMessage').html(message);
	$('#successMsgDiv').show();
}

function error(message) {
	$('#successMessage').html();
	$('#successMsgDiv').hide();

	$('#failMessage').html(message);
	$('#failMsgDiv').show();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
