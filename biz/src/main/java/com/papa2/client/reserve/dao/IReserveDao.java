package com.papa2.client.reserve.dao;

import java.util.List;

import com.papa2.client.api.reserve.bo.Reserve;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IReserveDao {

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	int getReserveCount(Reserve reserve);

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	List<Reserve> getReserveList(Reserve reserve);

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	Long createReserve(Reserve reserve);

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	Reserve getReserve(Reserve reserve);

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	int updateReserve(Reserve reserve);

}
