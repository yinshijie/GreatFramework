/*
 * @title com.enn.greatframework.common.http.context.ResponseBody.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年8月16日 下午7:18:22
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.enn.greatframework.common.lang.StringUtil;

/**
 * 返回结果对象
 *
 * @ClassName ResponseBody
 * @Description TODO
 * @author yinshijie
 * @date 2016年8月16日 下午7:18:22
 *
 */
@JSONType(orders = { "messageId", "state", "describe", "results" })
public class ResponseContent {

	public static final String FIELD_NAME_STATE = "state";
	public static final String FIELD_NAME_DESCRIBE = "describe";

	public static final String FIELD_NAME_RESULTS = "results";

	public static final String FIELD_NAME_MSGID = "messageId";

	public static final String SUCCESS_CODE = "000000";
	public static final String SUCCESS_MSG = "SUCCESS";

	public static final String PARAMS_ERROR_CODE = "200001";
	public static final String PARAMS_ERROR_MSG = "请求参数检查错误";

	public static final String EXPIRED_ERROR_CODE = "200002";
	public static final String EXPIRED_ERROR_MSG = "请求不在有效时间内";

	public static final String VARIFY_ERROR_CODE = "200003";
	public static final String VARIFY_ERROR_MSG = "签名验证错误";

	public static final String CHECK_TOKEN_ERROR_CODE = "200004";
	public static final String CHECK_TOKEN_ERROR_MSG = "应用Token检查错误";

	public static final String REQUEST_MULTIFARIOUS_ERROR_CODE = "200005";
	public static final String REQUEST_MULTIFARIOUS_ERROR_MSG = "请求过于频繁";

	public static final String DATABASE_ERROR_CODE = "666666";
	public static final String DATABASE_ERROR_MSG = "数据库操作失败";

	public static final String INNER_ERROR_CODE = "888888";
	public static final String INNER_ERROR_MSG = "内部错误";

	public static final String SYSTEM_ERROR_CODE = "999999";
	public static final String SYSTEM_ERROR_MSG = "系统错误";

	/**
	 * 成功结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.SUCCESS(...)
	 *
	 * @return
	 */
	public static final ResponseContent SUCCESS() {
		return new ResponseContent(SUCCESS_CODE, SUCCESS_MSG);
	}

	/**
	 * 请求参数检查错误结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.PARAMS_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent PARAMS_ERROR() {
		return new ResponseContent(PARAMS_ERROR_CODE, PARAMS_ERROR_MSG);
	}

	/**
	 * 请求时间不在有效时段内结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.EXPIRED_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent EXPIRED_ERROR() {
		return new ResponseContent(EXPIRED_ERROR_CODE, EXPIRED_ERROR_MSG);
	}

	/**
	 * 数据操作失败
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.INNER_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent DATABASE_ERROR() {
		return new ResponseContent(DATABASE_ERROR_CODE, DATABASE_ERROR_MSG);
	}

	/**
	 * 内部错误结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.INNER_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent INNER_ERROR() {
		return new ResponseContent(INNER_ERROR_CODE, INNER_ERROR_MSG);
	}

	/**
	 * 系统错误结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.SYSTEM_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent SYSTEM_ERROR() {
		return new ResponseContent(SYSTEM_ERROR_CODE, SYSTEM_ERROR_MSG);
	}

	/**
	 * 签名验证失败结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.VARIFY_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent VARIFY_ERROR() {
		return new ResponseContent(VARIFY_ERROR_CODE, VARIFY_ERROR_MSG);
	}

	/**
	 * 应用Token检查失败结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseBody.CHECK_TOKEN_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent CHECK_TOKEN_ERROR() {
		return new ResponseContent(CHECK_TOKEN_ERROR_CODE, CHECK_TOKEN_ERROR_MSG);
	}

	/**
	 * 访问请求过于频繁错误结果集
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.context.ResponseContent.REQUEST_MULTIFARIOUS_ERROR(...)
	 *
	 * @return
	 */
	public static final ResponseContent REQUEST_MULTIFARIOUS_ERROR() {
		return new ResponseContent(REQUEST_MULTIFARIOUS_ERROR_CODE, REQUEST_MULTIFARIOUS_ERROR_MSG);
	}

