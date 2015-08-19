function back() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
