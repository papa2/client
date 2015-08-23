package com.papa2.client.park.dao.impl;

import java.util.List;

import com.papa2.client.api.park.bo.Park;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.park.dao.IParkDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ParkDaoImpl extends BaseDaoImpl implements IParkDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Park> getParkList(Park park) {
		return getSqlMapClientTemplate().queryForList("park.getParkList", park);
	}

	@Override
	public Park getPark(Park park) {
		return (Park) getSqlMapClientTemplate().queryForObject("park.getPark", park);
	}

}
