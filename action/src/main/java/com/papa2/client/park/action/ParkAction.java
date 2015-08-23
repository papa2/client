package com.papa2.client.park.action;

import com.papa2.client.api.park.IParkService;
import com.papa2.client.framework.action.BaseAction;

/**
 * 社区 公共停车场.
 * 
 * @author xujiakun
 * 
 */
public class ParkAction extends BaseAction {

	private static final long serialVersionUID = 2546215646989779993L;

	private IParkService parkService;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	public IParkService getParkService() {
		return parkService;
	}

	public void setParkService(IParkService parkService) {
		this.parkService = parkService;
	}

}
