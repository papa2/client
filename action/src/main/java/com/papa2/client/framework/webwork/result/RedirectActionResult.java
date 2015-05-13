package com.papa2.client.framework.webwork.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.webwork.dispatcher.ServletRedirectResult;
import com.opensymphony.webwork.dispatcher.mapper.ActionMapper;
import com.opensymphony.webwork.dispatcher.mapper.ActionMapperFactory;
import com.opensymphony.webwork.dispatcher.mapper.ActionMapping;
import com.opensymphony.webwork.views.util.UrlHelper;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.config.entities.ResultConfig;
import com.papa2.client.framework.util.EncodeUtil;

/**
 * 
 * @author
 * 
 */
public class RedirectActionResult extends ServletRedirectResult {

	public static final String DEFAULT_PARAM = "actionName";

	private static final long serialVersionUID = 5419860595625790964L;

	protected String application;
	protected String namespace;
	protected String actionName;

	// modify by xujiakun 2013-10-26
	// protected String method = "execute"
	protected String method;

	@SuppressWarnings("rawtypes")
	protected List prohibitedResultParam = Arrays.asList(new String[] { DEFAULT_PARAM, "application", "namespace",
		"method", "encode", "parse", "location", "prependServletContext" });

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(ActionInvocation invocation) throws Exception {
		actionName = conditionalParse(actionName, invocation);

		if (namespace == null) {
			namespace = invocation.getProxy().getNamespace();
		} else {
			namespace = conditionalParse(namespace, invocation);
		}

		if (method == null) {
			method = "";
		} else {
			method = conditionalParse(method, invocation);
		}

		Map requestParameters = new HashMap();
		ResultConfig resultConfig =
			(ResultConfig) invocation.getProxy().getConfig().getResults().get(invocation.getResultCode());
		Map resultConfigParams = resultConfig.getParams();

		// 2012-2-10 modify by xujiakun add EncodeUtil.url
		for (Iterator i = resultConfigParams.entrySet().iterator(); i.hasNext();) {
			Map.Entry e = (Map.Entry) i.next();
			if (!prohibitedResultParam.contains(e.getKey())) {
				requestParameters.put(e.getKey().toString(),
					e.getValue() == null ? "" : EncodeUtil.url(conditionalParse(e.getValue().toString(), invocation)));
			}
		}

		ActionMapper mapper = ActionMapperFactory.getMapper();
		StringBuffer tmpLocation =
			new StringBuffer(mapper.getUriFromActionMapping(new ActionMapping(actionName, namespace, method, null)));
		UrlHelper.buildParametersString(requestParameters, tmpLocation, "&");

		if (application != null) {
			location = conditionalParse(application, invocation);
			if (tmpLocation.charAt(0) != '/') {
				location += '/';
			}

			location += tmpLocation.toString();
		} else {
			location = tmpLocation.toString();
		}

		super.execute(invocation);
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
