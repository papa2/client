// 百度地图API功能
var map = new BMap.Map("allmap"); // 创建Map实例
map.centerAndZoom('杭州', 15); // 初始化地图,设置中心点坐标和地图级别。
map.addControl(new BMap.ZoomControl()); // 添加地图缩放控件
var traffic = new BMap.TrafficLayer(); // 创建交通流量图层实例
map.addTileLayer(traffic); // 将图层添加到地图上

function goBack() {
	loading();
	top.location.href = appUrl + "/index.htm";
}

function loading() {
	new Spinner({
				top : '500%'
			}).spin($("#foo")[0]);
}
