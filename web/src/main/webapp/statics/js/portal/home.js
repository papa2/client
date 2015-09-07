$(document).ready(function() {
			$.ajax({
						type : "post",
						url : appUrl
								+ "/reserve/reserveAction!getReserveCount.htm",
						data : {
							dateTime : new Date().getTime()
						},
						success : function(data) {
							if (data != 0) {
								$("#reserveCount").iosbadge({
											content : data,
											size : 24
										});
							}
						},
						error : function() {

						}
					});
		});

function reserve() {
	loading();
	top.location.href = appUrl + "/reserve/index.htm";
}

function trade() {
	loading();
	top.location.href = appUrl + "/trade/index.htm";
}

function settings() {
	loading();
	top.location.href = appUrl + "/settings/index.htm";
}

function space() {
	loading();
	top.location.href = appUrl + "/space/index.htm";
}

function recommend() {
	loading();
	top.location.href = appUrl + "/recommend/index.htm";
}

function promotion() {
	loading();
	top.location.href = appUrl + "/promotion/index.htm";
}

function scan() {
	loading();
	top.location.href = appUrl + "/reserve/scan.htm";
}

function help() {
	loading();
	top.location.href = appUrl + "/help/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}