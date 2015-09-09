package com.papa2.client.trade.dao;

import java.util.List;

import com.papa2.client.api.trade.bo.Order;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IOrderDao {

	/**
	 * 
	 * @param tradeId
	 * @param orderList
	 * @param modifyUser
	 * @return
	 */
	int createOrder(Long tradeId, List<Order> orderList, String modifyUser);

	/**
	 * 
	 * @param order
	 * @return
	 */
	List<Order> getOrderList(Order order);

}
