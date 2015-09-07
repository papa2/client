package com.papa2.client.api.record;

import java.util.List;

import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 记录停车信息.
 * 
 * @author xujiakun
 * 
 */
public interface IClientRecordService {

	/**
	 * 
	 * @param record
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createRecord(Record record, String modifyUser);

	/**
	 * 
	 * @param record
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateRecord(Record record, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param carNo
	 * @return
	 */
	List<Record> getRecordList(Long userId, String carNo);

	/**
	 * 
	 * @param userId
	 * @param recordId
	 * @return
	 */
	Record getRecord(Long userId, String recordId);

}
