package com.papa2.client.account.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.account.IAccountService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.annotation.ActionMonitor;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.struts.annotations.JsonResult;

/**
 * 账户管理.
 * 
 * @author xujiakun
 * 
 */
public class AccountAction extends BaseAction {

	private static final long serialVersionUID = -618238287549084080L;

	private static final String GOTO = "goto";

	private IAccountService accountService;

	private String passport;

	private String password;

	/**
	 * 原密码.
	 */
	private String oldPassword;

	/**
	 * 验证码.
	 */
	private String checkCode;

	/**
	 * ajax 返回.
	 */
	private String message;

	// >>>>>>>>>>以下是忘记密码<<<<<<<<<<

	/**
	 * 忘记密码.
	 * 
	 * @return
	 */
	public String forgetPassword() {
		setAttribute();

		return SUCCESS;
	}

	/**
	 * 找回登录密码 发送验证码.
	 * 
	 * @return
	 */
	@JsonResult(field = "message")
	public String sendCheckCode() {
		this.getServletResponse().setStatus(500);

		BooleanResult result = null;

		result = accountService.generateCheckCode(passport);

		if (result.getResult()) {
			this.getServletResponse().setStatus(200);
		}

		message = result.getCode();

		return JSON_RESULT;
	}

	@ActionMonitor(actionName = "密码重置")
	public String setPassword() {

		BooleanResult result = accountService.setPassword(password, checkCode);

		if (result.getResult()) {
			this.setSuccessMessage("成功修改密码！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	private void setAttribute() {
		// if 存在 goto then
		HttpServletRequest request = getServletRequest();
		String url = request.getParameter(GOTO);
		if (StringUtils.isNotBlank(url)) {
			request.setAttribute(GOTO, url.trim());
		}
	}

	// >>>>>>>>>>以下是修改密码<<<<<<<<<<

	/**
	 * 修改密码.
	 * 
	 * @return
	 */
	public String resetPassword() {
		return SUCCESS;
	}

	@ActionMonitor(actionName = "密码修改")
	public String renewPassword() {
		this.getServletResponse().setStatus(500);

		// 验证用户是否登陆
		User user = this.getUser();
		if (user == null) {
			message = "login";
			return JSON_RESULT;
		}

		BooleanResult result = accountService.resetPassword(user.getUserId(), password, oldPassword);

		if (result.getResult()) {
			this.getServletResponse().setStatus(200);
		}

		message = result.getCode();

		return JSON_RESULT;
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
