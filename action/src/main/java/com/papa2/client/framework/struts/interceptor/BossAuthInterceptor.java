package com.papa2.client.framework.struts.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.papa2.client.api.ca.ICAService;
import com.papa2.client.api.user.bo.User;

/**
 * AuthencationInterceptor.
 * 
 * @author xujiakun
 * 
 */
public class BossAuthInterceptor implements Interceptor {

	private static final long serialVersionUID = -7498838714747075663L;

	private static final String LOGIN_TIMEOUT = "440";

	private static final String UNAUTHORIZED = "401";

	private ICAService caService;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get("ACEGI_SECURITY_LAST_LOGINUSER");

		if (user == null || !"BOSS".equals(user.getType())) {
			String actionName = getActionName();
			// 登录首页 不需要 goto
			if (!"/home.htm".equals(actionName)) {
				setAttribute("goto", getUrl());
			}

			return LOGIN_TIMEOUT;
		}

		if (validateRequest(user.getUserId())) {
			return invocation.invoke();
		} else {
			return UNAUTHORIZED;
		}
	}

	/**
	 * 验证当前Request是否有权限.
	 * 
	 * @return
	 */
	private boolean validateRequest(Long userId) {
		// if getActionUrl 由于menu数据量的问题，验证上存在漏洞 -> like
		String url = getActionName();
		if (StringUtils.isNotEmpty(url)) {
			// 1 判断actionName是否属于menu
			// 2 判断当前用户是否拥有该menu权限
			return caService.validateRequest(userId, url);
		}

		return false;
	}

	private String getUrl() {
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		// Map map = ctx.getSession()
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();

		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			return url.toString() + "?" + queryString;
		}

		return url.toString();
	}

	private void setAttribute(String name, Object o) {
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		// Map map = ctx.getSession()
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute(name, o);
	}

	/**
	 * actionName.
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		// Map map = ctx.getSession()
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath()) + request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

}
