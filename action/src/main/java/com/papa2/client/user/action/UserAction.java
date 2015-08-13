package com.papa2.client.user.action;

import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 3114603829318433575L;

	private IClientUserService clientUserService;

	private User user;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		user = clientUserService.getUser(super.getUser().getPassport());

		return SUCCESS;
	}

	public String update() {
		String passport = super.getUser().getPassport();

		BooleanResult result = clientUserService.updateUser(passport, user, passport);

		if (result.getResult()) {
			this.setSuccessMessage("用户信息修改成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public IClientUserService getClientUserService() {
		return clientUserService;
	}

	public void setClientUserService(IClientUserService clientUserService) {
		this.clientUserService = clientUserService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
