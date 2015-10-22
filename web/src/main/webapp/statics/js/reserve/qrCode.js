var qrcode = null;

$(document).ready(function() {
	qrcode = new QRCode("qrcode", {
				text : token
			});

	setInterval(function() {
				$.ajax({
							type : "post",
							url : appUrl
									+ "/reserve/reserveAction!getQRCode.htm",
							data : {
								reserveId : reserveId,
								dateTime : new Date().getTime()
							},
							success : function(data) {
								qrcode.clear();
								qrcode.makeCode(data);
							},
							error : function() {

							}
						});
			}, 60000);

	setInterval(function() {
				$.ajax({
							type : "post",
							url : appUrl + "/reserve/reserveAction!trigger.htm",
							data : {
								reserveId : reserveId,
								dateTime : new Date().getTime()
							},
							success : function(data) {
								if (state == 'U' && data == 'I') {
									success('欢迎您。');
									setTimeout(function() {
												reserve();
											}, 3000);
								}

								if (state == 'I' && data == 'O') {
									success('欢迎下次再来。');
									setTimeout(function() {
												record();
											}, 3000);
								}

								if (state == 'U' && data == 'O') {
									error('车辆已离开停车场。');
								}
							},
							error : function() {

							}
						});
			}, 3000);

});

function back() {
	loading();
	top.location.href = appUrl + "/reserve/detail.htm?reserveId=" + reserveId;
}

function reserve() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function record() {
	loading();
	top.location.href = appUrl + "/record/index.htm";
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
