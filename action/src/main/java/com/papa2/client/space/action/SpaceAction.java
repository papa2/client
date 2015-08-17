package com.papa2.client.space.action;

import java.util.List;

import com.papa2.client.api.cases.ICaseService;
import com.papa2.client.api.cases.bo.Case;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 出租车位.
 * 
 * @author xujiakun
 * 
 */
public class SpaceAction extends BaseAction {

	private static final long serialVersionUID = 438411530615208990L;

	private ICaseService caseService;

	private ISpaceService spaceService;

	private List<Case> caseList;

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
	private Case cases;

	/**
	 * 查询我的出租车位.
	 */
	private List<Space> spaceList;

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
			caseList = caseService.getCaseList(backCode, "Y");
		} else {
			caseList = caseService.getCaseList(backCode);
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
			cases = caseService.getCase(space.getCaseId());
		}

		return SUCCESS;
	}

	public String create() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = spaceService.createSpace(userId, space, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("车位信息添加成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public ICaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}

	public ISpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(ISpaceService spaceService) {
		this.spaceService = spaceService;
	}

	public List<Case> getCaseList() {
		return caseList;
	}

	public void setCaseList(List<Case> caseList) {
		this.caseList = caseList;
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

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

}
