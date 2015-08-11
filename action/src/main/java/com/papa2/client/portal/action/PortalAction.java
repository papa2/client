package com.papa2.client.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.action.BaseAction;

/**
 * 
 * @author xujiakun
 * 
 */
public class PortalAction extends BaseAction {

	private static final long serialVersionUID = 2191525146456353749L;

	private static final String GOTO = "goto";

	/**
	 * 登录首页.
	 * 
	 * @return
	 */
	public String index() {
		User user = getUser();
		if (user != null) {
			setAttribute(this.getSession());
		} else {
			setAttribute();
		}

		return SUCCESS;
	}

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String boss() {
		return SUCCESS;
	}

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String client() {
		return SUCCESS;
	}

	private void setAttribute(HttpSession session) {
		// if 存在 goto then
		HttpServletRequest request = getServletRequest();
		String url = request.getParameter(GOTO);
		if (StringUtils.isNotBlank(url)) {
			session.setAttribute(GOTO, url.trim());
		}
	}

	private void setAttribute() {
		// if 存在 goto then
		HttpServletRequest request = getServletRequest();
		String url = request.getParameter(GOTO);
		if (StringUtils.isNotBlank(url)) {
			request.setAttribute(GOTO, url.trim());
		}
	}

}
