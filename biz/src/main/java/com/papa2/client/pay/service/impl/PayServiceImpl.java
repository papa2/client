package com.papa2.client.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.pay.IPayService;
import com.papa2.client.api.trade.ITradeService;
import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.api.wxpay.IWxpayService;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.DateUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class PayServiceImpl implements IPayService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(PayServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IWxpayService wxpayService;

	private ITradeService tradeService;

	@Override
	public BooleanResult authorize(String redirectUrl, String state) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(redirectUrl)) {
			result.setCode("redirectUrl 不能为空.");
			return result;
		}

		try {
			result.setCode(wxpayService.authorize(redirectUrl, "snsapi_base", state));
			result.setResult(true);
		} catch (ServiceException e) {
			logger.error(e);

			result.setCode(e.getMessage());
		}

		return result;
	}

	@Override
	public String getOpenId(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		try {
			return wxpayService.getOpenId(code);
		} catch (ServiceException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public BooleanResult pay(Long userId, String openId, String tradeNo, String payType, String ip) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空.");
			return result;
		}

		if (StringUtils.isBlank(tradeNo)) {
			result.setCode("交易信息不能为空.");
			return result;
		}

		Trade trade = tradeService.getTrade(userId, tradeNo);

		if (trade == null) {
			result.setCode("当前交易不存在.");
			return result;
		}

		if (!ITradeService.TO_PAY.equals(trade.getType())) {
			result.setCode("当前交易已完成支付.");
			return result;
		}

		// 锁定订单
		String key = tradeNo.trim();

		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_TRADE_NO + key, key,
				IMemcachedCacheService.CACHE_KEY_TRADE_NO_DEFAULT_EXP);
		} catch (ServiceException e) {
			result.setCode("当前订单已被锁定，请稍后再试。");
			return result;
		}

		if (IPayService.PAY_TYPE_ALIPAY.equals(payType)) {

			return result;
		}

		if (IPayService.PAY_TYPE_WXPAY.equals(payType)) {
			BigDecimal price = trade.getPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
			String timeStart = DateUtil.getNowDateminStr();
			String timeExpire =
				DateUtil.datetime(DateUtil.addMinutes(new Date(), 15), DateUtil.DEFAULT_DATEFULLTIME_FORMAT);

			try {
				result.setCode(wxpayService.getBrandWCPayRequest(tradeNo, "停车缴费：" + trade.getTradeNo(), null, null,
					Integer.parseInt(price.toString()), ip, timeStart, timeExpire, openId, userId.toString()));
				result.setResult(true);
			} catch (ServiceException e) {
				logger.error(e);

				result.setCode(e.getMessage());
			}

			return result;
		}

		result.setCode("支付类型.");
		return result;
	}

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	@Override
	public BooleanResult callback(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean notify(String tradeNo, Map<String, String> params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BooleanResult notify(Map<String, Object> params) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		for (Map.Entry<String, Object> m : params.entrySet()) {
			System.out.println(m.getKey());
			System.out.println(m.getValue());
		}

		result.setCode("<xml><return_code><![CDATA[FAIL]]></return_code></xml>");

		return result;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IWxpayService getWxpayService() {
		return wxpayService;
	}

	public void setWxpayService(IWxpayService wxpayService) {
		this.wxpayService = wxpayService;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

}
