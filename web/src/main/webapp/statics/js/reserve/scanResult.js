$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function back() {
	loading();
	top.location.href = appUrl + "/reserve/scan.htm";
}

function enter() {
	$('#btn0').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/reserve/enter.htm";
	form.target = "hideFrame";
	form.submit();
}

function leave() {
	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/reserve/leave.htm";
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

	if (state == 'U') {
		$('#btn0').button('reset');
	} else if (state == 'I') {
		$('#btn1').button('reset');
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