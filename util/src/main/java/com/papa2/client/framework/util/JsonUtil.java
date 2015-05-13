package com.papa2.client.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import ognl.OgnlOps;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork.util.OgnlUtil;

/**
 * java对象与json字符串互转的工具类.
 * 
 * @author cuican.dingcc
 * 
 */

public final class JsonUtil {

	private static Logger log = Logger.getLogger(JsonUtil.class);

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private static final String ERROR_MESSAGE = "日期格式错误!";

	private JsonUtil() {

	}

	/**
	 * 将json字符串转成javabean.
	 * 
	 * @param jsonStr
	 *            要转换的json字符串
	 * @param toClass
	 *            要转化到的类型
	 * @param childMap
	 *            该类型中集合字段的字段名和集合元素类型map
	 * @return Object
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	public static Object json2Bean(Class toClass, String jsonStr, Map<String, Class> childMap) throws Exception {
		if (toClass == null) {
			return null;
		}

		String jsonValue = jsonStr;

		try {
			jsonValue = URLDecoder.decode(jsonValue, "utf-8");
		} catch (Exception e) {
			throw new Exception("编码格式错误！", e);
		}
		JSONObject object = null;
		try {
			object = new JSONObject(jsonValue);
		} catch (Exception e) {
			throw new Exception("json格式错误！", e);
		}
		Object obj = toClass.newInstance();

		Map<String, Object> sourceMap = toMap(object);
		PropertyDescriptor[] props = OgnlUtil.getPropertyDescriptors(obj);
		Map<String, Object> map = new HashMap<String, Object>();
		// 参数不区分大小写
		for (PropertyDescriptor desc : props) {
			Set entrys = sourceMap.entrySet();
			boolean contains = false;
			for (Iterator i = entrys.iterator(); i.hasNext();) {
				Entry entry = (Entry) i.next();
				if (((String) entry.getKey()).equalsIgnoreCase(desc.getName())) {
					map.put(desc.getName(), entry.getValue());
					contains = true;
					break;
				}
			}

			if (contains) {
				Object value = null;
				// 处理日期型
				// "yyyy-MM-dd'T'HH:mm:ss'Z'"此格式是由json2.js转换后的Date格式
				if (java.util.Date.class.isAssignableFrom(desc.getPropertyType())) {
					String date = (String) map.get(desc.getName());
					if (!validateDate(date)) {
						throw new Exception(desc.getName() + ERROR_MESSAGE);
					}
					value = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim());
				} else if (java.sql.Date.class.isAssignableFrom(desc.getPropertyType())) {
					String date = (String) map.get(desc.getName());
					if (!validateDate(date)) {
						throw new Exception(desc.getName() + ERROR_MESSAGE);
					}
					value = new java.sql.Date(new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim()).getTime());
				} else if (Collection.class.isAssignableFrom(desc.getPropertyType())) {
					Class clz = childMap.get(desc.getName());
					if (clz.isPrimitive() || CharSequence.class.isAssignableFrom(clz)
						|| Number.class.isAssignableFrom(clz)) {
						value = convertFromStringListSimple(map.get(desc.getName()).toString(), clz);
					} else {
						value = convertFromStringListComplex(map.get(desc.getName()).toString(), clz);
					}
				} else {

					if (desc.getPropertyType().isPrimitive()
						|| CharSequence.class.isAssignableFrom(desc.getPropertyType())
						|| Number.class.isAssignableFrom(desc.getPropertyType())) {
						try {
							value = OgnlOps.convertValue(map.get(desc.getName()), desc.getPropertyType());
						} catch (NumberFormatException e) {
							Class clz = desc.getPropertyType();
							if (clz == Integer.TYPE || clz == Short.TYPE || clz == Long.TYPE || clz == BigInteger.class) {
								throw new Exception(desc.getName() + "应为整数!", e);
							} else if (clz == Double.TYPE || clz == Float.TYPE || clz == BigDecimal.class) {
								throw new Exception(desc.getName() + "应为数字!", e);
							}
						}
					} else {
						value = json2Bean(desc.getPropertyType(), map.get(desc.getName()).toString(), null);
					}

				}
				desc.getWriteMethod().invoke(obj, new Object[] { value });
			}
		}
		return obj;
	}

	public static boolean validateDate(String date) {
		if (date == null || date.trim().length() != 10) {
			return false;
		} else {
			String[] param = (date.trim()).split("-");
			if (param[0].length() != 4) {
				return false;
			}
			int month = Integer.parseInt(param[1]);
			if (month < 1 || month > 12) {
				return false;
			}
			int day = Integer.parseInt(param[2]);
			if (day < 1 || day > 31) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	private static Object convertFromStringListSimple(String jsonValue, Class toClass) throws Exception {
		if (toClass == null) {
			return null;
		}
		ArrayList<Object> list = new ArrayList<Object>();
		JSONArray array = new JSONArray(jsonValue);
		int i;
		Object value = null;
		for (i = 0; i < array.length(); i++) {
			Object property = array.get(i);
			if (java.util.Date.class.isAssignableFrom(toClass)) {
				String date = (String) property;
				if (!validateDate(date)) {
					throw new Exception(ERROR_MESSAGE);
				}
				value = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim());
			} else if (java.sql.Date.class.isAssignableFrom(toClass)) {
				String date = (String) property;
				if (!validateDate(date)) {
					throw new Exception(ERROR_MESSAGE);
				}
				value = new java.sql.Date(new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim()).getTime());
			} else {
				value = OgnlOps.convertValue(property, toClass);
			}
			list.add(value);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	private static Object convertFromStringListComplex(String jsonValue, Class toClass) throws Exception {
		if (toClass == null) {
			return null;
		}
		ArrayList<Object> list = new ArrayList<Object>();
		JSONArray array = new JSONArray(jsonValue);
		for (int i = 0; i < array.length(); i++) {
			Object obj = toClass.newInstance();
			Map map = toMap(array.getJSONObject(i));
			PropertyDescriptor[] props = OgnlUtil.getPropertyDescriptors(obj);
			for (PropertyDescriptor desc : props) {
				if (map.containsKey(desc.getName())) {
					Object value = null;
					// 处理日期型
					// "yyyy-MM-dd'T'HH:mm:ss'Z'"此格式是由json2.js转换后的Date格式
					if (java.util.Date.class.isAssignableFrom(desc.getPropertyType())) {
						String date = (String) map.get(desc.getName());
						if (!validateDate(date)) {
							throw new Exception(desc.getName() + ERROR_MESSAGE);
						}
						value = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim());
					} else if (java.sql.Date.class.isAssignableFrom(desc.getPropertyType())) {
						String date = (String) map.get(desc.getName());
						if (!validateDate(date)) {
							throw new Exception(desc.getName() + ERROR_MESSAGE);
						}
						value =
							new java.sql.Date(new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date.trim()).getTime());
					} else {
						try {
							value = OgnlOps.convertValue(map.get(desc.getName()), desc.getPropertyType());
						} catch (NumberFormatException e) {
							Class clz = desc.getPropertyType();
							if (clz == Integer.class || clz == Short.class || clz == Long.class
								|| clz == BigInteger.class) {
								throw new Exception(desc.getName() + "应为整数!", e);
							} else if (clz == Double.class || clz == Float.class || clz == BigDecimal.class) {
								throw new Exception(desc.getName() + "应为数字!", e);
							}
						}

					}
					desc.getWriteMethod().invoke(obj, new Object[] { value });
				}
			}
			list.add(obj);
		}
		return list;
	}

	private static Map<String, Object> toMap(JSONObject object) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Iterator iter = object.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object val = object.get(key);
			if (val != null && !"".equals(val.toString().trim())) {
				map.put(key, val);
			}
		}
		return map;
	}

	/**
	 * 将javabean转成json字符串.
	 * 
	 * @param cls
	 *            要转的javabean类型
	 * @param value
	 *            要转的javabean
	 * @return String
	 * 
	 */
	public static String bean2Json(Class<?> cls, Object value) {
		try {
			return transToJSONObject(cls, value).toString();
		} catch (Exception e) {
			log.error("转换jsonObject失败", e);
			return null;
		}
	}

