package com.papa2.client.trade.action;

import com.papa2.client.framework.action.BaseAction;

/**
 * 我的账单.
 * 
 * @author xujiakun
 * 
 */
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = 3172508458518842227L;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	/**
	 * 账单明细.
	 * 
	 * @return
	 */
	public String detail() {
		return SUCCESS;
	}

}
