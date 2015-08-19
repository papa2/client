$(document).ready(function() {
			$("#startTime").val(startTime);
			$("#endTime").val(endTime);

			$("#startYear").val(startYear);
			$("#startMonth").val(startMonth);
			$("#endYear").val(endYear);
			$("#endMonth").val(endMonth);

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

function cancel() {
	$('#btn0').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/cancel.htm";
	form.target = "hideFrame";
	form.submit();
}

function enable() {
	$('#btn0').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/enable.htm";
	form.target = "hideFrame";
	form.submit();
}

function update() {
	$('#btn1').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/update.htm";
	form.target = "hideFrame";
	form.submit();
}

function profit() {
	$('#btn2').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/space/profit.htm";
	form.target = "hideFrame";
	form.submit();
}

function back() {
	loading();

	$('#btn1').button('loading');

	top.location.href = appUrl + "/space/index.htm";
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		error(failResult);
	} else if (successResult != undefined) {
		success(successResult);

		setTimeout(function() {
					top.location.href = appUrl + "/space/index.htm";
				}, 3000);
	}

	$('#btn0').button('reset');
	$('#btn1').button('reset');
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
