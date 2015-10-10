package com.papa2.client.api.wxpay;

import com.papa2.client.api.wxpay.bo.UnifiedOrder;
import com.papa2.client.framework.exception.ServiceException;

/**
 * 统一下单.
 * 
 * @author JiakunXu
 * 
 */
public interface IUnifiedOrderService {

	String HTTPS_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 
	 * @param unifiedOrder
	 * @param modifyUser
	 * @return
	 * @throws ServiceException
	 */
	String unifiedOrder(UnifiedOrder unifiedOrder, String modifyUser) throws ServiceException;;

}
