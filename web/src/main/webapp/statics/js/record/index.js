$(function() {
			$('input').iCheck({
						checkboxClass : 'iradio_square-blue'
					});
			$('input').on('ifToggled', function(event) {
						check();
					});
		});

function back() {
	loading();
	top.location.href = appUrl + "/home.htm";
}

function detail(recordId) {
	loading();
	top.location.href = appUrl + "/record/detail.htm?recordId=" + recordId;
}

var checkType = true;
var sync = true;

function checkAll() {
	sync = false;

	var total = 0;
	var count = 0;

	$("input[name='recordId']").each(function() {
				if (checkType) {
					$(this).iCheck('check');
					total = dcmAdd(total, $("#cost" + this.value).val());
					count++;
				} else {
					$(this).iCheck('uncheck');
				}
			});

	if (checkType && count != 0) {
		$("#total").html(total);
		$("#count").html("(" + count + ")");
	} else {
		$("#total").html(0);
		$("#count").html("");
	}

	checkType = !checkType;

	sync = true;
}

function check() {
	if (!sync) {
		return;
	}

	var total = 0;
	var count = 0;

	$("input[name='recordId']").each(function() {
				if (this.checked) {
					total = dcmAdd(total, $("#cost" + this.value).val());
					count++;
				}
			});

	if (count == 0) {
		$("#total").html(0);
		$("#count").html("");
	} else {
		$("#total").html(total);
		$("#count").html("(" + count + ")");
	}
}

function confirm() {
	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/trade/create.htm";
	form.submit();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
