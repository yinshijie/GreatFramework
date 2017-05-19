/*
 * @title com.enn.greatframework.common.http.context.RequestParameter.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年8月16日 下午7:45:22
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数
 * @ClassName RequestBody
 * @Description TODO
 * @author yinshijie
 * @date 2016年8月16日 下午7:45:22
 *
 */
public class RequestParameter extends HashMap<String, Object> implements Serializable {

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = -7600036658428172034L;

	/**
	 * 创建一个实例 RequestParameter.
	 *
	 * @Description: TODO
	 */
	public RequestParameter() {
	}

	/**
	 * 创建一个实例 RequestParameter.
	 *
	 * @Description: TODO
	 */
	public RequestParameter(Map<String, Object> params) {
		putAll(params);
	}

}
