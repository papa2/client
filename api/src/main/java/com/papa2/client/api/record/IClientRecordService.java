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
	 * @param userId
	 * @param record
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createRecord(Long userId, Record record, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param reserveId
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateRecord(Long userId, Long reserveId, String modifyUser);

	/**
	 * 根据交易编号 更新停车记录状态(是否已交易付款).
	 * 
	 * @param userId
	 * @param tradeId
	 * @param state
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateRecordState(Long userId, Long tradeId, String state, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param state
	 * @return
	 */
	List<Record> getRecordList(Long userId, String state);

	/**
	 * 
	 * @param userId
	 * @param state
	 * @param carNo
	 * @return
	 */
	List<Record> getRecordList(Long userId, String state, String carNo);

	/**
	 * 
	 * @param userId
	 * @param recordId
	 * @return
	 */
	Record getRecord(Long userId, String recordId);

	/**
	 * 
	 * @param userId
	 * @param recordId
	 * @param state
	 * @return
	 */
	Record getRecord(Long userId, String recordId, String state);

}
