package com.papa2.client.cases.action;

import com.papa2.client.api.cases.ICaseService;
import com.papa2.client.framework.action.BaseAction;

/**
 * 社区 公共停车场.
 * 
 * @author xujiakun
 * 
 */
public class CaseAction extends BaseAction {

	private static final long serialVersionUID = 2546215646989779993L;

	private ICaseService caseService;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	public ICaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}

}
