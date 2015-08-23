function cancel() {
	loading();

	$('#btn0').button('loading');

	top.location.href = appUrl + "/space/index.htm";
}

function back() {
	loading();

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/1st.htm";
	form.submit();
}

function next(parkId) {
	$('#parkId').val(parkId);

	loading();

	var form = window.document.forms[0];
	form.action = appUrl + "/space/3rd.htm";
	form.submit();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
