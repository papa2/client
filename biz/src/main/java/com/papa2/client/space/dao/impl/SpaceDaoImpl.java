package com.papa2.client.space.dao.impl;

import java.util.List;

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

}
