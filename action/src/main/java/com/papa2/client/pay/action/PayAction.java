package com.papa2.client.pay.action;

import com.papa2.client.api.pay.IPayService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.action.BaseAction;

/**
 * 支付中心.
 * 
 * @author xujiakun
 * 
 */
public class PayAction extends BaseAction {

	private static final long serialVersionUID = -3773371524123852427L;

	private IPayService payService;

	private String id;

	private Record record;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {

		record = payService.getRecord(id);

		return SUCCESS;
	}

	public IPayService getPayService() {
		return payService;
	}

	public void setPayService(IPayService payService) {
		this.payService = payService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
