package com.papa2.client.cases.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.cases.ICaseService;
import com.papa2.client.api.cases.bo.Case;
import com.papa2.client.cases.dao.ICaseDao;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class CaseServiceImpl implements ICaseService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CaseServiceImpl.class);

	private ICaseDao caseDao;

	@Override
	public List<Case> getCaseList(String backCode) {
		return getCaseList(backCode, null);
	}

	@Override
	public List<Case> getCaseList(String backCode, String type) {
		Case cases = new Case();

		if (StringUtils.isBlank(backCode)) {
			return null;
		}

		cases.setBackCode(backCode.trim());

		if (StringUtils.isNotBlank(type)) {
			cases.setType(type.trim());
		}

		try {
			return caseDao.getCaseList(cases);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cases), e);
		}

		return null;
	}

	@Override
	public Case getCase(Long caseId) {
		if (caseId == null) {
			return null;
		}

		Case cases = new Case();
		cases.setCaseId(caseId);

		try {
			return caseDao.getCase(cases);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cases), e);
		}

		return null;
	}

	public ICaseDao getCaseDao() {
		return caseDao;
	}

	public void setCaseDao(ICaseDao caseDao) {
		this.caseDao = caseDao;
	}

}
