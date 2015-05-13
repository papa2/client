package com.papa2.client.framework.webwork.result;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.webwork.dispatcher.ServletRedirectResult;
import com.opensymphony.webwork.views.util.UrlHelper;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.config.entities.ActionConfig;
import com.opensymphony.xwork.config.entities.ResultConfig;

/**
 * 
 * @author
 * 
 */
public class RedirectResult extends ServletRedirectResult {

	private static final long serialVersionUID = 2474958981537264314L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(ActionInvocation invocation) throws Exception {
		location = conditionalParse(location, invocation);

		ActionConfig actionConfig = invocation.getProxy().getConfig();
		ResultConfig resultConfig = (ResultConfig) actionConfig.getResults().get(invocation.getResultCode());
		Map resultConfigParams = resultConfig.getParams();

		Map requestParameters = new HashMap();
		for (Iterator i = resultConfigParams.entrySet().iterator(); i.hasNext();) {
			Map.Entry e = (Map.Entry) i.next();

			if (!DEFAULT_PARAM.equals(e.getKey())) {
				requestParameters.put(e.getKey().toString(),
					e.getValue() == null ? "" : conditionalParse(e.getValue().toString(), invocation));
			}
		}

		if (requestParameters.size() > 0) {
			StringBuffer tmpLocation = new StringBuffer(location);
			UrlHelper.buildParametersString(requestParameters, tmpLocation, "&");

			location = tmpLocation.toString();
		}

		super.execute(invocation);
	}

}
