/*
 * @title com.enn.greatframework.gateway.server.tcp.http.GreatPreRequestLogFilter.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年3月14日 上午10:41:49
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.gateway.server.tcp.http.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.date.DateTimeFormator;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.sender.param.GreatSignatureParam;
import com.enn.greatframework.common.http.utils.HttpRequestUtils;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.symmetric.AES.AESAlgorithmMode;
import com.enn.greatframework.common.security.symmetric.AES.AESCrypt;
import com.enn.greatframework.common.security.symmetric.AES.AESCryptFactory;
import com.enn.greatframework.gateway.server.service.client.OpenDeveloperClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

/**
 * 请求日志过滤器
 * @ClassName GreatPreRequestLogFilter
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月14日 上午10:41:49
 *
 */
@Service("greatPreCheckRequestTimeFilter")
public class GreatPreCheckRequestTimeFilter extends ZuulFilter {

	private static final Logger LOGGER = Logger.getLogger(GreatPreCheckRequestTimeFilter.class);

	@Autowired
	private OpenDeveloperClient developerClient;

	/**
	 * 平台允许请求时间差异值
	 */
	private static final long _requestDiffTime = 1000 * 60 * 5;

	/*
	 * @Title: run
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		ResponseContent responseBody = null;
		RequestContext ctx = RequestContext.getCurrentContext();

		try {
			/** 解决输入流只能读取一次的问题 */
			HttpServletRequest request = ctx.getRequest();
			HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);
			ctx.setRequest(requestWrapper);

			/** 获取传入参数 */
			String ip = HttpRequestUtils.getRequestIp(requestWrapper);
			String appToken = requestWrapper.getParameter(GreatFrameworkConst.REQUEST_PARAM_APPTOKEN);
			String timestamp = requestWrapper.getParameter(GreatFrameworkConst.REQUEST_PARAM_TIMESTAMP);
			String nonce = requestWrapper.getParameter(GreatFrameworkConst.REQUEST_PARAM_NONCE);
			String signature = requestWrapper.getParameter(GreatFrameworkConst.REQUEST_PARAM_SIGNATURE);
			String data = StringUtil.EMPTY;
			if (requestWrapper.getMethod().equals("GET")) { // GET请求正文内容
				data = requestWrapper.getParameter("data");
			} else if (requestWrapper.getMethod().equals("POST")) { // POST请求正文内容
				data = new String(requestWrapper.getContentData(), GreatFrameworkConst.DEFAULT_CHARSET_NAME);
			}

			LOGGER.debug(String.format("%s - %s(%s) : appToken=%s, timestamp=%s, nonce=%s, signature=%s, data=%s", ip,
			        request.getMethod(), request.getServletPath(), appToken, timestamp, nonce, signature, data));

			/**
			 * 检查请求参数正确性及验签
			 */
			if (!StringUtil.checkParams(appToken, timestamp, nonce, signature)) { // 检查必填参数
				responseBody = ResponseContent.PARAMS_ERROR();
				ctx.setSendZuulResponse(Boolean.FALSE);
				ctx.setResponseStatusCode(HttpStatus.NOT_IMPLEMENTED.value()); // 参数错误
				ctx.setResponseBody(responseBody.toString());
				LOGGER.warn("请求基础参数错误!");
			} else if (!checkRequestTime(timestamp)) { // 检查请求时间戳与服务器时间戳
				responseBody = ResponseContent.EXPIRED_ERROR();
				ctx.setSendZuulResponse(Boolean.FALSE);
				ctx.setResponseStatusCode(HttpStatus.NOT_IMPLEMENTED.value()); // 请求时间戳错误
				ctx.setResponseBody(responseBody.toString());
				LOGGER.warn("请求基础参数错误!请求时间不在服务器允许范围内!");
			} else {
				// 获取应用信息
				String applicationInfoJson = developerClient.getApplicationInfoByToken(appToken);
				JSONObject applicationInfo = ResponseContent.fromJsonStr(applicationInfoJson)
				        .getResultJson("applicationInfo");
				if (!checkApplication(applicationInfo)) {
					responseBody = ResponseContent.CHECK_TOKEN_ERROR();
					ctx.setSendZuulResponse(Boolean.FALSE);
					ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); // 请求时间戳错误
					ctx.setResponseBody(responseBody.toString());
					LOGGER.warn("应用Token验证失败!");
				} else { /** 消息体解密 */
					String encodeType = applicationInfo.getString("encodeType");
					if (GreatFrameworkConst.DECODE_TYPE_AES.equals(encodeType)) {
						String encodeAesKey = applicationInfo.getString("encodeAesKey");
						AESCrypt aesCrypt = AESCryptFactory.getInstance(AESAlgorithmMode.AES_CBC_NOPADDING,
						        encodeAesKey);
						data = aesCrypt.decryptHexStr(data);
						LOGGER.debug(String.format("AES decode : %s", data));
					} else if (GreatFrameworkConst.DECODE_TYPE_BASE64.equals(encodeType)) {
						data = new String(Base64Utils.decodeFromString(data), GreatFrameworkConst.DEFAULT_CHARSET_NAME);
						LOGGER.debug(String.format("BASE64 decode : %s", data));
					}

					/**
					 * 验证签名
					 */
					if (!checkSignature(appToken, timestamp, nonce, signature, data)) {
						responseBody = ResponseContent.VARIFY_ERROR();
						ctx.setSendZuulResponse(Boolean.FALSE);
						ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); // 签名验证错误
						ctx.setResponseBody(responseBody.toString());
						LOGGER.warn("请求签名校验失败!");
					}
				}
			}
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			ctx.setSendZuulResponse(Boolean.FALSE);
			ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // 平台内部错误
			ctx.setResponseBody(responseBody.toString());
			LOGGER.warn("平台内部错误!");
		}
		return null;
	}

	/*
	 * @Title: shouldFilter
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return Boolean.TRUE;
	}

	/*
	 * @Title: filterOrder
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 101;
	}

	/*
	 * @Title: filterType
	 *
	 * @Description TODO
	 *
	 * @return
	 *
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 检查请求时间
	 * @Description  TODO
	 * @Call com.enn.greatframework.gateway.server.tcp.http.filter.GreatPreCheckRequestTimeFilter.checkRequestTime(...)
	 *
	 * @param requestTimestamp
	 * @return
	 */
	private boolean checkRequestTime(String requestTimestamp) {
		if (StringUtil.isBlank(requestTimestamp)) {
			return Boolean.FALSE;
		}
		try {
			long requestTime = Long.parseLong(requestTimestamp); // 请求时间
			LOGGER.debug(String.format("请求时间戳:%s [%s], 当前时间戳:%s [%s]", requestTimestamp,
			        DateTimeFormator.jFormatDate(new Date(requestTime)), System.currentTimeMillis(),
			        DateTimeFormator.jFormatDate(new Date())));
			long nowTime = System.currentTimeMillis(); // 当前时间
			long diff = Math.abs(requestTime - nowTime); // 时间差
			if (diff < _requestDiffTime) {
				return Boolean.TRUE;
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Boolean.FALSE;
	}

	/**
	 * 检查签名
	 * @Description  TODO
	 * @Call com.enn.greatframework.gateway.server.tcp.http.filter.GreatPreCheckRequestTimeFilter.checkSignature(...)
	 *
	 * @param appToken
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	private boolean checkSignature(String appToken, String timestamp, String nonce, String signature, String data) {
		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken, timestamp, nonce, data);
		return signatureParam.verifySignature(signature);
	}

	/**
	 * 检查应用token
	 * @Description  TODO
	 * @Call com.enn.greatframework.gateway.server.tcp.http.filter.GreatPreCheckRequestTimeFilter.checkAppToken(...)
	 *
	 * @param appToken
	 * @return
	 */
	private boolean checkApplication(JSONObject applicationInfo) {
		// 验证获取应用信息
		if (!StringUtil.checkObjectParams(applicationInfo, applicationInfo.get("developerId"),
		        applicationInfo.get("applicationId"))) {
			return Boolean.FALSE;
		}

		// 获取开发者信息
		String developerInfoJson = developerClient.getDeveloperInfo(applicationInfo.get("developerId").toString());
		JSONObject developerInfo = ResponseContent.fromJsonStr(developerInfoJson).getResultJson("developerInfo");
		if (!StringUtil.checkObjectParams(developerInfo, developerInfo.get("developerId"),
		        developerInfo.get("developerStatus"))) {
			return Boolean.FALSE;
		}

		// 判断开发者状态
		String developerStatus = developerInfo.getString("developerStatus");
		if (!developerStatus.equals(GreatFrameworkConst.STATE_ENABLED)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
