package com.papa2.client.sms.dao;

import com.papa2.client.api.sms.bo.SMS;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISMSDao {

	/**
	 * 保存发送短信.
	 * 
	 * @param sms
	 * @return
	 */
	Long createSMS(SMS sms);

}
