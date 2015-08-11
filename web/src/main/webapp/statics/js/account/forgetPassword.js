var __last_login_passport__ = "__last_login_passport__";

$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
		});

function sendCheckCode() {
	Messenger().hideAll();

	if (disabled) {
		return;
	}

	if ($('#passport').val() == "") {
		error("账号名不能为空！");
		return;
	}

	$.ajax({
				type : "post",
				url : appUrl + "/account/sendCheckCode.htm",
				data : {
					"passport" : $("#passport").val(),
					"type" : 'forget',
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

function save() {
	if ($('#passport').val() == "") {
		error("账号名不能为空！");
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
	form.action = appUrl + "/account/msetPassword.htm";
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
					top.location.href = appUrl + "/mindex.htm?goto="
							+ encodeURIComponent($("#goto").val());
				}, 3000);
	}
}

function success(message) {
	Messenger.options = {
		extraClasses : 'messenger-fixed messenger-on-top',
		theme : 'air'
	}
	Messenger().hideAll();
	Messenger().post({
				message : message,
				type : 'success',
				showCloseButton : true
			});
}

function error(message) {
	Messenger.options = {
		extraClasses : 'messenger-fixed messenger-on-top',
		theme : 'air'
	}
	Messenger().hideAll();
	Messenger().post({
				message : message,
				type : 'error',
				showCloseButton : true
			});
}