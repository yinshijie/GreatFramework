/*
 * @title com.enn.greatframework.common.GreatFrameworkConst.java
 * @Copy.Right (c)2017.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年1月24日 下午2:48:07
 * @author Enn.HowMuch.yinshijie
 * @version V0.1.0
 */
package com.enn.greatframework.common;

/**
 * 平台公用常量类
 * @ClassName GreatFrameworkConst
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月24日 下午2:48:07
 *
 */
public class GreatFrameworkConst {

	/**
	 * 状态-有效
	 */
	public static final String STATE_ENABLED = "1";
	/**
	 * 状态-无效
	 */
	public static final String STATE_DISABLED = "0";
	/**
	 * 状态-成功
	 */
	public static final String STATE_SUCCESS = "1";
	/**
	 * 状态-失败
	 */
	public static final String STATE_FAIL = "0";

	/**
	 * 默认字符集
	 */
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";
	/**
	 * 默认sessionId存放字段名称
	 */
	public static final String DEFAULT_SESSION_ID_FIELD_NAME = "_jsessionid";
	/**
	 * 默认msgId存放字段名称
	 */
	public static final String DEFAULT_MESSAGE_ID_FIELD_NAME = "msgId";

	/**
	 * 消息体加密类型-不加密
	 */
	public static final String DECODE_TYPE_NONE = "0";
	/**
	 * 消息体加密类型-AES加密
	 */
	public static final String DECODE_TYPE_AES = "1";
	/**
	 * 消息体加密类型-BASE64加密
	 */
	public static final String DECODE_TYPE_BASE64 = "2";
	/**
	 * 请求参数名称--应用TOKEN
	 */
	public static final String REQUEST_PARAM_APPTOKEN = "appToken";
	/**
	 * 请求参数名称--时间戳
	 */
	public static final String REQUEST_PARAM_TIMESTAMP = "timestamp";
	/**
	 * 请求参数名称--随机数
	 */
	public static final String REQUEST_PARAM_NONCE = "nonce";
	/**
	 * 请求参数名称--签名
	 */
	public static final String REQUEST_PARAM_SIGNATURE = "signature";

}
