package com.papa2.client.record.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.record.IBossRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
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
	public BooleanResult createRecord(Long userId, Record record, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (record == null) {
			result.setCode("记录日志信息不能为空。");
			return result;
		}

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		record.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		record.setModifyUser(modifyUser);

		try {
			bossRecordDao.createRecord(record);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			result.setCode("写入记录日志表失败！");
		}

		return result;
	}

	@Override
	public List<Record> getRecordList(Long userId) {
		Record record = new Record();

		if (userId == null) {
			return null;
		}

		record.setUserId(userId);

		try {
			return bossRecordDao.getRecordList(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		return null;
	}

	public IBossRecordDao getBossRecordDao() {
		return bossRecordDao;
	}

	public void setBossRecordDao(IBossRecordDao bossRecordDao) {
		this.bossRecordDao = bossRecordDao;
	}

}
