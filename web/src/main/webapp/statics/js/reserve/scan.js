function scanQRCode() {
	wx.scanQRCode({
				needResult : 1,
				desc : 'scanQRCode desc',
				success : function(res) {
					result(res.resultStr);
				}
			});
}

function result(res) {
	loading();
	top.location.href = appUrl + "/reserve/result.htm?token=" + res;
}

wx.error(function(res) {
			alert(res.errMsg);
		});

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
