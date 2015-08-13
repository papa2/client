package com.papa2.client.record.dao.impl;

import java.util.List;

import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.record.dao.IRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class RecordDaoImpl extends BaseDaoImpl implements IRecordDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getRecordList(Record record) {
		return getSqlMapClientTemplate().queryForList("record.getRecordList", record);
	}

	@Override
	public Record getRecord(Record record) {
		return (Record) getSqlMapClientTemplate().queryForObject("record.getRecord", record);
	}

}
