function back() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function detail(tradeNo) {
	loading();
	top.location.href = appUrl + "/trade/detail.htm?showwxpaytitle=1&tradeNo="
			+ tradeNo;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
