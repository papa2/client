package com.papa2.client.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.price.IPriceService;
import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.api.trade.ITradeService;
import com.papa2.client.api.trade.bo.Order;
import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.framework.util.UUIDUtil;
import com.papa2.client.trade.dao.IOrderDao;
import com.papa2.client.trade.dao.ITradeDao;

/**
 * 
 * @author JiakunXu
 * 
 */
public class TradeServiceImpl implements ITradeService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(TradeServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IMemcachedCacheService memcachedCacheService;

	private IClientRecordService clientRecordService;

	private IPriceService priceService;

	private ITradeDao tradeDao;

	private IOrderDao orderDao;

	@Override
	public BooleanResult createTrade(final Long userId, String[] recordId, final String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		final Trade trade = new Trade();
		trade.setTradeNo(UUIDUtil.generate());

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		trade.setUserId(userId);

		if (recordId == null || recordId.length == 0) {
			result.setCode("请选择停车记录。");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		trade.setModifyUser(modifyUser);

		BigDecimal tradePrice = BigDecimal.ZERO;

		final List<Order> orderList = new ArrayList<Order>();
		Record record = null;
		Space space = null;
		BigDecimal price = null;

		for (String recId : recordId) {
			record = clientRecordService.getRecord(userId, recId, "F");
			if (record == null) {
				result.setCode(recId + " 停车记录不存在。");
				return result;
			}

			Order order = new Order();
			space = record.getReserve().getSpace();
			order.setParkId(space.getParkId());
			order.setParkName(space.getParkName());
			order.setRecordId(record.getRecordId());
			order.setStartTime(record.getStartTime());
			order.setEndTime(record.getEndTime());

			price =
				priceService.cost(space.getCostType(), record.getCost(), record.getStartTime(), record.getEndTime());
			order.setPrice(price);

			tradePrice = tradePrice.add(price);

			orderList.add(order);
		}

		trade.setTradePrice(tradePrice);
		trade.setCouponPrice(BigDecimal.ZERO);
		trade.setType(ITradeService.TO_PAY);

		result = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);

				Long tradeId = null;

				try {
					tradeId = tradeDao.createTrade(trade);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(trade), e);
					ts.setRollbackOnly();

					result.setCode("写入交易表失败！");
					return result;
				}

				try {
					orderDao.createOrder(tradeId, orderList, modifyUser);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(orderList), e);
					ts.setRollbackOnly();

					result.setCode("写入交易明细表失败！");
					return result;
				}

				// 更新 record state F -> D
				result = clientRecordService.updateRecordState(userId, tradeId, "D", modifyUser);
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				return result;
			}
		});

		// 成功返回
		if (result.getResult()) {
			result.setCode(trade.getTradeNo());
			return result;
		}

		return result;
	}

	@Override
	public List<Trade> getTradeList(Long userId, String type) {
		Trade trade = new Trade();

		if (userId == null) {
			return null;
		}
		trade.setUserId(userId);

		if (StringUtils.isNotBlank(type)) {
			trade.setType(type.trim());
		}

		try {
			return tradeDao.getTradeList(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	@Override
	public Trade getTrade(Long userId, String tradeNo) {
		if (userId == null || StringUtils.isBlank(tradeNo)) {
			return null;
		}

		Trade trade = new Trade();
		trade.setUserId(userId);
		trade.setTradeNo(tradeNo.trim());

		trade = getTrade(trade);

		if (trade == null) {
			return null;
		}

		Order order = new Order();
		order.setTradeId(trade.getTradeId());
		List<Order> orderList = getOrderList(order);

		if (orderList == null || orderList.size() == 0) {
			return null;
		}

		trade.setOrderList(orderList);

		return trade;
	}

	@Override
	public Trade getTrade(String tradeNo) {
		if (StringUtils.isBlank(tradeNo)) {
			return null;
		}

		Trade trade = new Trade();
		trade.setTradeNo(tradeNo.trim());

		return getTrade(trade);
	}

	private Trade getTrade(Trade trade) {
		try {
			return tradeDao.getTrade(trade);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
		}

		return null;
	}

	private List<Order> getOrderList(Order order) {
		try {
			return orderDao.getOrderList(order);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}

		return null;
	}

	@Override
	public BooleanResult payTrade(String tradeNo, String payType, String payDate) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Trade trade = new Trade();
		trade.setType(ITradeService.PAY);

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易订单不能为空。");
			return result;
		}
		trade.setTradeNo(tradeNo.trim());

		if (StringUtils.isBlank(payType)) {
			result.setCode("支付类型不能为空。");
			return result;
		}
		trade.setPayType(payType.trim());

		if (StringUtils.isBlank(payDate)) {
			result.setCode("支付时间不能为空。");
			return result;
		}
		trade.setPayDate(payDate);

		trade.setModifyUser(payType);

		try {
			int c = tradeDao.updateTrade(trade);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(trade), e);
			result.setCode("更新交易表失败！");
		}

		return result;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IClientRecordService getClientRecordService() {
		return clientRecordService;
	}

	public void setClientRecordService(IClientRecordService clientRecordService) {
		this.clientRecordService = clientRecordService;
	}

	public IPriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(IPriceService priceService) {
		this.priceService = priceService;
	}

	public ITradeDao getTradeDao() {
		return tradeDao;
	}

	public void setTradeDao(ITradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
