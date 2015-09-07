package com.papa2.client.record.service.impl;

import java.util.List;

import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.record.dao.IClientRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ClientRecordServiceImpl implements IClientRecordService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ClientRecordServiceImpl.class);

	private IClientRecordDao clientRecordDao;

	@Override
	public BooleanResult createRecord(Record record, String modifyUser) {
		return null;
	}

	@Override
	public BooleanResult updateRecord(Record record, String modifyUser) {
		return null;
	}

	@Override
	public List<Record> getRecordList(Long userId, String carNo) {
		Record record = new Record();

		try {
			return clientRecordDao.getRecordList(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return null;
	}

	@Override
	public Record getRecord(Long userId, String recordId) {
		Record record = new Record();

		try {
			return clientRecordDao.getRecord(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return null;
	}

	public IClientRecordDao getClientRecordDao() {
		return clientRecordDao;
	}

	public void setClientRecordDao(IClientRecordDao clientRecordDao) {
		this.clientRecordDao = clientRecordDao;
	}

}
