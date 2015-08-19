function back() {
	loading();
	top.location.href = appUrl + "/space/index.htm";
}

function next() {
	loading();

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/1st.htm";
	form.submit();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
