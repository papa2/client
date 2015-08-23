package com.papa2.client.api.park;

import java.util.List;

import com.papa2.client.api.park.bo.Park;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IParkService {

	/**
	 * 根据省市区查询 社区 公共停车场.
	 * 
	 * @param backCode
	 * @return
	 */
	List<Park> getParkList(String backCode);

	/**
	 * 根据省市区查询 社区 公共停车场.
	 * 
	 * @param backCode
	 * @param type
	 *            是否开放车位.
	 * @return
	 */
	List<Park> getParkList(String backCode, String type);

	/**
	 * 
	 * @param parkId
	 * @return
	 */
	Park getPark(Long parkId);

}
