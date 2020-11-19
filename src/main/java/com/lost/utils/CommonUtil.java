package com.lost.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CommonUtil {

	public static String nullToEmpty(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();

	}

	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().length() == 0 || "null".equals(s)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Long o) {
		if (o == null || o <= 0L) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(String s) {
		return !isNullOrEmpty(s);
	}

	public static boolean isNotEmpty(String s) {
		return !isNullOrEmpty(s);
	}

	public static boolean isNotEmpty(Collection c) {
		return !isNullOrEmpty(c);
	}

	public static boolean isNullOrEmpty(Collection c) {
		if (c == null || c.size() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(List l) {
		return !isNullOrEmpty(l);
	}

	public static boolean isNullOrEmpty(List l) {
		if (l == null || l.size() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Map map) {
		return !isNullOrEmpty(map);
	}

	public static boolean isNullOrEmpty(Map map) {
		if (map == null || map.size() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isNullOrEmpty(obj);
	}

	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	// 得到一个随机数，位数不超过index
	public static String getRandom(int index) {
		String random = String.valueOf(System.currentTimeMillis());
		if (random.length() > index) {
			random = random.substring(random.length() - index);
		}
		return random;
	}

	// 根据List 中的ID 拼接成以,而的字符串
	public static String parseListToString(List<String> idsList) {
		if (CommonUtil.isNullOrEmpty(idsList)) {
			return "";
		}
		StringBuilder ids = new StringBuilder(200);
		for (String s : idsList) {
			ids.append(s + ",");
		}
		ids.deleteCharAt(ids.length() - 1);
		return ids.toString();
	}

	// 根据List 中的id 拼接成以,而的字符串
	public static String parseListMapToString(List idsList) {
		if (CommonUtil.isNullOrEmpty(idsList)) {
			return "";
		}
		StringBuilder ids = new StringBuilder(200);
		Map map = null;
		for (int i = 0; i < idsList.size(); i++) {
			map = (Map) idsList.get(i);
			ids.append(map.get("id") + ",");
		}
		ids.deleteCharAt(ids.length() - 1);
		return ids.toString();
	}

	// 将list中的id 以逗号拼结起来，每个拼接的串所用项不能超过count个数
	public static String[] splitString(List<String> ids, int count) {
		if (CommonUtil.isNullOrEmpty(ids)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		StringBuilder strBuf = new StringBuilder(500);
		int j = 0;
		for (int i = 0; i < ids.size(); i++) {
			strBuf.append(ids.get(i) + ",");
			j++;
			if (j == count) {
				j = 0;
				list.add(strBuf.deleteCharAt(strBuf.length() - 1).toString());
				strBuf.delete(0, strBuf.length());
			}
		}
		if (strBuf.length() > 0) {
			list.add(strBuf.deleteCharAt(strBuf.length() - 1).toString());
		}
		return list.toArray(new String[] {});

	}

	public static List<String> parseListMapToList(List dataList) {
		List<String> idsList = new ArrayList<String>();
		if (CommonUtil.isNullOrEmpty(dataList)) {
			return idsList;
		}
		Map map = null;
		for (int i = 0; i < dataList.size(); i++) {
			map = (Map) dataList.get(i);
			idsList.add(map.get("id") + "");
		}
		return idsList;
	}

	public static String joinString(Object[] value) {
		if (value == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (Object obj : value) {
			result.append(nullToEmpty(obj)).append(",");
		}
		if (StringUtils.isNotBlank(result.toString())) {
			return result.substring(0, result.length() - 1);
		}
		return "";
	}

	public static String getListItemValue(List list, int index) {
		if (isNotEmpty(list)) {
			if (index < list.size()) {
				return nullToEmpty(list.get(index));
			}
		}
		return "";
	}

	public static String getStandardString(String content) {
		StringBuffer stringBuffer = new StringBuffer(500);
		if (CommonUtil.isNotEmpty(content)) {
			int count = 0;
			count = content.length() % 47 == 0 ? content.length() / 47 : (content.length() / 47 + 1);
			if (count != 0) {
				for (int i = 0; i < count; i++) {
					int length = 47 * (i + 1) > content.length() ? content.length() : 47 * (i + 1);
					stringBuffer.append(content.substring((47 * i), length));
					stringBuffer.append("<br>");
				}
			} else {
				stringBuffer.append(content);
			}
		}
		return stringBuffer.toString();
	}
}
