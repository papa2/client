package com.papa2.client.api.wxap;

/**
 * 签名.
 * 
 * @author xujiakun
 * 
 */
public interface ISignatureService {

	/**
	 * 
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	String signature(String nonceStr, String timestamp, String url);

}
