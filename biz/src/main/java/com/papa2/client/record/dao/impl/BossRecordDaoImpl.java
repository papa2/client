package com.papa2.client.record.dao.impl;

import java.util.List;

import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.record.dao.IBossRecordDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class BossRecordDaoImpl extends BaseDaoImpl implements IBossRecordDao {

	@Override
	public Long createRecord(Record record) {
		return (Long) getSqlMapClientTemplate().insert("record.boss.createRecord", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getRecordList(Record record) {
		return getSqlMapClientTemplate().queryForList("record.boss.getRecordList", record);
	}

}
