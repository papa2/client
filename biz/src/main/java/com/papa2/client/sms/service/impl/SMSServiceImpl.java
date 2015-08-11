package com.papa2.client.sms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.papa2.client.api.sms.ISMSService;
import com.papa2.client.api.sms.bo.SMS;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.sms.dao.ISMSDao;

/**
 * SMS.
 * 
 * @author xujiakuin
 * 
 */
public class SMSServiceImpl implements ISMSService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SMSServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private ISMSDao smsDao;

	@Override
	public BooleanResult send(String sender, String receiver, String content, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		final SMS sms = new SMS();

		if (StringUtils.isBlank(sender)) {
			result.setCode("短信发送方信息不能为空！");
			return result;
		}

		sms.setSender(sender.trim());

		if (StringUtils.isBlank(receiver)) {
			result.setCode("短信接受方信息不能为空！");
			return result;
		}

		sms.setReceiver(receiver.trim());

		if (StringUtils.isBlank(content)) {
			result.setCode("短信内容信息不能为空！");
			return result;
		}

		sms.setContent(content.trim());

		if (StringUtils.isBlank(modifyUser)) {
			result.setCode("操作人信息不能为空！");
			return result;
		}

		sms.setModifyUser(modifyUser);

		BooleanResult res = transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				result.setResult(false);

				// 1. 短信记录
				try {
					smsDao.createSMS(sms);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(sms), e);
					ts.setRollbackOnly();

					result.setCode("短信记录写入失败！");
					return result;
				}

				// 2. 短信发送
				if (!send(sms.getReceiver(), sms.getContent())) {
					ts.setRollbackOnly();

					result.setCode("短信发送失败！");
					return result;
				}

				result.setCode("短信发送成功！");
				result.setResult(true);
				return result;
			}
		});

		return res;
	}

	private boolean send(String receiver, String content) {
		return true;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ISMSDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(ISMSDao smsDao) {
		this.smsDao = smsDao;
	}

}
