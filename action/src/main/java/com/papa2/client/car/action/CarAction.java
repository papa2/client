package com.papa2.client.car.action;

import java.util.List;

import com.papa2.client.api.car.ICarService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.action.BaseAction;

/**
 * 我的车辆.
 * 
 * @author xujiakun
 * 
 */
public class CarAction extends BaseAction {

	private static final long serialVersionUID = 2962533123805048247L;

	private ICarService carService;

	private List<Record> recordList;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {

		recordList = carService.getRecords("userId");

		return SUCCESS;
	}

	public ICarService getCarService() {
		return carService;
	}

	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

}
