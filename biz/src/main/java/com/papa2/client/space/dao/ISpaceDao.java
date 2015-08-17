package com.papa2.client.space.dao;

import java.util.List;

import com.papa2.client.api.space.bo.Space;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISpaceDao {

	/**
	 * 
	 * @param space
	 * @return
	 */
	Long createSpace(Space space);

	/**
	 * 
	 * @param space
	 * @return
	 */
	List<Space> getSpaceList(Space space);

}
