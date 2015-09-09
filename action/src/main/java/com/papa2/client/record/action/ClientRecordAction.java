package com.papa2.client.record.action;

import java.util.List;

import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.framework.action.BaseAction;

/**
 * 
 * @author JiakunXu
 * 
 */
public class ClientRecordAction extends BaseAction {

	private static final long serialVersionUID = 4358297932710012177L;

	private IClientRecordService clientRecordService;

	private IReserveService reserveService;

	/**
	 * 停车记录用于勾选付款.
	 */
	private List<Record> recordList;

	/**
	 * 查询参数.
	 */
	private String recordId;

	/**
	 * 交易页面跳转暂存参数.
	 */
	private String tradeNo;

	/**
	 * 返回对象.
	 */
	private Record record;

	public String index() {
		recordList = clientRecordService.getRecordList(this.getUser().getUserId(), "F");

		return SUCCESS;
	}

	public String detail() {
		record = clientRecordService.getRecord(this.getUser().getUserId(), recordId);

		return SUCCESS;
	}

	public IClientRecordService getClientRecordService() {
		return clientRecordService;
	}

	public void setClientRecordService(IClientRecordService clientRecordService) {
		this.clientRecordService = clientRecordService;
	}

	public IReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
