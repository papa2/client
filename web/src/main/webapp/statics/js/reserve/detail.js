function back() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
