package com.papa2.client.reserve.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.park.IParkService;
import com.papa2.client.api.park.bo.Park;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.action.BaseAction;

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

	private List<Park> parkList;

	private String backCode;

	private List<Space> spaceList;

	private String parkId;

	private Reserve reserve;

	private String spaceId;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
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
	public String list() {
		spaceList = spaceService.getSpaceList(parkId);

		return SUCCESS;
	}

	public String detail() {
		reserve = reserveService.getReserve(spaceId);

		return SUCCESS;
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

}
