package com.papa2.client.car.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.car.ICarService;
import com.papa2.client.api.record.IRecordService;
import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author xujiakun
 * 
 */
public class CarServiceImpl implements ICarService {

	private IRecordService recordService;

	@Override
	public List<Record> getRecords(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}

		return recordService.getRecordList("X");
	}

	public IRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecordService recordService) {
		this.recordService = recordService;
	}

}
