package com.papa2.client.trade.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.papa2.client.api.trade.bo.Order;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.trade.dao.IOrderDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OrderDaoImpl extends BaseDaoImpl implements IOrderDao {

	@Override
	public int createOrder(final Long tradeId, final List<Order> orderList, final String modifyUser) {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Order order : orderList) {
					order.setTradeId(tradeId);
					order.setModifyUser(modifyUser);

					executor.insert("trade.order.createOrder", order);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderList(Order order) {
		return getSqlMapClientTemplate().queryForList("trade.order.getOrderList", order);
	}

}
