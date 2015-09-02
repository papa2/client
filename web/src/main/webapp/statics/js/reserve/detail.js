function back() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

var qrcode = null;

function QR() {
	if (qrcode) {
		qrcode.clear();
		qrcode.makeCode(new Date().getTime().toString());
	} else {
		qrcode = new QRCode("qrcode", {
					text : new Date().getTime().toString(),
					width : 300,
					height : 300
				});
	}

	$('#QRCodeModal').modal('show');
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
