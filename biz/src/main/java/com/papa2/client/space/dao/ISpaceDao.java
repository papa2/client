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

	/**
	 * 
	 * @param space
	 * @return
	 */
	Space getSpace(Space space);

	/**
	 * 
	 * @param space
	 * @return
	 */
	int updateSpace(Space space);

	/**
	 * 
	 * @param userId
	 * @param spaceId
	 * @param state
	 * @param modifyUser
	 * @return
	 */
	int updateSpace(Long userId, Long spaceId, String state, String modifyUser);

}
