package com.papa2.client.login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.ca.ICAService;
import com.papa2.client.api.ca.bo.ValidateResult;
import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.annotation.ActionMonitor;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;

/**
 * 登录模块.
 * 
 * @author xujiakun
 * 
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 7498561926934442624L;

	private static final String GOTO = "goto";

	private Logger4jExtend logger = Logger4jCollection.getLogger(LoginAction.class);

	private IMemcachedCacheService memcachedCacheService;

	private ICAService caService;

	private String passport;

	private String password;

	/**
	 * BOSS or CLIENT.
	 */
	private String type;

	private String url;

	/**
	 * 登录验证.
	 * 
	 * @return
	 */
	@ActionMonitor(actionName = "系统登录")
	public String login() {
		ValidateResult result = caService.validateUser(passport, password, type);

		// 验证失败
		if (ICAService.RESULT_FAILED.equals(result.getResultCode())
			|| ICAService.RESULT_ERROR.equals(result.getResultCode())) {
			this.setFailMessage(result.getMessage());

			setAttribute();

			return "incorrect";
		}

		// 验证通过
		User user = result.getUser();

		HttpSession session = this.getSession();
		session.setAttribute("ACEGI_SECURITY_LAST_PASSPORT", user.getPassport());
		session.setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", user);

		HttpServletResponse response = getServletResponse();
		if (response != null) {
			Cookie ps = new Cookie("PS", user.getPassport());
			// ps.setMaxAge(-1);
			ps.setPath("/");
			ps.setDomain(env.getProperty("domain"));
			response.addCookie(ps);
		}

		setAttribute(session);

		if ("BOSS".equals(type)) {
			return "boss";
		} else if ("CLIENT".equals(type)) {
			return "client";
		}

		return SUCCESS;
	}

	/**
	 * 退出系统.
	 * 
	 * @return
	 */
	@ActionMonitor(actionName = "系统退出")
	public String logout() {
		HttpSession session = this.getSession();

		// 清空cache中session信息
		try {
			memcachedCacheService.remove(session.getId());
		} catch (Exception e) {
			logger.error(e);
		}

		try {
			// login
			session.removeAttribute("ACEGI_SECURITY_LAST_PASSPORT");
			session.removeAttribute("ACEGI_SECURITY_LAST_LOGINUSER");

			session.invalidate();
		} catch (Exception e) {
			logger.error(e);
		}

		HttpServletResponse response = getServletResponse();
		if (response != null) {
			Cookie j = new Cookie("JSESSIONID", null);
			j.setMaxAge(0);
			j.setPath("/");
			response.addCookie(j);
		}

		return LOGOUT;
	}

	private void setAttribute(HttpSession session) {
		// if 存在 goto then
		HttpServletRequest request = getServletRequest();
		String u = request.getParameter(GOTO);
		if (StringUtils.isNotBlank(u)) {
			session.setAttribute(GOTO, u.trim());
		}
	}

	private void setAttribute() {
		// if 存在 goto then
		HttpServletRequest request = getServletRequest();
		String u = request.getParameter(GOTO);
		if (StringUtils.isNotBlank(u)) {
			request.setAttribute(GOTO, u.trim());
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
