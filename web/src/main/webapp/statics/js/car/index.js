$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function goBack() {
	loading();
	top.location.href = appUrl + "/settings/index.htm";
}

function save() {
	if ($('#carNo').val() == "") {
		error("车牌信息不能为空。");
		return;
	}

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/car/create.htm";
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
		success(successResult);
		// reload
		top.location.href = appUrl + "/car/index.htm";
	}
	$('#btn').button('reset');
}

function success(message) {
	$('#fMessage').html();
	$('#fMsgDiv').hide();

	$('#sMessage').html(message);
	$('#sMsgDiv').show();
}

function error(message) {
	$('#sMessage').html();
	$('#sMsgDiv').hide();

	$('#fMessage').html(message);
	$('#fMsgDiv').show();
}

function pay(id) {
	loading();
	top.location.href = appUrl + "/pay/index.htm?id=" + id;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
