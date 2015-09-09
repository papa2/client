package com.papa2.client.api.pay;

import com.papa2.client.api.alipay.bo.AliCallback;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface ICallbackService {

	/**
	 * 创建支付回调信息.
	 * 
	 * @param callback
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createAliCallback(AliCallback callback, String modifyUser);

}
