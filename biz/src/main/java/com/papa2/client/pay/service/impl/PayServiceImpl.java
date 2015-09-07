package com.papa2.client.pay.service.impl;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.pay.IPayService;
import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author xujiakun
 * 
 */
public class PayServiceImpl implements IPayService {

	private IClientRecordService clientRecordService;

	@Override
	public Record getRecord(Long userId, String recordId) {
		if (userId == null || StringUtils.isBlank(recordId)) {
			return null;
		}

		return clientRecordService.getRecord(userId, recordId);
	}

	public IClientRecordService getClientRecordService() {
		return clientRecordService;
	}

	public void setClientRecordService(IClientRecordService clientRecordService) {
		this.clientRecordService = clientRecordService;
	}

}
