package com.papa2.client.api.cases;

import java.util.List;

import com.papa2.client.api.cases.bo.Case;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICaseService {

	/**
	 * 根据省市区查询 社区 公共停车场.
	 * 
	 * @param backCode
	 * @return
	 */
	List<Case> getCaseList(String backCode);

	/**
	 * 根据省市区查询 社区 公共停车场.
	 * 
	 * @param backCode
	 * @param type
	 *            是否开放车位.
	 * @return
	 */
	List<Case> getCaseList(String backCode, String type);

	/**
	 * 
	 * @param caseId
	 * @return
	 */
	Case getCase(Long caseId);

}
