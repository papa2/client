function back() {
	loading();
	top.location.href = appUrl + "/trade/index.htm";
}

function detail(recordId) {
	loading();
	top.location.href = appUrl + "/record/detail.htm?recordId=" + recordId
			+ "&tradeNo=" + tradeNo;
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
