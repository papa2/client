package com.papa2.client.api.reserve;

import com.papa2.client.api.reserve.bo.Reserve;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IReserveService {

	/**
	 * 
	 * @param spaceId
	 * @return
	 */
	Reserve getReserve(String spaceId);

}
