/*
 * @title com.enn.greatframework.common.lang.StringUtil.java
 *
 * @Copy.Right (c)2015.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2015年12月4日 下午1:02:39
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.lang;

import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * String工具集</br>
 * 整合多个常用的String类方法
 *
 * @ClassName StringUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2015年12月4日 下午1:02:39
 *
 */
public final class StringUtil extends StringUtils {

	/**
	 * 空格：" "
	 */
	public static final String SPACE = " ";

	/**
	 * 空：""
	 */
	public static final String EMPTY = "";

	/**
	 * 换行符，Windows系统下："\r\n"，Linux系统下："\n"
	 */
	public static final String LINE = System.lineSeparator();

	/**
	 * 制表符："\t"
	 */
	public static final String TAB = "\t";

	/**
	 * 当使用String.indexOf(char|String)未找到对应的index时返回的常量
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * 项目通用默认字符集
	 */
	public static final String PUBLIC_DEFAULT_CHATSET = "UTF-8";

	/**
	 * 获取版权信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.COPYRIGHT(...)
	 *
	 * @return
	 */
	public static final String COPYRIGHT() {
		String currentYear = String.valueOf(Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR));
		return String.format("Copy.Right (c)%s.好买气电子商务有限公司", currentYear);
	}

	/**
	 * @Description TODO 是否为空的字符序列</br>
	 *              null:true</br>
	 *              "":true</br>
	 *              "     ":false
	 * @Call com.enn.greatframework.common.lang.StringUtil.isEmpty(...)
	 *
	 * @param cs
	 * @return
	 */
	public static final boolean isEmpty(final CharSequence cs) {
		return cs == null || "".equals(cs) || "null".equals(cs);
	}

	/**
	 * @Description TODO 是否不为空的字符序列</br>
	 *              null:false</br>
	 *              "":false</br>
	 *              "     ":true
	 * @Call com.enn.greatframework.common.lang.StringUtil.isNotEmpty(...)
	 *
	 * @param cs
	 * @return
	 */
	public static final boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * @Description TODO 是否为空白的字符序列</br>
	 *              null:true</br>
	 *              "":true</br>
	 *              "     ":true
	 * @Call com.enn.greatframework.common.lang.StringUtil.isBlank(...)
	 *
	 * @param cs
	 * @return
	 */
	public static final boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Description TODO 是否不为空白的字符序列</br>
	 *              null:false</br>
	 *              "":false</br>
	 *              "     ":false
	 * @Call com.enn.greatframework.common.lang.StringUtil.isNotBlank(...)
	 *
	 * @param cs
	 * @return
	 */
	public static final boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 *
	 * @Description TODO 去掉字符串两端空格
	 * @Call com.enn.greatframework.common.lang.StringUtil.trim(...)
	 *
	 * @param str
	 * @return
	 */
	public static final String trim(String str) {
		if (str == null) {
			return null;
		}
		return str.trim();
	}

	/**
	 * @Description TODO 去掉字符串两端空格，如果传入字符串为null或str.trim().equals("")将返回null
	 * @Call com.enn.greatframework.common.lang.StringUtil.trimToNull(...)
	 *
	 * @param str
	 * @return
	 */
	public static final String trimToNull(String str) {
		if (isBlank(str)) {
			return null;
		}
		return str.trim();
	}

	/**
	 * @Description TODO 从str第一个字符开始中去除stripChars中的字符直到非stripChars字符
	 * @Call com.enn.greatframework.common.lang.StringUtil.stripStart(...)
	 *
	 * @param str
	 *            源目标字符串
	 * @param stripChars
	 *            需要去除的字符
	 * @return
	 */
	public static final String stripStart(String str, final String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		int start = 0;
		if (stripChars == null) {
			while (start != strLen && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else if (stripChars.isEmpty()) {
			return str;
		} else {
			while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
				start++;
			}
		}
		return str.substring(start);
	}

	/**
	 *
	 * @Description TODO 从str最后一个字符开始中去除stripChars中的字符直到非stripChars字符
	 * @Call com.enn.greatframework.common.lang.StringUtil.stripEnd(...)
	 *
	 * @param str
	 * @param stripChars
	 * @return
	 */
	public static String stripEnd(final String str, final String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0) {
			return str;
		}

		if (stripChars == null) {
			while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else if (stripChars.isEmpty()) {
			return str;
		} else {
			while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	/**
	 *
	 * @Description TODO 分别从str的第一个字符、最后一个字符开始中去除stripChars中的字符直到非stripChars字符
	 * @Call com.enn.greatframework.common.lang.StringUtil.strip(...)
	 *
	 * @param str
	 * @param stripChars
	 * @return
	 */
	public static String strip(String str, final String stripChars) {
		if (isEmpty(str)) {
			return str;
		}
		str = stripStart(str, stripChars);
		return stripEnd(str, stripChars);
	}

	/**
	 * @Description TODO 将字符串转成非NULL
	 * @Call com.enn.greatframework.common.lang.StringUtil.toNotNull(...)
	 *
	 * @param str
	 * @return
	 */
	public static final String toNotNull(final String str) {
		if (str == null) {
			return EMPTY;
		}
		return str;
	}

	/**
	 * @Description TODO 将字符串转成非NULL
	 * @Call com.enn.greatframework.common.lang.StringUtil.toNotNull(...)
	 *
	 * @param str
	 * @return
	 */
	public static final String toNotNull(final String str, final String defaultStr) {
		if (str == null) {
			return defaultStr;
		}
		return str;
	}

	/**
	 * 首字母大写
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.capitalize(...)
	 *
	 * @param str
	 * @return
	 */
	public static final String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		final char firstChar = str.charAt(0);
		if (Character.isTitleCase(firstChar)) {
			// 已经首字母大写
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(firstChar)).append(str.substring(1)).toString();
	}

	/**
	 * 传入带下划线的数据库表名或列名，转化为标准参数名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.convertUnderscoreNameToPropertyName(...)
	 *
	 * @param name
	 * @return
	 */
	public static final String convertUnderscoreNameToPropertyName(String name) {
		StringBuilder result = new StringBuilder();
		boolean nextIsUpper = false;
		if (name != null && name.length() > 0) {
			if (name.length() > 1 && name.substring(1, 2).equals("_")) {
				result.append(name.substring(0, 1).toUpperCase());
			} else {
				result.append(name.substring(0, 1).toLowerCase());
			}
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals("_")) {
					nextIsUpper = true;
				} else {
					if (nextIsUpper) {
						result.append(s.toUpperCase());
						nextIsUpper = false;
					} else {
						result.append(s.toLowerCase());
					}
				}
			}
		}
		return result.toString();
	}

	/**
	 * 传入带下划线的数据库表名或列名，转化为标准类名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.convertUnderscoreNameToClassName(...)
	 *
	 * @param name
	 * @return
	 */
	public static final String convertUnderscoreNameToClassName(String name) {
		return capitalize(convertUnderscoreNameToPropertyName(name));
	}

	/**
	 * 将2进制数组转为16进制
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.parseByte2HexStr(...)
	 *
	 * @param buf
	 * @return
	 */
	public static final String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 将16进制字符串转为2进制字节数组
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.parseHexStr2Byte(...)
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 参数检查
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.checkParams(...)
	 *
	 * @param params
	 * @return
	 */
	public static final boolean checkParams(String... params) {
		for (String param : params) {
			if (isBlank(param)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * 参数检查
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.lang.StringUtil.checkObjectNotNull(...)
	 *
	 * @param params
	 * @return
	 */
	public static final boolean checkObjectParams(Object... params) {
		for (Object param : params) {
			if (param == null) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
