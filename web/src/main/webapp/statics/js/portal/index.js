function navigate() {
	loading();
	top.location.href = appUrl + "/navigate/index.htm";
}

function reserve() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

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

function recommend() {
	loading();
	top.location.href = appUrl + "/recommend/index.htm";
}

function promotion() {
	loading();
	top.location.href = appUrl + "/promotion/index.htm";
}

function about() {
	loading();
	top.location.href = appUrl + "/about/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}