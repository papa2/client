package com.papa2.client.trade.dao.impl;

import java.util.List;

import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.trade.dao.ITradeDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeDaoImpl extends BaseDaoImpl implements ITradeDao {

	@Override
	public Long createTrade(Trade trade) {
		return (Long) getSqlMapClientTemplate().insert("trade.createTrade", trade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> getTradeList(Trade trade) {
		return getSqlMapClientTemplate().queryForList("trade.getTradeList", trade);
	}

	@Override
	public Trade getTrade(Trade trade) {
		return (Trade) getSqlMapClientTemplate().queryForObject("trade.getTrade", trade);
	}

	@Override
	public int updateTrade(Trade trade) {
		return getSqlMapClientTemplate().update("trade.updateTrade", trade);
	}

}
