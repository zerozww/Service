package com.lost.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static final String EMPTY = "";
    
	/** 下划线 */
	private static final char SEPARATOR = '_';

	/**
	 * 为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 转大写 
	 * @param instr
	 * @return
	 */
	public static String toUpperCase(String instr) {
		return instr == null ? instr : instr.toUpperCase();
	}

	/**
	 * 转小写
	 * 
	 * @param instr
	 * @return
	 */
	public static String toLowerCase(String instr) {
		return instr == null ? instr : instr.toLowerCase();
	}

	/**
	 * 首字母大写 ,其余不变
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toUpperCase());
	}

	/**
	 * 首字母小写 ,其余不变
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toLowerCase());
	}

	/**
	 * 不会抛NullPointerException 的trim() 传入null会返回null
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 过滤 ;当instr==null时返回长度为0的""; 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
	 * 
	 * @param instr
	 * @return
	 */
	public static String nvl(String instr) {
		return nvl(instr, "");
	}

	/**
	 * 
	 * 过滤 ,把null和长度为0的""当成同一种情况处理; <br>
	 * 
	 * 当instr==null||"".equals(instr)时返回defaultValue ;其它情况返回 instr
	 * 
	 * 
	 * 
	 * @param instr
	 * 
	 * @param defaultValue
	 * 
	 * @return
	 * 
	 */
	public static String nvl(String instr, String defaultValue) {
		return instr == null || "".equals(instr) ? defaultValue : instr;
	}

	/**
	 * 比较 str1 和 str2 如果都是 null 或者 str1.equals(str2) 返回 true 表示一样 ;
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 != null && str1.equals(str2))
			return true;
		return false;
	}

	public static String apadLeft(double a, int b, int len) {
		return apadLeft(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadRight(double a, int b, int len) {
		return apadRight(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadLeft(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(str.length() - len, len);
		return apadpro(str, str2, len, true);
	}

	public static String apadRight(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(0, len);
		return apadpro(str, str2, len, false);
	}

	private static String apadpro(String a, String b, int len, boolean appendleft) {
		int f = len - a.length();
		for (int i = 0; i < f; i++) {
			a = appendleft == true ? b + a : a + b;
		}
		return a;
	}

	/**
	 * 
	 * 清除字符串中所有的空格 ,传入null返回null
	 * 
	 * 
	 * 
	 * @author wangp
	 * 
	 * @since 2009.02.06
	 * 
	 * @param str
	 * 
	 * @return
	 * 
	 */
	public static String clear(String str) {
		return clear(str, " ");
	}
	
	/**
	 * 去掉所有换行空格字符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}


	/**
	 * 
	 * 清除str中出现的所有str2字符序列 直到结果中再也找不出str2为止 str2 == null时 返回str
	 * 
	 * 
	 * 
	 * @author wangp
	 * 
	 * @since 2009.02.06
	 * 
	 * @param str
	 * 
	 *             原始字符串
	 * 
	 * @param str2
	 * 
	 *             清除的目标
	 * 
	 * @return
	 * 
	 */
	public static String clear(String str, String str2) {
		if (str == null)
			return str;
		if (str2 == null)
			return str;
		String reg = "(" + str2 + ")+";
		Pattern p = Pattern.compile(reg);
		while (p.matcher(str).find()) {
			str = str.replaceAll(reg, "");
		}
		return str;
	}

	/**
	 * 
	 * 如果str的长度超过了c则取c-sub.length长度,然后拼上sub结尾
	 * 
	 * 
	 * 
	 * @author wangp
	 * 
	 * @since 2009.06.11
	 * 
	 * @param str
	 * 
	 * @param c
	 * 
	 * @param sub
	 * 
	 * @return
	 * 
	 */
	public static String suojin(String str, int c, String sub) {
		if (isBlank(str))
			return str;
		if (str.length() <= c)
			return str;
		sub = nvl(sub);
		c = c - sub.length();
		c = c > str.length() ? 0 : c;
		str = str.substring(0, c);
		return str + sub;
	}

	/**
	 * 
	 * 如果str的长度超过了length,取前length位然后拼上...
	 * 
	 * 
	 * 
	 * @author yimian
	 * 
	 * @since 2009.06.11
	 * 
	 * @param str
	 * 
	 * @param length
	 * 
	 * @return
	 * 
	 */
	public static String suojin(String str, int length) {
		return suojin(str, length, "…");
	}

	public static String replaceOnce(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, 1);
	}

	public static String replace(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, -1);
	}

	public static String replace(String text, String searchString, String replacement, int max) {
		if (isBlank(text) || isBlank(searchString) || replacement == null || max == 0)
			return text;
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == -1)
			return text;
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = increase >= 0 ? increase : 0;
		increase *= max >= 0 ? max <= 64 ? max : 64 : 16;
		StringBuffer buf = new StringBuffer(text.length() + increase);
		do {
			if (end == -1)
				break;
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0)
				break;
			end = text.indexOf(searchString, start);
		} while (true);
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 把一个数组连接成字符串
	 * 
	 * @param arrays Object 可以转换成字符串的数组
	 * @param split  String 分隔符
	 * @return
	 */
	public static String join(Object[] arrays, String split) {
		StringBuilder sb = new StringBuilder();
		for (Object o : arrays) {
			sb.append(split).append(o);
		}
		return sb.substring(split.length());
	}

	public static int intVal(String intStr, int defVal) {
		if (intStr == null || "".equals(intStr.trim()))
			return defVal;
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e) {
			e.printStackTrace();
			return defVal;
		}
	}

	/**
	 * 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
	 * 
	 * @param length
	 * @return
	 */
	public static String random(int length) {
		// 随机字符串的随机字符库
		String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		int len = KeyString.length();
		for (int i = 0; i < length; i++) {
			sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
		}
		return sb.toString();
	}

	/**
	 * 字符串对象如果为空，返回默认值
	 * 
	 * @param valStr   String 字符串
	 * @param defaluts String 默认值
	 * @return String
	 */
	public static String defaultString(String valStr, String defaluts) {
		return isBlank(valStr) ? defaluts : valStr;
	}

	/**
	 * * 判断一个对象是否为空
	 * 
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 * 
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * * 判断一个对象数组是否为空
	 * 
	 * @param objects 要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 * 
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params   参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isBlank(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str  验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 下划线转驼峰命名
	 */
	public static String toUnderScoreCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (i > 0) {
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			} else {
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1)) {
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
				sb.append(SEPARATOR);
			} else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}
	
    /**
     * * 将驼峰字符串转换成下划线小写格式 
     * @param str String
     * @return string
     */
    public static String underline(String str)
    {
    	StringBuilder sb = new StringBuilder();
    	char[] charArray = str.toCharArray(); 
    	for (int i = 0; i < charArray.length; i++) { 
	    	if(charArray[i] >= 'A' && charArray[i] <= 'Z'){ 
	    		sb.append('_').append(Character.toLowerCase(charArray[i])); 
	    	}else{ 
	    		sb.append(charArray[i]); 
	    	} 
    	} 
    	return sb.toString();
    }
    
    /**
     * * 将下划线小写转换成驼峰字符串格式 
     * @param str String
     * @return string
     */
    public static String hump(String str)
    {
    	StringBuilder sb = new StringBuilder();
    	char[] charArray = str.toCharArray(); 
    	for (int i = 0; i < charArray.length; i++) { 
	    	if(charArray[i] == '_'){ 
	    		sb.append(Character.toUpperCase(charArray[++i])); 
	    	}else{ 
	    		sb.append(charArray[i]); 
	    	} 
    	} 
    	return sb.toString();
    }
    /**
     * * 将下划线小写转换成驼峰字符串格式 
     * @param str String
     * @return string
     */
    public static String hump(String str, boolean upFirst)
    {
    	str = hump(str);
    	return upFirst ? toUpperCaseFirst(str) : str;
    }
    /**
     * list转换为分隔符字符串
     * @param list
     * @param separator
     * @return
     */
    @SuppressWarnings("rawtypes")
	public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }
    
    /**
     * 将文本转换成长整形,默认返回0
     * @param v
     * @return
     */
    public static long parseLong(String v) {
    	if(isBlank(v)) return 0;
    	try {
    		return Long.parseLong(v);
    	}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 将文本转换成长整形,默认返回0
     * @param v
     * @return
     */
    public static long parseInt(String v) {
    	if(isBlank(v)) return 0;
    	try {
    		return Integer.parseInt(v);
    	}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 判定ID是不是合法的long数据，如果不是返回false
     * @param v
     * @return
     */
    public static boolean isValidLong(Long value) {
        if (null == value || value.longValue() < 1L) {
            return false;
        }
        return true;
    }
}
