$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function back(parkId) {
	loading();

	$('#btn').button('loading');

	top.location.href = appUrl + "/reserve/2nd.htm?backCode=" + backCode
			+ "&parkId=" + parkId;
}

function cancel() {
	loading();

	top.location.href = appUrl + "/reserve/index.htm";
}

function reserve() {
	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/reserve/reserve.htm";
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

		setTimeout(function() {
					top.location.href = appUrl + "/reserve/index.htm";
				}, 3000);
	}

	$('#btn0').button('reset');
	$('#btn1').button('reset');
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
