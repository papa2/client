package com.papa2.client.api.wxpay.bo;

import org.dom4j.Element;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UnifiedOrderReturn extends Result {

	private UnifiedOrder unifiedOrder = new UnifiedOrder();

	public void visit(Element node) {
		super.visit(node);

		String name = node.getName();
		String text = node.getText();

		if ("appid".equals(name)) {
			unifiedOrder.setAppId(text);
		} else if ("mch_id".equals(name)) {
			unifiedOrder.setMchId(text);
		} else if ("device_info".equals(name)) {
			unifiedOrder.setDeviceInfo(text);
		} else if ("nonce_str".equals(name)) {
			unifiedOrder.setNonceStr(text);
		} else if ("sign".equals(name)) {
			unifiedOrder.setSign(text);
		} else if ("trade_type".equals(name)) {
			unifiedOrder.setTradeType(text);
		} else if ("prepay_id".equals(name)) {
			unifiedOrder.setPrePayId(text);
		} else if ("code_url".equals(name)) {
			unifiedOrder.setCodeUrl(text);
		}
	}

	public UnifiedOrder getUnifiedOrder() {
		return unifiedOrder;
	}

	public void setUnifiedOrder(UnifiedOrder unifiedOrder) {
		this.unifiedOrder = unifiedOrder;
	}

}
