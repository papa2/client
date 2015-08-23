package com.papa2.client.park.dao;

import java.util.List;

import com.papa2.client.api.park.bo.Park;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IParkDao {

	/**
	 * 
	 * @param park
	 * @return
	 */
	List<Park> getParkList(Park park);

	/**
	 * 
	 * @param park
	 * @return
	 */
	Park getPark(Park park);

}
