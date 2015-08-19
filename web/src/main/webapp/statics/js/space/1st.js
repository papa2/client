$(document).ready(function() {
			var e = {
				provinceId : "province",
				cityId : "city",
				countyId : "area",
				dfCode : backCode
			};
			$.fn.cityTools(e);
		});

function back() {
	loading();

	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/createPrepare.htm";
	form.submit();
}

function next() {
	$("#bc").val($("#backCode").val());

	loading();

	$('#btn2').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/2nd.htm";
	form.submit();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
