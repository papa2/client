package com.papa2.client.record.dao;

import java.util.List;

import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IBossRecordDao {

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
	List<Record> getRecordList(Record record);

}
