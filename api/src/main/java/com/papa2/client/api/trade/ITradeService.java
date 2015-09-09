package com.papa2.client.api.trade;

import java.util.List;

import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ITradeService {

	String TO_PAY = "topay";

	String PAY = "pay";

	/**
	 * 
	 * @param userId
	 * @param recordId
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createTrade(Long userId, String[] recordId, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param state
	 * @return
	 */
	List<Trade> getTradeList(Long userId, String state);

	/**
	 * 
	 * @param userId
	 * @param tradeNo
	 * @return
	 */
	Trade getTrade(Long userId, String tradeNo);

}
