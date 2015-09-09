package com.papa2.client.reserve.action;

import java.util.List;

import com.papa2.client.api.record.IBossRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.api.wxap.ISignService;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.util.UUIDUtil;

/**
 * 扫一扫.
 * 
 * @author xujiakun
 * 
 */
public class ScanAction extends BaseAction {

	private static final long serialVersionUID = 133749244927063673L;

	private ISignService signService;

	private IReserveService reserveService;

	private IBossRecordService bossRecordService;

	/**
	 * 微信参数.
	 */
	private String appId;

	/**
	 * 微信参数.
	 */
	private String timestamp;

	/**
	 * 微信参数.
	 */
	private String nonceStr;

	/**
	 * 微信参数.
	 */
	private String signature;

	private List<Record> recordList;

	/**
	 * 二维码.
	 */
	private String token;

	/**
	 * 预约信息.
	 */
	private Reserve reserve;

	private String reserveId;

	/**
	 * 保安扫一扫.
	 * 
	 * @return
	 */
	public String scan() {
		timestamp = Long.toString(System.currentTimeMillis() / 1000);
		nonceStr = UUIDUtil.generate();

		signature = signService.sign(nonceStr, timestamp, env.getProperty("appUrl") + "/reserve/scan.htm");

		// 保安已扫码记录
		recordList = bossRecordService.getRecordList(this.getUser().getUserId());

		return SUCCESS;
	}

	public String result() {
		User user = this.getUser();

		reserve = reserveService.getReserve(user.getParkId(), user.getUserId(), token);

		return SUCCESS;
	}

	public String enter() {
		User user = this.getUser();

		BooleanResult result = reserveService.enter(user.getParkId(), user.getUserId(), reserveId);

		if (result.getResult()) {
			this.setSuccessMessage("确认进场成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String leave() {
		User user = this.getUser();

		BooleanResult result = reserveService.leave(user.getParkId(), user.getUserId(), reserveId);

		if (result.getResult()) {
			this.setSuccessMessage("确认出场成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public ISignService getSignService() {
		return signService;
	}

	public void setSignService(ISignService signService) {
		this.signService = signService;
	}

	public IReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public IBossRecordService getBossRecordService() {
		return bossRecordService;
	}

	public void setBossRecordService(IBossRecordService bossRecordService) {
		this.bossRecordService = bossRecordService;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

}
