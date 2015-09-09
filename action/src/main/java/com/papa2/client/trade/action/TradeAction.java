package com.papa2.client.trade.action;

import java.util.List;

import com.papa2.client.api.trade.ITradeService;
import com.papa2.client.api.trade.bo.Trade;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 我的账单.
 * 
 * @author xujiakun
 * 
 */
public class TradeAction extends BaseAction {

	private static final long serialVersionUID = 3172508458518842227L;

	private ITradeService tradeService;

	/**
	 * 查询参数.
	 */
	private String type;

	private List<Trade> tradeList;

	/**
	 * 勾选停车记录用于付款.
	 */
	private String[] recordId;

	/**
	 * 交易编号(第三方支付).
	 */
	private String tradeNo;

	/**
	 * 交易单明细.
	 */
	private Trade trade;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		tradeList = tradeService.getTradeList(this.getUser().getUserId(), type);

		return SUCCESS;
	}

	public String create() {
		Long userId = this.getUser().getUserId();
		BooleanResult result = tradeService.createTrade(userId, recordId, userId.toString());

		if (result.getResult()) {
			tradeNo = result.getCode();
			return SUCCESS;
		}

		return ERROR;
	}

	public String detail() {
		trade = tradeService.getTrade(this.getUser().getUserId(), tradeNo);

		if (trade == null) {
			return ERROR;
		}

		// 判断是否已完成支付
		if (ITradeService.TO_PAY.equals(trade.getType())) {
			tradeNo = trade.getTradeNo();
			return "redirect";
		}

		return SUCCESS;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	public String[] getRecordId() {
		return recordId;
	}

	public void setRecordId(String[] recordId) {
		this.recordId = recordId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

}
