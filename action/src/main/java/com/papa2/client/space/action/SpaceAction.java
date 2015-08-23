package com.papa2.client.space.action;

import java.util.List;

import com.papa2.client.api.park.IParkService;
import com.papa2.client.api.park.bo.Park;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.util.DateUtil;

/**
 * 出租车位.
 * 
 * @author xujiakun
 * 
 */
public class SpaceAction extends BaseAction {

	private static final long serialVersionUID = 438411530615208990L;

	private IParkService parkService;

	private ISpaceService spaceService;

	private List<Park> parkList;

	/**
	 * 出租车位.
	 */
	private Space space;

	/**
	 * 省市区.
	 */
	private String backCode;

	/**
	 * 所属小区.
	 */
	private Park park;

	/**
	 * 当前年.
	 */
	private int year;

	/**
	 * 当前月.
	 */
	private int month;

	/**
	 * 查询我的出租车位.
	 */
	private List<Space> spaceList;

	private String spaceId;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		spaceList = spaceService.getSpaceList(this.getUser().getUserId());

		return SUCCESS;
	}

	/**
	 * 选择 包月包年 或者 按时.
	 * 
	 * @return
	 */
	public String createPrepare() {
		if (space == null) {
			space = new Space();
			space.setCostType("H");
		}

		return CREATE_PREPARE;
	}

	/**
	 * first 选择省市区.
	 * 
	 * @return
	 */
	public String first() {
		return SUCCESS;
	}

	/**
	 * second 选择所在地区 开放物业.
	 * 
	 * @return
	 */
	public String second() {
		if (space != null && "M".equals(space.getCostType())) {
			parkList = parkService.getParkList(backCode, "Y");
		} else {
			parkList = parkService.getParkList(backCode);
		}

		return SUCCESS;
	}

	/**
	 * third 选择计价.
	 * 
	 * @return
	 */
	public String third() {
		if (space != null) {
			park = parkService.getPark(space.getParkId());
		}

		year = DateUtil.getYear();
		month = DateUtil.getMonth();

		return SUCCESS;
	}

	public String create() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = spaceService.createSpace(userId, space, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("出租车位信息添加成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String detail() {
		space = spaceService.getSpace(this.getUser().getUserId(), spaceId);

		return SUCCESS;
	}

	public String update() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = spaceService.updateSpace(userId, space, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("出租信息修改成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String cancel() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = spaceService.cancelSpace(userId, space, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("出租信息取消成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String enable() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = spaceService.enableSpace(userId, space, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("出租信息启用成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
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

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

}
