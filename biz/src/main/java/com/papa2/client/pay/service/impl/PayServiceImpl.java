package com.papa2.client.pay.service.impl;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.pay.IPayService;
import com.papa2.client.api.record.IRecordService;
import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author xujiakun
 * 
 */
public class PayServiceImpl implements IPayService {

	private IRecordService recordService;

	@Override
	public Record getRecord(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}

		return recordService.getRecord(id);
	}

	public IRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecordService recordService) {
		this.recordService = recordService;
	}

}
