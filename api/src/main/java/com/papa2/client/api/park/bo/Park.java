package com.papa2.client.api.park.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 社区 公共停车场.
 * 
 * @author xujiakun
 * 
 */
public class Park implements Serializable {

	private static final long serialVersionUID = 2932879228480807674L;

	private Long parkId;

	private String parkName;

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
	 * 建议按时收费金额.
	 */
	private BigDecimal costHour;

	/**
	 * 建议包月收费金额.
	 */
	private BigDecimal costMonth;

	/**
	 * 是否开放车位.
	 */
	private String type;

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
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

	public BigDecimal getCostHour() {
		return costHour;
	}

	public void setCostHour(BigDecimal costHour) {
		this.costHour = costHour;
	}

	public BigDecimal getCostMonth() {
		return costMonth;
	}

	public void setCostMonth(BigDecimal costMonth) {
		this.costMonth = costMonth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
