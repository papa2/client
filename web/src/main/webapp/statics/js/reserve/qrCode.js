var qrcode = null;

$(document).ready(function() {
	qrcode = new QRCode("qrcode", {
				text : token
			});

	setInterval(function() {
				$.ajax({
							type : "post",
							url : appUrl
									+ "/reserve/reserveAction!getQRCode.htm",
							data : {
								reserveId : reserveId,
								dateTime : new Date().getTime()
							},
							success : function(data) {
								qrcode.clear();
								qrcode.makeCode(data);
							},
							error : function() {

							}
						});
			}, 60000);
});

function back() {
	loading();
	top.location.href = appUrl + "/reserve/detail.htm?reserveId=" + reserveId;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
