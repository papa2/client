function back() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function detail(reserveId) {
	loading();
	top.location.href = appUrl + "/reserve/detail.htm?reserveId=" + reserveId;
}

function reserve() {
	loading();
	top.location.href = appUrl + "/reserve/1st.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
