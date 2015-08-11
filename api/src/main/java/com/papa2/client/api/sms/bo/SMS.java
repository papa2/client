package com.papa2.client.api.sms.bo;

/**
 * 短信.
 * 
 * @author xujiakun
 * 
 */
public class SMS {

	private Long id;

	/**
	 * 短信发送方.
	 */
	private String sender;

	/**
	 * 短信接受方.
	 */
	private String receiver;

	/**
	 * 短信内容.
	 */
	private String content;

	/**
	 * 操作人.
	 */
	private String modifyUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
