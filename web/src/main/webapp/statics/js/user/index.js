$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function back() {
	loading();
	top.location.href = appUrl + "/settings/index.htm";
}

function save() {
	if ($('#userName').val() == "") {
		error("昵称不能为空。");
		return;
	}

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/user/update.htm";
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
	}
	$('#btn').button('reset');
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
