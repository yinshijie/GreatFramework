/*
 * @title com.enn.greatframework.common.http.context.RequestBody.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年8月16日 下午7:49:48
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.context;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * 请求参数集
 *
 * @ClassName RequestBody
 * @Description TODO
 * @author yinshijie
 * @date 2016年8月16日 下午7:49:48
 *
 */
@JSONType(orders = { "messageId", "requestDeviceId", "requestDeviceType", "params" })
public class RequestContent implements Serializable {

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = 5153527978230423660L;

	/**
	 * 请求消息ID
	 */
	private String messageId;
	/**
	 * 请求参数集合
	 */
	private RequestParameter params = new RequestParameter();
	/**
	 * 请求设备标识ID
	 */
	private String requestDeviceId;
	/**
	 * 请求设备类型：00-	web，01-android，02-ios，99-未知
	 */
	private String requestDeviceType;

	/**
	 * @return the messageId
	 * @since JDK1.5
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 * @since JDK1.5
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the requestDeviceId
	 * @since JDK1.5
	 */
	public String getRequestDeviceId() {
		return requestDeviceId;
	}

	/**
	 * @param requestDeviceId the requestDeviceId to set
	 * @since JDK1.5
	 */
	public void setRequestDeviceId(String requestDeviceId) {
		this.requestDeviceId = requestDeviceId;
	}

	/**
	 * @return the requestDeviceType
	 * @since JDK1.5
	 */
	public String getRequestDeviceType() {
		return requestDeviceType;
	}

	/**
	 * @param requestDeviceType the requestDeviceType to set
	 * @since JDK1.5
	 */
	public void setRequestDeviceType(String requestDeviceType) {
		this.requestDeviceType = requestDeviceType;
	}

	/**
	 * @return the arguments
	 * @since JDK1.5
	 */
	public RequestParameter getParams() {
		return params;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 * @since JDK1.5
	 */
	public void setParams(RequestParameter arguments) {
		this.params = arguments;
	}

	/**
	 * 添加参数List
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.http.context.RequestBody.addArg(...)
	 *
	 * @param argName
	 * @param argValue
	 */
	public void addParam(String argName, Object argValues) {
		this.params.put(argName, argValues);
	}

	/**
	 * 添加参数
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.http.context.RequestBody.addArg(...)
	 *
	 * @param argName
	 * @param argValue
	 */
	public void addParamsMap(Map<String, Object> argMap) {
		this.params.putAll(argMap);
	}

	/**
	 * 获取参数
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.http.context.RequestBody.getArgument(...)
	 *
	 * @param argKey
	 * @return
	 * @throws TransportException
	 */
	public String getParam(String paramKey) throws IllegalArgumentException {
		if (StringUtil.isBlank(paramKey)) {
			throw new IllegalArgumentException("param paramKey can not be null");
		}
		return params.get(paramKey).toString();
	}

	/**
	 * 获取参数
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.http.context.RequestBody.getArgument(...)
	 *
	 * @param argKey
	 * @param claz
	 * @return
	 * @throws TransportException
	 */
	@JSONField(serialize = false)
	public <T> T getParam(String paramKey, Class<T> claz) throws IllegalArgumentException {
		if (StringUtil.isBlank(paramKey)) {
			throw new IllegalArgumentException("params paramKey can not be null");
		}
		return JSON.parseObject(params.get(paramKey).toString(), claz);
	}

	/**
	 * 从json字符串加载
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.http.context.RequestBody.fromJsonStr(...)
	 *
	 * @param string
	 * @return
	 */
	public static final RequestContent fromJsonStr(String string) {
		return JSON.parseObject(string, RequestContent.class);
	}

	/*
	 * @Title: toString
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		RequestContent parameters = new RequestContent();
		// parameters.setMessageId(DigestorManager.GUID());
		// parameters.addParam("loginName", "18613982225");
		// parameters.addParam("loginPwd",
		// Base64Utils.encodeToString("ysj850620".getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME)));

		parameters.addParam("customerName", "yin_shj");
		parameters.addParam("customerTitle", "尹世杰");
		parameters.addParam("customerPassword", DigestorManager.greatPasswordCreate("ysj850620"));
		parameters.addParam("customerMobile", "18613982225");
		parameters.addParam("customerEmail", "920251@qq.com");
		parameters.addParam("customerSecMobile", "18613982225");
		parameters.addParam("customerSecEmail", "920251@qq.com");
		System.out.println(parameters);

		System.exit(0);
	}
}
