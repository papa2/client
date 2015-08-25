function back(backCode) {
	loading();
	top.location.href = appUrl + "/reserve/1st.htm?backCode=" + backCode;
}

function next(spaceId) {
	loading();
	top.location.href = appUrl + "/reserve/3rd.htm?backCode=" + backCode
			+ "&spaceId=" + spaceId;
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}