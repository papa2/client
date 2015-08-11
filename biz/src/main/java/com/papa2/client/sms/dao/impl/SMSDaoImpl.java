package com.papa2.client.sms.dao.impl;

import com.papa2.client.api.sms.bo.SMS;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.sms.dao.ISMSDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class SMSDaoImpl extends BaseDaoImpl implements ISMSDao {

	@Override
	public Long createSMS(SMS sms) {
		return (Long) getSqlMapClientTemplate().insert("sms.createSMS", sms);
	}

}
