function scanQRCode() {
	wx.scanQRCode();
}

wx.error(function(res) {
			alert(res.errMsg);
		});
