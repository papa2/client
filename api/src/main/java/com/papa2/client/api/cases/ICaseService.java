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

}
