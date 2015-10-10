package com.papa2.client.wxpay.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.wxpay.IUnifiedOrderService;
import com.papa2.client.api.wxpay.bo.UnifiedOrder;
import com.papa2.client.api.wxpay.bo.UnifiedOrderReturn;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;
import com.papa2.client.framework.util.HttpUtil;
import com.papa2.client.framework.util.UUIDUtil;
import com.papa2.client.framework.util.XmlUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UnifiedOrderServiceImpl implements IUnifiedOrderService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(UnifiedOrderServiceImpl.class);

	private String key;

	@Override
	public String unifiedOrder(UnifiedOrder unifiedOrder, String modifyUser) {
		if (unifiedOrder == null) {
			throw new ServiceException("");
		}

		unifiedOrder.setNonceStr(UUIDUtil.generate());

		StringBuilder sign = new StringBuilder();

		sign.append("appid=").append(unifiedOrder.getAppId());
		if (StringUtils.isNotBlank(unifiedOrder.getAttach())) {
			sign.append("&attach=").append(unifiedOrder.getAttach());
		}
		sign.append("&body=").append(unifiedOrder.getBody());
		if (StringUtils.isNotBlank(unifiedOrder.getDetail())) {
			sign.append("&detail=").append(unifiedOrder.getDetail());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getDeviceInfo())) {
			sign.append("&device_info=").append(unifiedOrder.getDeviceInfo());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getFeeType())) {
			sign.append("&fee_type=").append(unifiedOrder.getFeeType());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getGoodsTag())) {
			sign.append("&goods_tag=").append(unifiedOrder.getGoodsTag());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getLimitPay())) {
			sign.append("&limit_pay=").append(unifiedOrder.getLimitPay());
		}
		sign.append("&mch_id=").append(unifiedOrder.getMchId());
		sign.append("&nonce_str=").append(unifiedOrder.getNonceStr());
		sign.append("&notify_url=").append(unifiedOrder.getNotifyUrl());
		if (StringUtils.isNotBlank(unifiedOrder.getOpenId())) {
			sign.append("&openid=").append(unifiedOrder.getOpenId());
		}
		sign.append("&out_trade_no=").append(unifiedOrder.getOutTradeNo());
		if (StringUtils.isNotBlank(unifiedOrder.getProductId())) {
			sign.append("&product_id=").append(unifiedOrder.getProductId());
		}
		sign.append("&spbill_create_ip=").append(unifiedOrder.getSpbillCreateIp());
		if (StringUtils.isNotBlank(unifiedOrder.getTimeExpire())) {
			sign.append("&time_expire=").append(unifiedOrder.getTimeExpire());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getTimeStart())) {
			sign.append("&time_start=").append(unifiedOrder.getTimeStart());
		}
		sign.append("&total_fee=").append(unifiedOrder.getTotalFee());
		sign.append("&trade_type=").append(unifiedOrder.getTradeType());

		sign.append("&key=").append(key);

		try {
			unifiedOrder.setSign(EncryptUtil.encryptMD5(sign.toString()).toUpperCase());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("	<appid>" + unifiedOrder.getAppId() + "</appid>");
		if (StringUtils.isNotBlank(unifiedOrder.getAttach())) {
			sb.append("	<attach>" + unifiedOrder.getAttach() + "</attach>");
		}
		sb.append("	<body>" + unifiedOrder.getBody() + "</body>");
		if (StringUtils.isNotBlank(unifiedOrder.getDetail())) {
			sb.append("	<detail>" + unifiedOrder.getDetail() + "</detail>");
		}
		if (StringUtils.isNotBlank(unifiedOrder.getDeviceInfo())) {
			sb.append(" <device_info>" + unifiedOrder.getDeviceInfo() + "</device_info>");
		}
		if (StringUtils.isNotBlank(unifiedOrder.getFeeType())) {
			sb.append("	<fee_type>" + unifiedOrder.getFeeType() + "</fee_type>");
		}
		if (StringUtils.isNotBlank(unifiedOrder.getGoodsTag())) {
			sb.append("	<goods_tag>" + unifiedOrder.getGoodsTag() + "</goods_tag>");
		}
		if (StringUtils.isNotBlank(unifiedOrder.getLimitPay())) {
			sb.append("	<limit_pay>" + unifiedOrder.getLimitPay() + "</limit_pay>");
		}
		sb.append("	<mch_id>" + unifiedOrder.getMchId() + "</mch_id>");
		sb.append("	<nonce_str>" + unifiedOrder.getNonceStr() + "</nonce_str>");
		sb.append(" <notify_url>" + unifiedOrder.getNotifyUrl() + "</notify_url>");
		if (StringUtils.isNotBlank(unifiedOrder.getOpenId())) {
			sb.append(" <openid>" + unifiedOrder.getOpenId() + "</openid>");
		}
		sb.append(" <out_trade_no>" + unifiedOrder.getOutTradeNo() + "</out_trade_no>");
		if (StringUtils.isNotBlank(unifiedOrder.getProductId())) {
			sb.append(" <product_id>" + unifiedOrder.getProductId() + "</product_id>");
		}
		sb.append(" <spbill_create_ip>" + unifiedOrder.getSpbillCreateIp() + "</spbill_create_ip>");
		if (StringUtils.isNotBlank(unifiedOrder.getTimeExpire())) {
			sb.append(" <time_expire>" + unifiedOrder.getTimeExpire() + "</time_expire>");
		}
		if (StringUtils.isNotBlank(unifiedOrder.getTimeStart())) {
			sb.append(" <time_start>" + unifiedOrder.getTimeStart() + "</time_start>");
		}
		sb.append(" <total_fee>" + unifiedOrder.getTotalFee() + "</total_fee>");
		sb.append(" <trade_type>" + unifiedOrder.getTradeType() + "</trade_type>");
		sb.append(" <sign>" + unifiedOrder.getSign() + "</sign>");
		sb.append("</xml>");

		String result = null;

		try {
			result = HttpUtil.post(IUnifiedOrderService.HTTPS_UNIFIED_ORDER_URL, sb.toString());
		} catch (Exception e) {
			logger.error(sb.toString(), e);
		}

		if (StringUtils.isEmpty(result)) {
			throw new ServiceException("统一下单失败.");
		}

		try {
			result = new String(result.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(result, e);

			throw new ServiceException("统一下单失败.");
		}

		UnifiedOrderReturn ret = (UnifiedOrderReturn) XmlUtil.parse(result, new UnifiedOrderReturn());

		if (ret == null) {
			throw new ServiceException("统一下单失败.");
		}

		// 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		if ("FAIL".equals(ret.getReturnCode())) {
			throw new ServiceException(ret.getReturnMsg());
		}

		// 验证返回签名
		if (!validate(ret)) {
			throw new ServiceException("接口返回签名验证失败.");
		}

		if ("FAIL".equals(ret.getResultCode())) {
			throw new ServiceException(ret.getErrCode() + "|" + ret.getErrCodeDes());
		}

		return ret.getUnifiedOrder().getPrePayId();
	}

	private boolean validate(UnifiedOrderReturn ret) {
		UnifiedOrder unifiedOrder = ret.getUnifiedOrder();

		StringBuilder sign = new StringBuilder();

		if (StringUtils.isNotBlank(unifiedOrder.getAppId())) {
			sign.append("&appid=").append(unifiedOrder.getAppId());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getCodeUrl())) {
			sign.append("&code_url=").append(unifiedOrder.getCodeUrl());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getDeviceInfo())) {
			sign.append("&device_info=").append(unifiedOrder.getDeviceInfo());
		}
		if (StringUtils.isNotBlank(ret.getErrCode())) {
			sign.append("&err_code=").append(ret.getErrCode());
		}
		if (StringUtils.isNotBlank(ret.getErrCodeDes())) {
			sign.append("&err_code_des=").append(ret.getErrCodeDes());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getMchId())) {
			sign.append("&mch_id=").append(unifiedOrder.getMchId());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getNonceStr())) {
			sign.append("&nonce_str=").append(unifiedOrder.getNonceStr());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getPrePayId())) {
			sign.append("&prepay_id=").append(unifiedOrder.getPrePayId());
		}
		if (StringUtils.isNotBlank(ret.getResultCode())) {
			sign.append("&result_code=").append(ret.getResultCode());
		}
		if (StringUtils.isNotBlank(ret.getReturnCode())) {
			sign.append("&return_code=").append(ret.getReturnCode());
		}
		if (StringUtils.isNotBlank(ret.getReturnMsg())) {
			sign.append("&return_msg=").append(ret.getReturnMsg());
		}
		if (StringUtils.isNotBlank(unifiedOrder.getTradeType())) {
			sign.append("&trade_type=").append(unifiedOrder.getTradeType());
		}

		sign.append("&key=").append(key);

		try {
			if (unifiedOrder.getSign().equals(EncryptUtil.encryptMD5(sign.substring(1).toString()).toUpperCase())) {
				return true;
			}
		} catch (IOException e) {
			logger.error(e);
		}

		return false;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
