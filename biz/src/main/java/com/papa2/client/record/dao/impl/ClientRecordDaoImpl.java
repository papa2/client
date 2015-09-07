package com.papa2.client.record.dao.impl;

import java.util.List;

import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.record.dao.IClientRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ClientRecordDaoImpl extends BaseDaoImpl implements IClientRecordDao {

	@Override
	public Long createRecord(Record record) {
		return (Long) getSqlMapClientTemplate().insert("record.client.createRecord", record);
	}

	@Override
	public int updateRecord(Record record) {
		return getSqlMapClientTemplate().update("record.client.updateRecord", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getRecordList(Record record) {
		return getSqlMapClientTemplate().queryForList("record.client.getRecordList", record);
	}

	@Override
	public Record getRecord(Record record) {
		return (Record) getSqlMapClientTemplate().queryForObject("record.client.getRecord", record);
	}

}
