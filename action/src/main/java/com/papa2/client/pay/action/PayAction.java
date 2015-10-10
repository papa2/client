package com.papa2.client.pay.action;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.pay.IPayService;
import com.papa2.client.api.trade.ITradeService;
import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.ClientUtil;

/**
 * 支付中心.
 * 
 * @author xujiakun
 * 
 */
public class PayAction extends BaseAction {

	private static final long serialVersionUID = -3773371524123852427L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(PayAction.class);

	private IPayService payService;

	private ITradeService tradeService;

	/**
	 * 查询参数/交易支付.
	 */
	private String tradeNo;

	private String redirectUrl;

	/**
	 * 用户唯一标识.
	 */
	private String openId;

	private Trade trade;

	/**
	 * 支付类型.
	 */
	private String payType;

	/**
	 * 微信支付.
	 * 
	 * @return
	 */
	public String authorize() {
		BooleanResult result;

		try {
			result =
				payService.authorize(
					URLEncoder.encode(env.getProperty("appUrl") + "/pay/index.htm?tradeNo=" + tradeNo, "UTF-8"),
					tradeNo);
		} catch (Exception e) {
			logger.error(e);
			return ERROR;
		}

		if (result.getResult()) {
			redirectUrl = result.getCode();

			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		openId = payService.getOpenId(this.getCode());

		if (StringUtils.isEmpty(openId)) {
			return ERROR;
		}

		trade = tradeService.getTrade(this.getUser().getUserId(), tradeNo);

		if (trade == null) {
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 支付.
	 * 
	 * @return
	 */
	public String pay() {
		BooleanResult result =
			payService.pay(this.getUser().getUserId(), openId, tradeNo, payType,
				ClientUtil.getIpAddr(this.getServletRequest()));

		if (result.getResult()) {
			this.setSuccessMessage(result.getCode());
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String wxNotify() {
		@SuppressWarnings("unchecked")
		BooleanResult result = payService.notify(this.getServletRequest().getParameterMap());

		this.setResourceResult(result.getCode());

		return RESOURCE_RESULT;
	}

	public IPayService getPayService() {
		return payService;
	}

	public void setPayService(IPayService payService) {
		this.payService = payService;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
