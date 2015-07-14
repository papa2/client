function goBack() {
	loading();
	top.location.href = appUrl + "/index.htm";
}

function pay(id) {
	loading();
	top.location.href = appUrl + "/pay/index.htm?id=" + id;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
