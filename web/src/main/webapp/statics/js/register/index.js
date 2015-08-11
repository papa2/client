var __last_login_passport__ = "__last_login_passport__";

$(document).ready(function() {
			agree();

			$('#hideFrame').bind('load', promgtMsg);
		});

function sendCheckCode() {
	if (disabled) {
		return;
	}

	if ($('#passport').val() == "") {
		error("账号名不能为空！");
		return;
	}

	$.ajax({
				type : "post",
				url : appUrl + "/register/sendCheckCode.htm",
				data : {
					"passport" : $("#passport").val(),
					dateTime : new Date().getTime()
				},
				beforeSend : function() {
					startTimer();
				},
				success : function(data) {
					success(data);
				},
				error : function(data) {
					error(data.responseJSON);
					stopTimer();
				}
			});
}

var timer;
var sendBtn = $('#sendBtn');
var disabled = false;

function stopTimer() {
	clearTimeout(timer);
	disabled = false;
	sendBtn.text('点此获取');
}

function startTimer() {
	disabled = true;
	btnCountdown(sendBtn, 60, function() {
				sendBtn.text('再次获取');
				disabled = false;
			});
}

function btnCountdown(obj, second, callback) {
	$(obj).text(second + '秒');
	if (--second >= 0) {
		timer = setTimeout(function() {
					btnCountdown(obj, second, callback);
				}, 1000);
	} else {
		callback();
	}
}

function agree() {
	if ($("input[type='checkbox']").is(':checked')) {
		$('#btn').removeAttr("disabled");
	} else {
		$('#btn').attr("disabled", "true");
	}
}

function save() {
	agree();

	if (!$("input[type='checkbox']").is(':checked')) {
		return;
	}

	if ($('#passport').val() == "") {
		error("手机号码不能为空或不正确！");
		return;
	}

	var pwd = $('#password').val();

	if (pwd == "") {
		error("密码不能为空！");
		return;
	}

	if (pwd.length < 6) {
		error("密码输入长度至少6位！");
		return;
	}

	$('#btn').button('loading');

	var form = window.document.forms[0];
	form.action = appUrl + "/register/register.htm";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != undefined && failResult != "") {
		error(failResult);
		$('#btn').button('reset');
	} else if (successResult != undefined) {
		success(successResult);

		// 写cookie
		var passport = $("#passport").val();

		$.cookie(__last_login_passport__, passport, {
					expires : 30,
					path : '/' + appName + '/',
					domain : domain
				});

		setTimeout(function() {
					top.location.href = appUrl + "/index.htm?goto="
							+ encodeURIComponent($("#goto").val());
				}, 3000);
	}
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
