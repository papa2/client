function goBack() {
	loading();
	top.location.href = appUrl + "/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
