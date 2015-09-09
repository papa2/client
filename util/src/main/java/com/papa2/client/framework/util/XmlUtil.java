package com.papa2.client.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Visitor;

/**
 * 
 * @author JiakunXu
 * 
 */
public final class XmlUtil {

	static Logger logger = Logger.getLogger(XmlUtil.class);

	private XmlUtil() {

	}

	public static Object parse(String str, Object object) {
		if (StringUtils.isBlank(str) || object == null) {
			return null;
		}

		Document document = null;

		try {
			document = DocumentHelper.parseText(str.trim());
		} catch (DocumentException e) {
			logger.error(e);

			return object;
		}

		document.accept((Visitor) object);

		return object;
	}

}
