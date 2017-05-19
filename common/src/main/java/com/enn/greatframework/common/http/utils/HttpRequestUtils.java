/*
 * @title com.enn.greatframework.common.http.utils.HttpRequestUtils.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年1月25日 下午1:40:17
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.utils;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.enn.greatframework.common.GreatFrameworkException;

/**
 * Http请求工具集
 * @ClassName HttpRequestUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月25日 下午1:40:17
 *
 */
public class HttpRequestUtils {

	/**
	 * 从HttpServletRequest中获取请求内容
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.utils.HttpRequestUtils.getRequestContext(...)
	 *
	 * @param httpServletRequest
	 * @param charSet
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static String getRequestContent(HttpServletRequest httpServletRequest, String charSet)
	        throws GreatFrameworkException {
		ServletInputStream localServletInputStream = null;
		try {
			localServletInputStream = httpServletRequest.getInputStream();

			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			byte[] arrayOfByte = new byte[4096];
			int i = -1;
			while ((i = localServletInputStream.read(arrayOfByte, 0, 4096)) != -1) {
				localByteArrayOutputStream.write(arrayOfByte, 0, i);
			}
			arrayOfByte = null;
			return new String(localByteArrayOutputStream.toByteArray(), charSet);

		} catch (Exception e) {
			throw new GreatFrameworkException(e);
		}
	}

	/**
	 * 从HttpServletRequest获取参数键值对
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.utils.HttpRequestUtils.getParams(...)
	 *
	 * @param request
	 * @return
	 */
	public static Map<String, String[]> getParams(HttpServletRequest request) {
		return request.getParameterMap();
	}

	/**
	 * 从HttpServletRequest中获取请求IP地址
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.utils.HttpRequestUtils.getRequestIp(...)
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		} else {
			String[] ipList = ip.split(",");
			for (String tempIp : ipList) {
				if (tempIp.trim().equalsIgnoreCase("unknown")) {
					continue;
				} else {
					ip = tempIp.trim();
					break;
				}
			}
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
}
