package com.papa2.client.api.cases.bo;

import java.io.Serializable;

/**
 * 社区 公共停车场.
 * 
 * @author xujiakun
 * 
 */
public class Case implements Serializable {

	private static final long serialVersionUID = 2932879228480807674L;

	private Long caseId;

	private String caseName;

	/**
	 * 省.
	 */
	private String province;

	/**
	 * 市.
	 */
	private String city;

	/**
	 * 区.
	 */
	private String area;

	private String address;

	/**
	 * 邮编.
	 */
	private String postalCode;

	private String backCode;

	/**
	 * 是否开放车位.
	 */
	private String type;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
