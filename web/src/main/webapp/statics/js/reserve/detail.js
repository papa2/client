function back() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function QR() {
	loading();
	top.location.href = appUrl + "/reserve/qrCode.htm?reserveId=" + reserveId;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
