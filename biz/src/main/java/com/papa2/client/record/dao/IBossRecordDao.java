package com.papa2.client.record.dao;

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

}
