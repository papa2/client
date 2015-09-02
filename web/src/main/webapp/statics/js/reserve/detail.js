function back() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function QR() {
	$('#QRCodeModal').modal('show');

	var qrcode = new QRCode("qrcode", {
				text : new Date().getTime(),
				width : 300,
				height : 300
			});
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
