function back() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function detail(spaceId) {
	loading();
	top.location.href = appUrl + "/reserve/detail.htm?spaceId=" + spaceId;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}