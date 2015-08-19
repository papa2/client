function back() {
	loading();

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/1st.htm";
	form.submit();
}

function next(caseId) {
	$('#caseId').val(caseId);

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
