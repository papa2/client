package com.papa2.client.wxap.action;

import java.util.UUID;

import com.papa2.client.api.wxap.ISignatureService;
import com.papa2.client.framework.action.BaseAction;

/**
 * 
 * @author xujiakun
 * 
 */
public class ScanAction extends BaseAction {

	private static final long serialVersionUID = 133749244927063673L;

	private ISignatureService signatureService;

	private String appUrl;

	private String appId;

	private String timestamp;

	private String nonceStr;

	private String signature;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		timestamp = Long.toString(System.currentTimeMillis() / 1000);
		nonceStr = UUID.randomUUID().toString();

		signature = signatureService.signature(nonceStr, timestamp, appUrl + "/wxap/scan.htm");

		return SUCCESS;
	}

	public ISignatureService getSignatureService() {
		return signatureService;
	}

	public void setSignatureService(ISignatureService signatureService) {
		this.signatureService = signatureService;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
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

}
