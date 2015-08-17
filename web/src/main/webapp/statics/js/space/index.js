function home() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function createSpace() {
	$('#btn').button('loading');
	top.location.href = appUrl + "/space/createPrepare.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}