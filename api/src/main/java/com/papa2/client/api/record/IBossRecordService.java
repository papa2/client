package com.papa2.client.api.record;

import java.util.List;

import com.papa2.client.api.record.bo.Record;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IBossRecordService {

	/**
	 * 
	 * @param userId
	 * @param record
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createRecord(Long userId, Record record, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Record> getRecordList(Long userId);

}
