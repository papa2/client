package com.papa2.client.record.dao;

import java.util.List;

import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IRecordDao {

	/**
	 * 
	 * @param record
	 * @return
	 */
	List<Record> getRecordList(Record record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	Record getRecord(Record record);

}
