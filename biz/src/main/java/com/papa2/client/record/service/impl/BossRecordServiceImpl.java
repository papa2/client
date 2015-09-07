package com.papa2.client.record.service.impl;

import com.papa2.client.api.record.IBossRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.record.dao.IBossRecordDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class BossRecordServiceImpl implements IBossRecordService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(BossRecordServiceImpl.class);

	private IBossRecordDao bossRecordDao;

	@Override
	public BooleanResult createRecord(Record record, String modifyUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public IBossRecordDao getBossRecordDao() {
		return bossRecordDao;
	}

	public void setBossRecordDao(IBossRecordDao bossRecordDao) {
		this.bossRecordDao = bossRecordDao;
	}

}
