package com.papa2.client.reserve.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.car.ICarService;
import com.papa2.client.api.car.bo.Car;
import com.papa2.client.api.park.IParkService;
import com.papa2.client.api.park.bo.Park;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.struts.annotations.JsonResult;

/**
 * 预约.
 * 
 * @author xujiakun
 * 
 */
public class ReserveAction extends BaseAction {

	private static final long serialVersionUID = 3172508458518842227L;

	private IReserveService reserveService;

	private IParkService parkService;

	private ISpaceService spaceService;

	private ICarService carService;

	private int count;

	private List<Reserve> reserveList;

	private List<Park> parkList;

	private String backCode;

	private List<Space> spaceList;

	private String parkId;

	private Reserve reserve;

	private String spaceId;

	private List<Car> carList;

	/**
	 * 查询具体预约信息.
	 */
	private String reserveId;

	/**
	 * 二维码.
	 */
	private String token;

	@JsonResult(field = "count")
	public String getReserveCount() {
		count = reserveService.getReserveCount(this.getUser().getUserId());

		return JSON_RESULT;
	}

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		reserveList = reserveService.getReserveList(this.getUser().getUserId());

		return SUCCESS;
	}

	public String first() {
		if (StringUtils.isNotBlank(backCode)) {
			parkList = parkService.getParkList(backCode, "Y");
		}

		return SUCCESS;
	}

	/**
	 * 查询停车场出租车位.
	 * 
	 * @return
	 */
	public String second() {
		spaceList = spaceService.getSpaceList(parkId);

		return SUCCESS;
	}

	public String third() {
		// 车位详情
		reserve = reserveService.getSpace(spaceId);
		// 我的车
		carList = carService.getCarList(this.getUser().getUserId());

		return SUCCESS;
	}

	public String reserve() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = reserveService.createReserve(userId, reserve, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("预约车位成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String detail() {
		reserve = reserveService.getReserve(this.getUser().getUserId(), reserveId);

		return SUCCESS;
	}

	public String qrCode() {
		token = reserveService.generateToken(this.getUser().getUserId(), reserveId);

		return SUCCESS;
	}

	@JsonResult(field = "token")
	public String getQRCode() {
		qrCode();

		return JSON_RESULT;
	}

	public IReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public IParkService getParkService() {
		return parkService;
	}

	public void setParkService(IParkService parkService) {
		this.parkService = parkService;
	}

	public ISpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(ISpaceService spaceService) {
		this.spaceService = spaceService;
	}

	public ICarService getCarService() {
		return carService;
	}

	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Reserve> getReserveList() {
		return reserveList;
	}

	public void setReserveList(List<Reserve> reserveList) {
		this.reserveList = reserveList;
	}

	public List<Park> getParkList() {
		return parkList;
	}

	public void setParkList(List<Park> parkList) {
		this.parkList = parkList;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
