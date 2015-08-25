package com.papa2.client.api.space;

import java.util.List;

import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISpaceService {

	// >>>>>>>>>>以下是出租<<<<<<<<<<

	/**
	 * 
	 * @param userId
	 * @param space
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createSpace(Long userId, Space space, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Space> getSpaceList(Long userId);

	/**
	 * 
	 * @param userId
	 * @param spaceId
	 * @return
	 */
	Space getSpace(Long userId, String spaceId);

	/**
	 * 
	 * @param userId
	 * @param space
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateSpace(Long userId, Space space, String modifyUser);

	/**
	 * 
	 * @param userId
	 * @param space
	 * @param modifyUser
	 * @return
	 */
	BooleanResult cancelSpace(Long userId, Space space, String modifyUser);

	/**
	 * 启用.
	 * 
	 * @param userId
	 * @param space
	 * @param modifyUser
	 * @return
	 */
	BooleanResult enableSpace(Long userId, Space space, String modifyUser);

	// >>>>>>>>>>以下是要租<<<<<<<<<<

	/**
	 * 
	 * @param parkId
	 * @return
	 */
	List<Space> getSpaceList(String parkId);

	/**
	 * 
	 * @param spaceId
	 * @return
	 */
	Space getSpace(String spaceId);

}
