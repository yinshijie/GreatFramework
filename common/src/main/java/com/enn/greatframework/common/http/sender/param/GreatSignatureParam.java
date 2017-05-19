/*
 * @title com.enn.greatframework.common.http.sender.param.GreatSignatureParam.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月1日 上午11:47:02
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.http.sender.param;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.enn.greatframework.common.http.utils.HttpUtils;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.Digestor;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * 验签参数集
 * @ClassName GreatSignatureParam
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月1日 上午11:47:02
 *
 */
public class GreatSignatureParam implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(GreatSignatureParam.class);

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = -2210834226666228752L;

	/**
	 * 程序调用token
	 */
	private String appToken;
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 随机数
	 */
	private String nonce;
	/**
	 * 签名
	 */
	private String signature;
	/**
	 * 请求内容
	 */
	private String data;

	private String encodeType = "0";

	private String encodeKey;

	public GreatSignatureParam(String appToken, String timestamp, String nonce, String data) {
		this.appToken = appToken;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.data = data;
	}

	/**
	 * @return the appToken
	 * @since JDK1.5
	 */
	public String getAppToken() {
		return appToken;
	}

	/**
	 * @param appToken the appToken to set
	 * @since JDK1.5
	 */
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	/**
	 * @return the timestamp
	 * @since JDK1.5
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 * @since JDK1.5
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the nonce
	 * @since JDK1.5
	 */
	public String getNonce() {
		return nonce;
	}

	/**
	 * @param nonce the nonce to set
	 * @since JDK1.5
	 */
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	/**
	 * @return the data
	 * @since JDK1.5
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 * @since JDK1.5
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the encodeType
	 * @since JDK1.5
	 */
	public String getEncodeType() {
		return encodeType;
	}

	/**
	 * @param encodeType the encodeType to set
	 * @since JDK1.5
	 */
	public void setEncodeType(String encodeType) {
		this.encodeType = encodeType;
	}

	/**
	 * @return the encodeKey
	 * @since JDK1.5
	 */
	public String getEncodeKey() {
		return encodeKey;
	}

	/**
	 * @param encodeKey the encodeKey to set
	 * @since JDK1.5
	 */
	public void setEncodeKey(String encodeKey) {
		this.encodeKey = encodeKey;
	}

	/**
	 * @return the signature
	 * @since JDK1.5
	 */
	private String getSignature() {
		if (StringUtil.isBlank(signature)) {
			String[] verifyStrs = { appToken, timestamp, nonce, data };
			Arrays.sort(verifyStrs);
			StringBuffer verifyStrBuf = new StringBuffer();
			for (String tempVerifyStr : verifyStrs) {
				verifyStrBuf.append(tempVerifyStr);
			}
			String verifyStr = verifyStrBuf.toString();
			LOGGER.debug(String.format("服务端签名参数值排序后:%s", verifyStr));
			String digestVerifyStr = DigestorManager.degest(Digestor.ALGORITHM_NAME_SHA1, verifyStr);
			LOGGER.debug(String.format("服务端计算签名=%s", digestVerifyStr));
			this.signature = digestVerifyStr;
		}
		return signature;
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
		try {
			if (StringUtil.checkParams(appToken, timestamp, nonce, getSignature())) {
				Map<String, String> signatureParams = new HashMap<String, String>();
				signatureParams.put("appToken", appToken);
				signatureParams.put("timestamp", timestamp);
				signatureParams.put("nonce", nonce);
				signatureParams.put("signature", getSignature());
				return HttpUtils.convertMap2UrlParam(signatureParams);
			} else {
				throw new IllegalArgumentException("签名参数异常");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return StringUtil.EMPTY;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("签名参数转换失败", e);
			return StringUtil.EMPTY;
		}
	}

	/**
	 * 签名校验
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.http.sender.param.GreatSignatureParam.verifySignature(...)
	 *
	 * @return
	 */
	public boolean verifySignature(String signature) {
		return getSignature().equalsIgnoreCase(signature);
	}
}