	// 将value转换成JSONObject
	@SuppressWarnings("rawtypes")
	private static Object transToJSONObject(Class<?> cls, Object value) throws Exception {
		Object json = null;
		if (value != null) {
			if (Collection.class.isAssignableFrom(cls)) {
				// 列表s
				Iterator iter = ((Collection) value).iterator();
				JSONArray array = new JSONArray();
				while (iter.hasNext()) {
					Object element = iter.next();
					if (element != null) {
						array.put(transToJSONObject(element.getClass(), element));
					}
				}
				json = array;
			} else if (cls.isArray()) {
				// 数组
				JSONArray array = new JSONArray();
				int length = Array.getLength(value);
				for (int i = 0; i < length; i++) {
					Object element = Array.get(value, i);
					if (element != null) {
						array.put(transToJSONObject(element.getClass(), element));
					}
				}
				json = array;
			} else if (Map.class.isAssignableFrom(cls)) {
				// 映射
				Iterator iter = ((Map) value).entrySet().iterator();
				JSONObject object = new JSONObject();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object v = entry.getValue();
					if (v != null) {
						object.put(entry.getKey().toString(), transToJSONObject(v.getClass(), v));
					}
				}
				json = object;
			} else if (cls.isPrimitive() || CharSequence.class.isAssignableFrom(cls)
				|| Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
				// 基本类型、数值或字符串
				if (cls == Boolean.TYPE || cls == Integer.TYPE || cls == Float.TYPE || cls == Double.TYPE
					|| cls == Long.TYPE || cls == Short.TYPE || cls == Byte.TYPE || cls.isAssignableFrom(Number.class)) {
					json = value;
				} else {
					json = value.toString();
				}
			} else if (java.util.Date.class.isAssignableFrom(cls) || java.sql.Date.class.isAssignableFrom(cls)) {
				json = value.toString();
			} else {
				// 对象
				Map map = OgnlUtil.getBeanMap(value);
				JSONObject object = new JSONObject();
				if (map != null) {
					Iterator iter = map.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry element = (Map.Entry) iter.next();
						Object v = element.getValue();
						if (v != null) {
							object.put((String) element.getKey(), transToJSONObject(v.getClass(), v));
						}
					}
					json = object;
				}
			}
		}
		return json == null ? "" : json;
	}

}
