function pay() {
	loading();
	top.location.href = appUrl + "/pay/index.htm";
}

function trade() {
	loading();
	top.location.href = appUrl + "/trade/index.htm";
}

function help() {
	loading();
	top.location.href = appUrl + "/help/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}