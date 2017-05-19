/*
 * @title com.enn.greatframework.common.http.utils.HttpUtils.java
 *
 * @Copy.Right (c)2015.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2015年12月22日 下午5:07:47
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;

/**
 * HTTP工具类
 * @ClassName HttpUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月25日 下午1:43:18
 *
 */
public class HttpUtils {

	private static final Logger LOGGER = Logger.getLogger(HttpUtils.class);

	/**
	 * HTTP_URL参数分隔符
	 */
	public static final String URL_PARAMS_SEPARATOR = "&";

	/**
	 * CONTENT_TYPE值：HTML格式
	 */
	public static final String CONTENT_TYPE_HTML = "text/html";
	/**
	 * CONTENT_TYPE值：纯文本
	 */
	public static final String CONTENT_TYPE_PLAIN = "text/plain";
	/**
	 * CONTENT_TYPE值：XML格式
	 */
	public static final String CONTENT_TYPE_XML = "text/xml";
	/**
	 * CONTENT_TYPE值：JSON格式
	 */
	public static final String CONTENT_TYPE_JSON = "application/json";
	/**
	 * CONTENT_TYPE值：二进制流数据
	 */
	public static final String CONTENT_TYPE_STREAM = "application/octet-stream";

	/**
	 * 将MAP格式的参数集合转换为httpUrl参数字符串
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.jedidiah.framework.common.web.HttpUtils.
	 *       convertMap2UrlParam(...)
	 *
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String convertMap2UrlParam(Map<String, String> paramMap) throws UnsupportedEncodingException {
		StringBuffer paramStrBuf = null;
		if (paramMap != null) {
			for (Entry<String, String> param : paramMap.entrySet()) {
				if (paramStrBuf == null) {
					paramStrBuf = new StringBuffer();
				} else {
					paramStrBuf.append(URL_PARAMS_SEPARATOR);
				}
				paramStrBuf.append(param.getKey());
				paramStrBuf.append("=");
				paramStrBuf.append(URLEncoder.encode(param.getValue(), Charset.defaultCharset().name()));
			}
		}
		return paramStrBuf != null ? paramStrBuf.toString() : null;
	}

	private static final String _boundaryPrefix = "-----------------------------";

	public static final String generateBoundary() {
		return _boundaryPrefix + Long.toHexString(System.nanoTime());
	}

	private static TrustManager[] trustManagers = { new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	} };

	private static SSLSocketFactory sslSocketFactory = null;

	/**
	 * 获取证书信任管理器
	 *
	 * @Description TODO
	 * @Call com.enn.howmuch.jedidiah.framework.common.web.HttpUtils.
	 *       getTrustManager(...)
	 *
	 * @return
	 */
	public static final SSLSocketFactory getSSLSocketFactory() {
		if (sslSocketFactory == null) {
			try {
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, trustManagers, new java.security.SecureRandom());
				sslSocketFactory = sslContext.getSocketFactory();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return sslSocketFactory;
	}

	private static ProtocolSocketFactory protocolSocketFactory = null;

	public static final ProtocolSocketFactory getProtocolSocketFactory() {

		if (protocolSocketFactory == null) {
			protocolSocketFactory = new ProtocolSocketFactory() {

				private SSLContext sslcontext = null;

				private SSLContext getSSLContext() {
					if (this.sslcontext == null) {
						this.sslcontext = createSSLContext();
					}
					return this.sslcontext;
				}

				private SSLContext createSSLContext() {
					SSLContext sslcontext = null;
					try {
						sslcontext = SSLContext.getInstance("SSL");
						sslcontext.init(null, trustManagers, new java.security.SecureRandom());
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (KeyManagementException e) {
						e.printStackTrace();
					}
					return sslcontext;
				}

				@Override
				public Socket createSocket(String host, int port, InetAddress localAddress, int localPort,
						HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
					if (params == null) {
						throw new IllegalArgumentException("Parameters may not be null");
					}
					int timeout = params.getConnectionTimeout();
					SocketFactory socketfactory = getSSLContext().getSocketFactory();
					if (timeout == 0) {
						return socketfactory.createSocket(host, port, localAddress, localPort);
					} else {
						Socket socket = socketfactory.createSocket();
						SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
						SocketAddress remoteaddr = new InetSocketAddress(host, port);
						socket.bind(localaddr);
						socket.connect(remoteaddr, timeout);
						return socket;
					}
				}

				@Override
				public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
						throws IOException, UnknownHostException {
					return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
				}

				@Override
				public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
					return getSSLContext().getSocketFactory().createSocket(host, port);
				}
			};
		}
		return protocolSocketFactory;
	}
}
