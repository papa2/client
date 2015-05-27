function goBack() {
	loading();
	top.location.href = appUrl + "/car/index.htm";
}

function promotion() {
	loading();
	top.location.href = appUrl + "/promotion/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
