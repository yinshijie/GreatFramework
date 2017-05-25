/*
 * @title com.enn.greatframework.common.http.sender.GreatRequestSender.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月1日 上午11:44:04
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.sender;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpMethod;
import org.springframework.util.Base64Utils;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.GreatFrameworkException;
import com.enn.greatframework.common.http.context.RequestContent;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.sender.param.GreatSignatureParam;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.random.RandomCharGenerate;
import com.enn.greatframework.common.security.symmetric.AES.AESAlgorithmMode;
import com.enn.greatframework.common.security.symmetric.AES.AESCrypt;
import com.enn.greatframework.common.security.symmetric.AES.AESCryptFactory;

/**
 * @ClassName GreatRequestSender
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月1日 上午11:44:04
 *
 */
public class GreatRequestSender {

	/**
	 * 加密方式-不加密；请求方式：POST
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.noEncodePost(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent noEncodePost(String url, String appToken, RequestContent content)
	        throws GreatFrameworkException {
		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());

		String result = HttpRequestSender.send(url, HttpMethod.POST, signatureParam.toString(), null,
		        content.toString(), GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	/**
	 * 加密方式-不加密；请求方式：GET
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.noEncodeGet(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent noEncodeGet(String url, String appToken, RequestContent content)
	        throws GreatFrameworkException {
		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());
		String urlParams = signatureParam.toString() + "&data=" + content.toString();

		String result = HttpRequestSender.send(url, HttpMethod.GET, urlParams, null, null,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	/**
	 * 加密方式-AES；请求方式：POST
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.aesEncodePost(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @param cryptKey
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent aesEncodePost(String url, String appToken, RequestContent content,
	        String cryptKey) throws GreatFrameworkException {
		AESCrypt cryptor = AESCryptFactory.getInstance(AESAlgorithmMode.AES_CBC_PKCS5, cryptKey);
		String requestData = cryptor.encryptBase64Str(content.toString());

		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());

		String result = HttpRequestSender.send(url, HttpMethod.POST, signatureParam.toString(), null, requestData,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	/**
	 * 加密方式-AES；请求方式：GET
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.aesEncodeGet(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @param cryptKey
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent aesEncodeGet(String url, String appToken, RequestContent content,
	        String cryptKey) throws GreatFrameworkException {
		AESCrypt cryptor = AESCryptFactory.getInstance(AESAlgorithmMode.AES_CBC_PKCS5, cryptKey);
		String requestData = cryptor.encryptBase64Str(content.toString());

		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());
		String urlParams = signatureParam.toString() + "&data=" + requestData;

		String result = HttpRequestSender.send(url, HttpMethod.GET, urlParams, null, null,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	/**
	 * 加密方式-BASE64；请求方式：POST
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.base64EncodePost(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent base64EncodePost(String url, String appToken, RequestContent content)
	        throws GreatFrameworkException {
		String requestData = StringUtil.EMPTY;
		try {
			requestData = Base64Utils
			        .encodeToUrlSafeString(content.toString().getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));
		} catch (UnsupportedEncodingException e) {
			throw new GreatFrameworkException(e);
		}
		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());

		String result = HttpRequestSender.send(url, HttpMethod.POST, signatureParam.toString(), null, requestData,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	/**
	 * 加密方式-BASE64；请求方式：GET
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.GreatRequestSender.base64EncodeGet(...)
	 *
	 * @param url
	 * @param appToken
	 * @param data
	 * @return
	 * @throws GreatFrameworkException
	 */
	public static final ResponseContent base64EncodeGet(String url, String appToken, RequestContent content)
	        throws GreatFrameworkException {
		String requestData = StringUtil.EMPTY;
		try {
			requestData = Base64Utils
			        .encodeToUrlSafeString(content.toString().getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));
		} catch (UnsupportedEncodingException e) {
			throw new GreatFrameworkException(e);
		}
		GreatSignatureParam signatureParam = new GreatSignatureParam(appToken,
		        String.valueOf(System.currentTimeMillis()), RandomCharGenerate.getRandomChars(6), content.toString());
		String urlParams = signatureParam.toString() + "&data=" + requestData;

		String result = HttpRequestSender.send(url, HttpMethod.GET, urlParams, null, null,
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		return ResponseContent.fromJsonStr(result);
	}

	public static void main(String[] args) throws GreatFrameworkException, UnsupportedEncodingException {
		try {
//			String url = "http://10.23.76.223:8180/auth/customer/login";
//			RequestContent content = new RequestContent();
//			content.addParam("sessionId", "1111111111111111111111111");
//			content.addParam("loginName", "18613982225");
//			content.addParam("loginPwd",
//			        Base64Utils.encodeToString("ysj850620".getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME)));
//			ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
//			System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/getCustomerBySessionId";
			// RequestContent content = new RequestContent();
			// content.addParam("sessionId", "1111111111111111111111111");
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/createCustomer";
			// RequestContent content = new RequestContent();
			// Map<String, String> params = new HashMap<>();
			// params.put("customerName", "yin_shj");
			// params.put("customerTitle", "尹世杰");
			// params.put("customerPassword", DigestorManager.greatPasswordCreate("ysj850620"));
			// params.put("customerMobile", "18613982225");
			// params.put("customerEmail", "920251@qq.com");
			// params.put("customerSecMobile", "18613982225");
			// params.put("customerSecEmail", "920251@qq.com");
			// content.addParam("customer", params);
			// String response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content.toString());
			// System.out.println(response);

			// String url = "http://10.23.76.223:8180/auth/customer/getCustomerBySessionId";
			// RequestContent content = new RequestContent();
			// content.addParam("sessionId", "07208c7f-30e2-49b0-bece-e74e64814748");
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/getChildCustomers";
			// RequestContent content = new RequestContent();
			// content.addParam("customerId", "072F5CF356FC4C3DA48391915D3FC93E");
			// content.addParam("pageNo", 1);
			// content.addParam("pageSize", 20);
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/searchCustomer";
			// RequestContent content = new RequestContent();
			// content.addParam("searchWord", "222");
			// content.addParam("pageNo", 1);
			// content.addParam("pageSize", 20);
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/getAllCustomers";
			// RequestContent content = new RequestContent();
			// content.addParam("pageNo", 1);
			// content.addParam("pageSize", 20);
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			String url = "http://10.23.76.223:8180/auth/customer/getCustomerInfo";
			RequestContent content = new RequestContent();
			content.addParam("customerId", "072F5CF356FC4C3DA48391915D3FC93E");
			ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/updateCustomer";
			// RequestContent content = new RequestContent();
			// Map<String, String> params = new HashMap<>();
			// params.put("customerId", "C231F2A12A094B74988D95161940DAB6");
			// params.put("customerEmail", "yinshijie@enn.cn");
			// params.put("customerSecEmail", "yinshijie@enn.cn");
			// params.put("customerState", "1");
			// content.addParam("customer", params);
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());

			// String url = "http://10.23.76.223:8180/auth/customer/logout";
			// RequestContent content = new RequestContent();
			// content.addParam("sessionId", "5b3ef963-3bb4-4b8c-962d-92b2b017ca47");
			// ResponseContent response = noEncodePost(url, "eWqc1A7O41MjliCANgiy2pMF", content);
			// System.out.println(response.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
