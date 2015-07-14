package com.papa2.client.api.record;

import java.util.List;

import com.papa2.client.api.record.bo.Record;

/**
 * 记录停车信息.
 * 
 * @author xujiakun
 * 
 */
public interface IRecordService {

	/**
	 * 
	 * @param carNo
	 * @return
	 */
	List<Record> getRecordList(String carNo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Record getRecord(String id);

}
