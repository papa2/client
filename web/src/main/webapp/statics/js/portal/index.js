function help() {
	loading();
	top.location.href = appUrl + "/help/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}