$(document).ready(function() {
			$("input[name='inlineRadioOptions']").change(function() {
						var s = $("input[name='inlineRadioOptions']:checked")
								.val();
						if (s == 'W') {
							$("#inlineCheckbox1").prop("checked", true);
							$("#inlineCheckbox2").prop("checked", true);
							$("#inlineCheckbox3").prop("checked", true);
							$("#inlineCheckbox4").prop("checked", true);
							$("#inlineCheckbox5").prop("checked", true);
							$("#inlineCheckbox6").prop("checked", false);
							$("#inlineCheckbox7").prop("checked", false);
						} else if (s == 'H') {
							$("#inlineCheckbox1").prop("checked", false);
							$("#inlineCheckbox2").prop("checked", false);
							$("#inlineCheckbox3").prop("checked", false);
							$("#inlineCheckbox4").prop("checked", false);
							$("#inlineCheckbox5").prop("checked", false);
							$("#inlineCheckbox6").prop("checked", true);
							$("#inlineCheckbox7").prop("checked", true);
						}
					});

			$('#hideFrame').bind('load', promgtMsg);
		});

function toggleCheckbox() {
	$("#inlineRadio3").prop("checked", true);
}

function back() {
	loading();

	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/2nd.htm";
	form.submit();
}

function done() {
	if ($('#spaceCode').val() == "") {
		error("出租车位编号信息不能为空。");
		return;
	}

	$('#btn1').hide();
	$('#btn2').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/create.htm";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		error(failResult);
		$('#btn1').show();
	} else if (successResult != undefined) {
		success(successResult);

		top.location.href = appUrl + "/space/index.htm";
	}

	$('#btn2').button('reset');
}

function success(message) {
	$('#failMessage').html();
	$('#failMsgDiv').hide();

	$('#successMessage').html(message);
	$('#successMsgDiv').show();
}

function error(message) {
	$('#successMessage').html();
	$('#successMsgDiv').hide();

	$('#failMessage').html(message);
	$('#failMsgDiv').show();
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
