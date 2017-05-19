/*
 * @title com.enn.greatframework.common.http.controller.AbstractController.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年1月25日 上午9:40:15
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.controller;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.GreatFrameworkException;
import com.enn.greatframework.common.http.context.RequestContent;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.utils.HttpRequestUtils;
import com.enn.greatframework.common.http.utils.HttpUtils;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * 基类控制器
 * @ClassName AbstractController
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月25日 上午9:40:15
 *
 */
public abstract class AbstractController {

	protected Logger LOGGER = Logger.getLogger(this.getClass());

	/**
	 * 获取http请求input字符串
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.greatgas.transport.common.TransportController.parseArguments(...)
	 *
	 * @param argsStr
	 * @return
	 * @throws GreatFrameworkException
	 */
	private String getRequestContent(HttpServletRequest request) throws GreatFrameworkException {
		String input = HttpRequestUtils.getRequestContent(request, GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return input;
	}

	/**
	 * 解析入参字符串，返回参数集类
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.parseArguments(...)
	 *
	 * @param request
	 * @return
	 * @throws GreatFrameworkException
	 */
	protected RequestContent getRequestBody(HttpServletRequest request) throws GreatFrameworkException {
		String inputStr = getRequestContent(request);

		logInfo(String.format("%s(%s) : %s", request.getMethod(), request.getServletPath(), inputStr));

		if (StringUtil.isNotBlank(inputStr)) {
			RequestContent requestBody = JSON.parseObject(inputStr, RequestContent.class);
			String msgId = requestBody.getMessageId();
			if (StringUtil.isBlank(msgId)) {
				requestBody.setMessageId(DigestorManager.GUID());
			}
			return requestBody;
		} else {
			return null;
		}
	}

	/**
	 * 获取http请求IP地址
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.getRequestIp(...)
	 *
	 * @param request
	 * @return
	 */
	protected String getRequestIp(HttpServletRequest request) {
		return HttpRequestUtils.getRequestIp(request);
	}

	/**
	 * 输出执行结果
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.responseWriter(...)
	 *
	 * @param request
	 * @param response
	 * @param result
	 */
	protected void responseWriter(HttpServletRequest request, HttpServletResponse response, String result) {
		try {
			String headEncoding = request.getHeader("Accept-Encoding");
			logTrace("Accept-Encoding = " + headEncoding);

			response.setContentType(HttpUtils.CONTENT_TYPE_PLAIN);
			if (headEncoding == null || (headEncoding.indexOf("gzip") == -1)) { // 客户端不支持gzip
				response.getWriter().print(result);
				response.getWriter().flush();
				logInfo(String.format("%s(%s) : %s", "RESPONSE", request.getServletPath(),
				        result));
			} else { // 客户端支持gzip
				response.addHeader("Content-Encoding", "gzip");// 在header中设置返回类型为gzip
				ServletOutputStream outputStream = response.getOutputStream();
				GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
				gzipOutputStream.write(result.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));
				gzipOutputStream.finish();
				gzipOutputStream.close();
				outputStream.close();
				logInfo(String.format("%s(%s) : %s", "RESPONSE-GZip",
				        request.getServletPath(), result));
			}
		} catch (IOException e) {
			logError(e.getMessage(), e);
		}
	}

	/**
	 * 输出执行结果
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.responseWriter(...)
	 *
	 * @param request
	 * @param response
	 * @param excuteResult
	 */
	protected void responseWriter(HttpServletRequest request, HttpServletResponse response, ResponseContent responseBody) {
		String result = StringUtil.EMPTY;
		if (responseBody != null) {
			result = responseBody.toString();
		}

		responseWriter(request, response, result);
	}

	/**
	 * 打印trace级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logTrace(...)
	 *
	 * @param logContent
	 */
	protected void logTrace(String logContent) {
		LOGGER.trace(logContent);
	}

	/**
	 * 打印trace级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logTrace(...)
	 *
	 * @param logContent
	 * @param e
	 */
	protected void logTrace(String logContent, Throwable e) {
		LOGGER.trace(logContent, e);
	}

	/**
	 * 打印info级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logInfo(...)
	 *
	 * @param logContent
	 */
	protected void logInfo(String logContent) {
		LOGGER.info(logContent);
	}

	/**
	 * 打印info级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logInfo(...)
	 *
	 * @param logContent
	 * @param e
	 */
	protected void logInfo(String logContent, Throwable e) {
		LOGGER.info(logContent, e);
	}

	/**
	 * 打印warn级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logWarn(...)
	 *
	 * @param logContent
	 */
	protected void logWarn(String logContent) {
		LOGGER.warn(logContent);
	}

	/**
	 * 打印warn级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logWarn(...)
	 *
	 * @param logContent
	 * @param e
	 */
	protected void logWarn(String logContent, Throwable e) {
		LOGGER.warn(logContent, e);
	}

	/**
	 * 打印error级别日志
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.controller.AbstractController.logError(...)
	 *
	 * @param logContent
	 * @param e
	 */
	protected void logError(String logContent, Throwable e) {
		LOGGER.error(logContent, e);
	}
}
