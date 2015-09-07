package com.papa2.client.api.pay;

import com.papa2.client.api.record.bo.Record;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IPayService {

	/**
	 * 
	 * @param userId
	 * @param recordId
	 * @return
	 */
	Record getRecord(Long userId, String recordId);

}
