function back() {
	loading();
	top.location.href = appUrl + "/trade/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
