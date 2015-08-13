function home() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function user() {
	loading();
	top.location.href = appUrl + "/user/index.htm";
}

function car() {
	loading();
	top.location.href = appUrl + "/car/index.htm";
}

function password() {
	loading();
	top.location.href = appUrl + "/user/password.htm";
}

function logout() {
	loading();
	top.location.href = appUrl + "/logout.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
