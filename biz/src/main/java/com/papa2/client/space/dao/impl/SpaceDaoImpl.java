package com.papa2.client.space.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.space.dao.ISpaceDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class SpaceDaoImpl extends BaseDaoImpl implements ISpaceDao {

	@Override
	public Long createSpace(Space space) {
		return (Long) getSqlMapClientTemplate().insert("space.createSpace", space);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Space> getSpaceList(Space space) {
		return getSqlMapClientTemplate().queryForList("space.getSpaceList", space);
	}

	@Override
	public Space getSpace(Space space) {
		return (Space) getSqlMapClientTemplate().queryForObject("space.getSpace", space);
	}

	@Override
	public int updateSpace(Space space) {
		return getSqlMapClientTemplate().update("space.updateSpace1", space);
	}

	@Override
	public int updateSpace(Long userId, Long spaceId, String state, String modifyUser) {
		Map<String, Object> space = new HashMap<String, Object>();
		space.put("userId", userId);
		space.put("spaceId", spaceId);
		space.put("state", state);
		space.put("modifyUser", modifyUser);

		return getSqlMapClientTemplate().update("space.updateSpace2", space);
	}

}
