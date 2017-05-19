/*
 * @title com.enn.greatframework.common.security.symmetric.AES.AESCryptFactory.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年3月9日 上午8:56:27
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.symmetric.AES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.enn.greatframework.common.GreatFrameworkConst;

/**
 * @ClassName AESCryptFactory
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月9日 上午8:56:27
 *
 */
public class AESCryptFactory {

	private static final Logger LOGGER = Logger.getLogger(AESCryptFactory.class);

	public static final String DEFAULT_CRYPT_KEY = "!919GreAtGAs!919";

	/**
	 * AES加密工具缓存
	 */
	private static final Map<AESAlgorithmMode, Map<String, AESCrypt>> aesCryptBuf = new HashMap<AESAlgorithmMode, Map<String, AESCrypt>>();

	private AESCryptFactory() {
	}

	/**
	 * 获取加解密工具实例
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCryptFactory.getInstance(...)
	 *
	 * @param algorithmMode
	 * @param key
	 * @return
	 */
	public static final AESCrypt getInstance(AESAlgorithmMode algorithmMode, String key) {
		if (aesCryptBuf.containsKey(algorithmMode)) {
			Map<String, AESCrypt> buf = aesCryptBuf.get(algorithmMode);
			if (buf.containsKey(key)) {
				return buf.get(key);
			} else {
				AESCrypt instance = _getInstance(algorithmMode, key);
				buf.put(key, instance);
				return instance;
			}
		} else {
			Map<String, AESCrypt> buf = new HashMap<String, AESCrypt>();
			AESCrypt instance = _getInstance(algorithmMode, key);
			buf.put(key, instance);
			aesCryptBuf.put(algorithmMode, buf);
			return instance;
		}
	}

	/**
	 * 获取加解密工具实例
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.symmetric.AES.AESCryptFactory._getInstance(...)
	 *
	 * @param algorithmMode
	 * @param key
	 * @return
	 */
	private static final AESCrypt _getInstance(AESAlgorithmMode algorithmMode, String key) {
		byte[] keyBytes;
		try {
			keyBytes = key.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		} catch (UnsupportedEncodingException e1) {
			keyBytes = key.getBytes();
		}
		Cipher encryptCipher;
		Cipher decryptCipher;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec iv = new IvParameterSpec("0000000000000000".getBytes(), 0, 16);

			encryptCipher = Cipher.getInstance(algorithmMode.getModeName());
			encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			decryptCipher = Cipher.getInstance(algorithmMode.getModeName());
			decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

			AESCrypt aesCrypt = new AESCrypt(key, encryptCipher, decryptCipher);
			return aesCrypt;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
		        | InvalidAlgorithmParameterException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static void main(String[] args) {
		AESCrypt crypt = getInstance(AESAlgorithmMode.AES_CBC_PKCS5, DEFAULT_CRYPT_KEY);

		String source = "尹世杰";
		System.out.println(crypt.encryptHexStr(source));
		System.out.println(crypt.encryptBase64Str(source));

		System.out.println(crypt.decryptHexStr("9d4de5300842ebf3e720ee57560c5db5"));
		System.out.println(crypt.decryptBase64Str("nU3lMAhC6/PnIO5XVgxdtQ=="));
	}
}
