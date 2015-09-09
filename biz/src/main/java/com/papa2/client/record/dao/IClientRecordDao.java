package com.papa2.client.record.dao;

import java.util.List;

import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IClientRecordDao {

	/**
	 * 
	 * @param record
	 * @return
	 */
	Long createRecord(Record record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	int updateRecord(Record record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	int updateRecordState(Record record);

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
