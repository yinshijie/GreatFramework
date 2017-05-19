/*
 * @title com.enn.greatframework.common.date.DateTimeFormator.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年8月17日 上午9:18:26
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 日期时间工具
 * @ClassName DateTimeFormator
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月24日 下午3:28:53
 *
 */
public class DateTimeFormator extends DateFormatUtils {

	private DateTimeFormator() {
	}

	/**
	 * 默认解析格式
	 */
	public static final String DEFAULT_PARSER = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 精确解析格式
	 */
	public static final String ACCURATE_PARSER = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * 中文解析格式
	 */
	public static final String CHINESE_PARSER = "yyyy年MM月dd日 HH时mm分ss秒";

	public static final String CURRENT_DATE_FORMAT = "yyyyMMdd";
	public static final String CURRENT_TIME_FORMAT = "HHmmss";
	public static final String CURRENT_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

	/**
	 * 获取当前日期（格式：yyyyMMdd）
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_DATE(...)
	 *
	 * @return
	 */
	public static final String CURRENT_DATE() {
		Calendar instance = Calendar.getInstance(Locale.getDefault());
		FastDateFormat dateFormat = FastDateFormat.getInstance(CURRENT_DATE_FORMAT);
		return dateFormat.format(instance.getTime());
	}

	/**
	 * 获取当前日期（格式：yyyy年MM月dd日 HH时mm分ss秒）
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CHINESE_PARSER(...)
	 *
	 * @return
	 */
	public static final String CHINESE_PARSER() {
		Calendar instance = Calendar.getInstance(Locale.getDefault());
		FastDateFormat dateFormat = FastDateFormat.getInstance(CHINESE_PARSER);
		return dateFormat.format(instance.getTime());
	}

	/**
	 * 获取当前时间（格式：HHmmss）
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_TIME(...)
	 *
	 * @return
	 */
	public static String CURRENT_TIME() {
		Calendar instance = Calendar.getInstance(Locale.getDefault());
		FastDateFormat dateFormat = FastDateFormat.getInstance(CURRENT_TIME_FORMAT);
		return dateFormat.format(instance.getTime());
	}

	/**
	 * 获取当前年份
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_YEAR(...)
	 *
	 * @return
	 */
	public static final int CURRENT_YEAR() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_MONTH(...)
	 *
	 * @return
	 */
	public static final int CURRENT_MONTH() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.MONTH);
	}

	/**
	 * 获取当前日期-针对月份
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_DAY_OF_MONTH(...)
	 *
	 * @return
	 */
	public static final int CURRENT_DAY_OF_MONTH() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前日期-针对周
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.CURRENT_DAY_OF_WEEK(...)
	 *
	 * @return
	 */
	public static final int CURRENT_DAY_OF_WEEK() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前时间戳（格式：yyyyMMddHHmmss）
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.TIMESTAMP(...)
	 *
	 * @return
	 */
	public static final String TIMESTAMP() {
		Calendar instance = Calendar.getInstance(Locale.getDefault());
		FastDateFormat dateFormat = FastDateFormat.getInstance(CURRENT_TIMESTAMP_FORMAT);
		return dateFormat.format(instance.getTime());
	}

	/**
	 * 根据传入字符串解析日期
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jParseDate(...)
	 *
	 * @param dateForString
	 * @param pattern
	 * @param locale
	 * @return
	 * @throws ParseException
	 */
	public static Date jParseDate(String dateForString, String pattern, Locale locale) throws ParseException {
		FastDateFormat dateFormat = FastDateFormat.getInstance(pattern, locale);
		return dateFormat.parse(dateForString);
	}

	/**
	 * 根据传入字符串解析日期
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jParseDate(...)
	 *
	 * @param dateForString
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date jParseDate(String dateForString, String pattern) throws ParseException {
		return jParseDate(dateForString, pattern, Locale.getDefault());
	}

	/**
	 * 根据传入字符串解析日期
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jParseDate(...)
	 *
	 * @param dateForString
	 * @return
	 * @throws ParseException
	 */
	public static Date jParseDate(String dateForString) throws ParseException {
		return jParseDate(dateForString, DEFAULT_PARSER, Locale.getDefault());
	}

	/**
	 * 根据传入日期时间解析成指定格式的字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jFormatDate(...)
	 *
	 * @param dateTime
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static String jFormatDate(Date dateTime, String pattern, Locale locale) {
		FastDateFormat dateFormat = FastDateFormat.getInstance(pattern, locale);
		return dateFormat.format(dateTime);
	}

	/**
	 * 根据传入日期时间解析成指定格式的字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jFormatDate(...)
	 *
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String jFormatDate(Date dateTime, String pattern) {
		return jFormatDate(dateTime, pattern, Locale.getDefault());
	}

	/**
	 * 根据传入日期时间解析成字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.date.DateTimeFormator.jFormatDate(...)
	 *
	 * @param dateTime
	 * @return
	 */
	public static String jFormatDate(Date dateTime) {
		return jFormatDate(dateTime, DEFAULT_PARSER, Locale.getDefault());
	}
}
