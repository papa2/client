function goBack() {
	loading();
	top.location.href = appUrl + "/index.htm";
}

function pay() {
	loading();
	top.location.href = appUrl + "/pay/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
