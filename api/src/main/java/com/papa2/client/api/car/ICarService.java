package com.papa2.client.api.car;

import java.util.List;

import com.papa2.client.api.record.bo.Record;

/**
 * 我的车辆-正在停车记录.
 * 
 * @author xujiakun
 * 
 */
public interface ICarService {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Record> getRecords(String userId);

}
