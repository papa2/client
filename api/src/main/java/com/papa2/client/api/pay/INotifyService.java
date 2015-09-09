package com.papa2.client.api.pay;

import com.papa2.client.api.alipay.bo.AliNotify;
import com.papa2.client.api.wxap.bo.WxNotify;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface INotifyService {

	/**
	 * 创建支付通知信息.
	 * 
	 * @param notify
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createAliNotify(AliNotify notify, String modifyUser);

	/**
	 * 创建支付通知信息.
	 * 
	 * @param notify
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createWxNotify(WxNotify notify, String modifyUser);

}
