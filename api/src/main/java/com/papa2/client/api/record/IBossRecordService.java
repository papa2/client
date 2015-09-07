package com.papa2.client.api.record;

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
	 * @param record
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createRecord(Record record, String modifyUser);

}
