package com.papa2.client.cases.dao.impl;

import java.util.List;

import com.papa2.client.api.cases.bo.Case;
import com.papa2.client.cases.dao.ICaseDao;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author xujiakun
 * 
 */
public class CaseDaoImpl extends BaseDaoImpl implements ICaseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Case> getCaseList(Case cases) {
		return getSqlMapClientTemplate().queryForList("case.getCaseList", cases);
	}

}
