function goBack() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function detail() {
	loading();
	top.location.href = appUrl + "/trade/detail.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
