function back() {
	loading();

	if (tradeNo != '') {
		top.location.href = appUrl + "/trade/detail.htm?tradeNo=" + tradeNo
	} else {
		top.location.href = appUrl + "/record/index.htm";
	}
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