	/**
	 * 状态码
	 */
	private String state;
	/**
	 * 状态说明
	 */
	private String describe;
	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 结果集
	 */
	private Map<String, Object> results = new HashMap<String, Object>();

	public ResponseContent() {
	}

	public ResponseContent(String state, String describe) {
		this.state = state;
		this.describe = describe;
	}

	/**
	 * @return the state
	 * @since JDK1.5
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 * @since JDK1.5
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the stateDescribe
	 * @since JDK1.5
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param stateDescribe
	 *            the stateDescribe to set
	 * @since JDK1.5
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * @return the results
	 * @since JDK1.5
	 */
	public Map<String, Object> getResults() {
		return results;
	}

	/**
	 * 执行结果是否成功
	 *
	 * @Description TODO
	 * @return
	 */
	@JSONField(serialize = false)
	public boolean isSuccess() {
		return state.equals(SUCCESS_CODE);
	}

	/**
	 * @return the messageId
	 * @since JDK1.5
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 * @since JDK1.5
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 添加一个结果
	 *
	 * @Description TODO
	 * @param objectName
	 * @param object
	 */
	public void addResultObject(String resultKey, Object result) {
		this.results.put(resultKey, result);
	}

	/**
	 * 添加一个结果集MAP
	 *
	 * @Description TODO
	 * @param resultMap
	 */
	public void addResultMap(Map<String, Object> resultMap) {
		this.results.putAll(resultMap);
	}

	/**
	 * 根据指定key返回一个结果的json字符串
	 *
	 * @Description TODO
	 * @param resultKey
	 * @return
	 */
	@JSONField(serialize = false)
	public String getResultStr(String resultKey) {
		if (this.results.containsKey(resultKey)) {
			return this.results.get(resultKey).toString();
		}
		return StringUtil.EMPTY;
	}

	/**
	 * 根据制定key返回一个结果的jsonObject
	 *
	 * @Description TODO
	 * @param resultKey
	 * @return
	 */
	@JSONField(serialize = false)
	public JSONObject getResultJson(String resultKey) {
		if (this.results.containsKey(resultKey)) {
			return JSON.parseObject(this.results.get(resultKey).toString());
		}
		return null;
	}

	/**
	 * 根据指定key返回一个结果
	 *
	 * @Description TODO
	 * @param resultKey
	 * @return
	 */
	@JSONField(serialize = false)
	public <T> T getResultObject(String resultKey, Class<T> claz) {
		if (this.results.containsKey(resultKey)) {
			return JSON.parseObject(this.results.get(resultKey).toString(), claz);
		}
		return null;
	}

	/**
	 * 根据指定key返回一个结果数组
	 *
	 * @Description TODO
	 * @param resultKey
	 * @return
	 */
	@JSONField(serialize = false)
	public <T> List<T> getResultList(String resultKey, Class<T> claz) {
		if (this.results.containsKey(resultKey)) {
			return JSON.parseArray(this.results.get(resultKey).toString(), claz);
		}
		return null;
	}

	/**
	 * 转化为json字符串
	 *
	 * @Description TODO
	 * @param excuteResult
	 * @return
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 从json字符串加载
	 *
	 * @Description TODO
	 * @param resultJson
	 * @return
	 */
	public static final ResponseContent fromJsonStr(String resultJson) {
		ResponseContent excuteResult = JSON.parseObject(resultJson, ResponseContent.class);
		return excuteResult;
	}

	public static void main(String[] args) {
		ResponseContent result = SUCCESS();
		result.setMessageId("1234");
		result.addResultObject("customerId", "0BF1218EE936E564850F63E3E46DF664");
		System.out.println(result);
	}
}
