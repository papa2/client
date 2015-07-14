package com.papa2.client.api.pay;

import com.papa2.client.api.record.bo.Record;

public interface IPayService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Record getRecord(String id);

}
