package com.lost.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 字符集工具类
 */
public class CharsetKit {
	/** ISO-8859-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";
	/** UTF-8 */
	public static final String UTF_8 = "UTF-8";
	/** GBK */
	public static final String GBK = "GBK";

	/** ISO-8859-1 */
	public static final Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);
	/** UTF-8 */
	public static final Charset CHARSET_UTF_8 = Charset.forName(UTF_8);
	/** GBK */
	public static final Charset CHARSET_GBK = Charset.forName(GBK);

	public static String[] SpecialChar = { "'", "\"", "[", "]" };
	private static List<String> formerCharacterList = Arrays
			.asList(new String[] { "\"", "'", "<", ">", "%", "+", "(", ")" });
	private static List<String> escapeCharacterList = Arrays
			.asList(new String[] { "&#34;", "&#39;", "&#60;", "&#62;", "&#37;", "&#43;", "&#40;", "&#41;" });

	private static List<String> formerCharacterList2 = Arrays.asList(new String[] { "&", ">", "<", "\"", "'" });
	private static List<String> escapeCharacterList2 = Arrays
			.asList(new String[] { "&amp;", "&gt;", "&lt;", "&quot;", "&apos;" });

	/**
	 * 转换为Charset对象
	 * 
	 * @param charset 字符集，为空则返回默认字符集
	 * @return Charset
	 */
	public static Charset charset(String charset) {
		return StringUtils.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset);
	}

	/**
	 * 转换字符串的字符集编码
	 * 
	 * @param source      字符串
	 * @param srcCharset  源字符集，默认ISO-8859-1
	 * @param destCharset 目标字符集，默认UTF-8
	 * @return 转换后的字符集
	 */
	public static String convert(String source, String srcCharset, String destCharset) {
		return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
	}

	/**
	 * 转换字符串的字符集编码
	 * 
	 * @param source      字符串
	 * @param srcCharset  源字符集，默认ISO-8859-1
	 * @param destCharset 目标字符集，默认UTF-8
	 * @return 转换后的字符集
	 */
	public static String convert(String source, Charset srcCharset, Charset destCharset) {
		if (null == srcCharset) {
			srcCharset = StandardCharsets.ISO_8859_1;
		}

		if (null == destCharset) {
			srcCharset = StandardCharsets.UTF_8;
		}

		if (StringUtils.isBlank(source) || srcCharset.equals(destCharset)) {
			return source;
		}
		return new String(source.getBytes(srcCharset), destCharset);
	}

	/**
	 * @return 系统字符集编码
	 */
	public static String systemCharset() {
		return Charset.defaultCharset().name();
	}

	/**
	 * 替换特殊字符(把特殊字符替换成预定义实体字符)
	 * 
	 * @param val
	 * @return
	 */
	public static String replaceSpecialCharacter2(String val) {
		if (CommonUtil.isNotEmpty(val)) {
			for (int i = 0; i < CharsetKit.formerCharacterList2.size(); i++) {
				val = val.replace(CharsetKit.formerCharacterList2.get(i), CharsetKit.escapeCharacterList2.get(i));
			}
			return val;
		}
		return "";
	}

	/**
	 * 替换特殊字符(把转义字符转换成特殊字符)
	 * 
	 * @param val
	 * @return
	 */
	public static String replaceSpecialToCharacter(String val) {
		if (CommonUtil.isNotEmpty(val)) {
			for (int i = 0; i < CharsetKit.formerCharacterList.size(); i++) {
				val = val.replace(CharsetKit.escapeCharacterList.get(i), CharsetKit.formerCharacterList.get(i));
			}
			return val;
		}
		return "";
	}

	/**
	 * 将List中的每个Map中的元素值替换特殊字符(把转义字符转换成特殊字符)
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map> replaceListSpecialToCharacter(List<Map> list) {
		List newlist = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			Map newmap = new HashMap();
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey() + "";
				String value = entry.getValue() == null ? null
						: CharsetKit.replaceSpecialToCharacter(entry.getValue() + "");
				newmap.put(key.toLowerCase(), value);
			}
			newlist.add(newmap);
		}
		return newlist;
	}

	/**
	 * 验证含有转义字符的长度是否超过指定的长度（例："&#34;"的长度为 1 ）
	 * 
	 * @param val(字符)
	 * @param len(长度)
	 * @return
	 */
	public static boolean isSpecialStringLength(String val, int len) {
		if (!StringUtils.isBlank(val) && val.length() > len) {
			String newval = CharsetKit.replaceSpecialToCharacter(val);
			if (newval.length() > len) {
				return false;
			}
		}
		return true;
	}
}
