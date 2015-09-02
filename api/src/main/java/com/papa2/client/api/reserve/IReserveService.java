package com.papa2.client.api.reserve;

import java.util.List;

import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IReserveService {

	/**
	 * 查询我的有效预约.
	 * 
	 * @param userId
	 * @return
	 */
	int getReserveCount(Long userId);

	/**
	 * 查询我的预约.
	 * 
	 * @param userId
	 * @return
	 */
	List<Reserve> getReserveList(Long userId);

	/**
	 * 获取出租车位详细信息.
	 * 
	 * @param spaceId
	 * @return
	 */
	Reserve getSpace(String spaceId);

	/**
	 * 预约车位.
	 * 
	 * @param userId
	 * @param reserve
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createReserve(Long userId, Reserve reserve, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param reserveId
	 * @return
	 */
	Reserve getReserve(Long userId, String reserveId);

}
