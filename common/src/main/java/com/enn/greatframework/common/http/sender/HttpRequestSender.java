/*
 * @title com.enn.greatframework.common.http.sender.HttpRequestUtils.java
 *
 * @Copy.Right (c)2016.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2016年5月22日 下午1:13:17
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.GreatFrameworkException;
import com.enn.greatframework.common.http.utils.HttpUtils;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * http请求工具
 *
 * @ClassName HttpRequestUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2016年5月22日 下午1:13:17
 *
 */
public class HttpRequestSender {
	private static final Logger LOGGER = Logger.getLogger(HttpRequestSender.class);

	/**
	 * 发送请求
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.common.utils.http.HttpRequestSender.send(...)
	 *
	 * @param url
	 * @param method
	 * @param urlParamsStr
	 * @param httpmethod
	 * @param requestProperties
	 * @param inputStr
	 * @param charSetName
	 * @return
	 * @throws TransportException
	 */
	public static final String send(String url, HttpMethod method, String urlParamsStr,
	        Map<String, String> requestProperties, String inputStr, String charSetName) throws GreatFrameworkException {
		LOGGER.info(String.format("send request-%s to %s, params : %s, charset : %s, inputBody : %s", method.name(),
		        url, urlParamsStr, charSetName, inputStr));
		if (url.startsWith("http://")) {
			String result = httpSend(url, method, urlParamsStr, requestProperties, inputStr, charSetName);
			LOGGER.info(String.format("receive from %s : %s", url, result));
			return result;
		} else if (url.startsWith("https://")) {
			String result = httpsSend(url, method, urlParamsStr, requestProperties, inputStr, charSetName);
			LOGGER.info(String.format("receive from %s : %s", url, result));
			return result;
		} else {
			throw new GreatFrameworkException("请求地址错误!");
		}
	}

	/**
	 * http请求
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.common.utils.http.HttpRequestSender.httpSend(...)
	 *
	 * @param url
	 * @param method
	 * @param urlParamsStr
	 * @param httpmethod
	 * @param requestProperties
	 * @param inputStr
	 * @param charSetName
	 * @return
	 */
	private static final String httpSend(String url, HttpMethod method, String urlParamsStr,
	        Map<String, String> requestProperties, String inputStr, String charSetName) {
		String msgId = DigestorManager.GUID();

		HttpURLConnection connection = null;
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;

		if (StringUtil.isNotBlank(urlParamsStr)) {
			url = String.format("%s?%s", url, urlParamsStr);
			System.out.println(url);
			System.out.println(inputStr);
		}

		try {
			URL _url = new URL(url);
			connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod(method.name());
			connection.setDoOutput(StringUtil.isNotBlank(inputStr));
			connection.setDoInput(true);
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(30000);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty(GreatFrameworkConst.DEFAULT_MESSAGE_ID_FIELD_NAME, msgId);

			if (requestProperties != null && !requestProperties.isEmpty()) {
				for (Entry<String, String> property : requestProperties.entrySet()) {
					connection.setRequestProperty(property.getKey(), property.getValue());
				}
			}

			// output
			if (StringUtil.isNotEmpty(inputStr)) {
				connection.getOutputStream().write(inputStr.getBytes(charSetName));
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}

			// input
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charSetName));
			String line;
			synchronized (result) {
				while ((line = in.readLine()) != null) {
					result.append(line);
					result.append(StringUtil.LINE);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return result.toString();
	}

	/**
	 * https请求
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.common.utils.http.HttpRequestSender.httpsSend(...)
	 *
	 * @param url
	 * @param method
	 * @param urlParamsStr
	 * @param httpmethod
	 * @param requestProperties
	 * @param inputStr
	 * @param charSetName
	 * @return
	 */
	private static final String httpsSend(String url, HttpMethod method, String urlParamsStr,
	        Map<String, String> requestProperties, String inputStr, String charSetName) {
		String msgId = DigestorManager.GUID();

		HttpsURLConnection connection = null;
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;

		if (StringUtil.isNotBlank(urlParamsStr)) {
			url = String.format("%s?%s", url, urlParamsStr);
		}

		try {
			URL _url = new URL(url);
			connection = (HttpsURLConnection) _url.openConnection();
			connection.setRequestMethod(method.name());
			connection.setSSLSocketFactory(HttpUtils.getSSLSocketFactory());
			connection.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					LOGGER.debug("https Request -- URL Host: " + hostname + " vs. " + session.getPeerHost());
					return true;
				}
			});
			connection.setDoOutput(StringUtil.isNotBlank(inputStr));
			connection.setDoInput(true);
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(30000);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty(GreatFrameworkConst.DEFAULT_MESSAGE_ID_FIELD_NAME, msgId);

			if (requestProperties != null && !requestProperties.isEmpty()) {
				for (Entry<String, String> property : requestProperties.entrySet()) {
					connection.setRequestProperty(property.getKey(), property.getValue());
				}
			}

			// output
			if (StringUtil.isNotEmpty(inputStr)) {
				connection.getOutputStream().write(inputStr.getBytes(charSetName));
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}

			// input
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charSetName));
			String line;
			synchronized (result) {
				while ((line = in.readLine()) != null) {
					result.append(line);
					result.append(StringUtil.LINE);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return result.toString();
	}

	public static void main(String[] args) throws GreatFrameworkException {

		String url = "http://10.37.149.157:8080/transport/rest/supportApi/config/getAppConfigs";
		String param = "";

		String send = HttpRequestSender.send(url, HttpMethod.POST, null, null, param,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		System.out.println(send);
	}
}
