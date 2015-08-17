package com.papa2.client.cases.dao;

import java.util.List;

import com.papa2.client.api.cases.bo.Case;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICaseDao {

	/**
	 * 
	 * @param cases
	 * @return
	 */
	List<Case> getCaseList(Case cases);

	/**
	 * 
	 * @param cases
	 * @return
	 */
	Case getCase(Case cases);

}
