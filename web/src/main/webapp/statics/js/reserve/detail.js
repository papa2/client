$(document).ready(function() {
			if (state == 'U' && expireState == 'Y') {
				error("预约车位已超时过期，请重新预约。");
			}
		});

function back() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function QR(state) {
	loading();
	top.location.href = appUrl + "/reserve/qrCode.htm?reserveId=" + reserveId
			+ "&state=" + state;
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
