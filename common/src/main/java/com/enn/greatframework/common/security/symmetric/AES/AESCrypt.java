/*
 * @title com.enn.greatframework.common.security.symmetric.AES.AESCrypt.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年3月9日 上午8:56:13
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.symmetric.AES;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.lang.StringUtil;

/**
 * @ClassName AESCrypt
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月9日 上午8:56:13
 *
 */
public class AESCrypt implements Serializable {

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = 4595742662827218175L;

	private static final Logger LOGGER = Logger.getLogger(AESCrypt.class);

	private String key;
	private Cipher encryptCipher;
	private Cipher decryptCipher;

	protected AESCrypt(String key, Cipher encryptCipher, Cipher decryptCipher) {
		this.key = key;
		this.encryptCipher = encryptCipher;
		this.decryptCipher = decryptCipher;
	}

	/**
	 * 加密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.encrypt(...)
	 *
	 * @param byteCollector
	 * @return
	 */
	public byte[] encrypt(byte[] byteCollector) {
		try {
			return encryptCipher.doFinal(byteCollector);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 加密返回16进制字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.encryptHexStr(...)
	 *
	 * @param byteCollector
	 * @return
	 */
	public String encryptHexStr(byte[] byteCollector) {
		return StringUtil.parseByte2HexStr(encrypt(byteCollector));
	}

	/**
	 * 加密返回16进制字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.encryptHexStr(...)
	 *
	 * @param byteCollector
	 * @return
	 */
	public String encryptHexStr(String str) {
		try {
			return StringUtil.parseByte2HexStr(encrypt(str.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME)));
		} catch (UnsupportedEncodingException e) {
			return StringUtil.parseByte2HexStr(encrypt(str.getBytes()));
		}
	}

	/**
	 * 加密返回base64字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.encryptBase64Str(...)
	 *
	 * @param byteCollector
	 * @return
	 */
	public String encryptBase64Str(byte[] byteCollector) {
		try {
			return new String(Base64.encodeBase64(encrypt(byteCollector)), GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return new String(Base64.encodeBase64(encrypt(byteCollector)));
		}
	}

	/**
	 * 加密返回base64字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.encryptBase64Str(...)
	 *
	 * @param byteCollector
	 * @return
	 */
	public String encryptBase64Str(String str) {
		try {
			return new String(Base64.encodeBase64(encrypt(str.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME))));
		} catch (UnsupportedEncodingException e) {
			return new String(Base64.encodeBase64(encrypt(str.getBytes())));
		}
	}

	/**
	 * 解密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.decrypt(...)
	 *
	 * @param byteEncrypted
	 * @return
	 */
	public byte[] decrypt(byte[] byteEncrypted) {
		try {
			return decryptCipher.doFinal(byteEncrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 解密16进制字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.decryptHexStr(...)
	 *
	 * @param byteEncrypted
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decryptHexStr(String hexStrEncrypted) {
		try {
			return new String(decrypt(StringUtil.parseHexStr2Byte(hexStrEncrypted)),
			        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return new String(decrypt(StringUtil.parseHexStr2Byte(hexStrEncrypted)));
		}
	}

	/**
	 * 解密base64字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCrypt.decryptBase64Str(...)
	 *
	 * @param base64StrEncrypted
	 * @return
	 */
	public String decryptBase64Str(String base64StrEncrypted) {
		try {
			return new String(decrypt(Base64.decodeBase64(base64StrEncrypted.getBytes())),
			        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return new String(decrypt(Base64.decodeBase64(base64StrEncrypted.getBytes())));
		}
	}

	/**
	 * @return the key
	 * @since JDK1.5
	 */
	public String getKey() {
		return key;
	}
}
