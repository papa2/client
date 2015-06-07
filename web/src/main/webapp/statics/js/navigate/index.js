// 百度地图API功能
function loadJScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=57GsXSutGfXcANaX3GfW1FqZ&callback=init";
	document.body.appendChild(script);
}

function init() {
	var map = new BMap.Map("allmap"); // 创建Map实例
	var point = new BMap.Point(120.175, 30.285);
	map.centerAndZoom(point, 15);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r) {
				if (this.getStatus() == BMAP_STATUS_SUCCESS) {
					var mk = new BMap.Marker(r.point);
					map.addOverlay(mk);
					map.panTo(r.point);
					// alert('您的位置：' + r.point.lng + ',' + r.point.lat);
				} else {
					// alert('failed' + this.getStatus());
				}
			}, {
				enableHighAccuracy : true
			})
}

window.onload = loadJScript; // 异步加载地图

function goBack() {
	loading();
	top.location.href = appUrl + "/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
