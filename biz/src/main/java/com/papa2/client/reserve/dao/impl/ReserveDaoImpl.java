package com.papa2.client.reserve.dao.impl;

import java.util.List;

import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.reserve.dao.IReserveDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ReserveDaoImpl extends BaseDaoImpl implements IReserveDao {

	@Override
	public int getReserveCount(Reserve reserve) {
		return (Integer) getSqlMapClientTemplate().queryForObject("reserve.getReserveCount", reserve);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reserve> getReserveList(Reserve reserve) {
		return getSqlMapClientTemplate().queryForList("reserve.getReserveList", reserve);
	}

	@Override
	public Long createReserve(Reserve reserve) {
		return (Long) getSqlMapClientTemplate().insert("reserve.createReserve", reserve);
	}

	@Override
	public Reserve getReserve(Reserve reserve) {
		return (Reserve) getSqlMapClientTemplate().queryForObject("reserve.getReserve", reserve);
	}

}
