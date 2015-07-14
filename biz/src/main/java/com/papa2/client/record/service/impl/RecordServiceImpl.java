package com.papa2.client.record.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.record.IRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.record.dao.IRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class RecordServiceImpl implements IRecordService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(RecordServiceImpl.class);

	private IRecordDao recordDao;

	@Override
	public List<Record> getRecordList(String carNo) {
		Record record = new Record();

		if (StringUtils.isBlank(carNo)) {
			return null;
		}

		record.setCarNo(carNo.trim());

		try {
			return recordDao.getRecordList(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return null;
	}

	@Override
	public Record getRecord(String id) {
		Record record = new Record();

		if (StringUtils.isBlank(id)) {
			return null;
		}

		try {
			record.setId(Long.valueOf(id));
		} catch (NumberFormatException e) {
			logger.error(id, e);
			return null;
		}

		try {
			return recordDao.getRecord(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return null;
	}

	public IRecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(IRecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
