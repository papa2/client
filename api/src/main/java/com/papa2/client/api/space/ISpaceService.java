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

}
