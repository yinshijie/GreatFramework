/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.enn.greatframework.common.security.asymmetric.RSA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.io.FileUtils;
import com.enn.greatframework.common.lang.StringUtil;

public abstract class RSAUtils {
	private static final Logger LOGGER = Logger.getLogger(RSAUtils.class.getName());
	private static final String RSA_PAIR_FILENAME = "__RSA_PAIR.txt";
	private static final int KEY_SIZE = 1024;
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
	private static KeyPairGenerator keyPairGen = null;
	private static KeyFactory keyFactory = null;
	private static KeyPair oneKeyPair = null;
	private static InputStream rsaPairInputStream = null;

	/**
	 * 生成密钥对
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.generateKeyPair(...)
	 *
	 * @return
	 */
	public static synchronized KeyPair generateKeyPair() {
		return generateKeyPair(new Date().toString().getBytes());
	}

	/**
	 * 生成密钥对
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.generateKeyPair(...)
	 *
	 * @param seed
	 * @return
	 */
	public static synchronized KeyPair generateKeyPair(byte[] seed) {
		try {
			keyPairGen.initialize(KEY_SIZE, new SecureRandom(seed));
			oneKeyPair = keyPairGen.generateKeyPair();
			saveKeyPair(oneKeyPair);
			return oneKeyPair;
		} catch (InvalidParameterException localInvalidParameterException) {
			LOGGER.error("KeyPairGenerator does not support a key length of 1024.", localInvalidParameterException);
		} catch (NullPointerException localNullPointerException) {
			LOGGER.error("RSAUtils#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.",
			        localNullPointerException);
		}
		return null;
	}

	private static void saveKeyPair(KeyPair paramKeyPair) {
		FileOutputStream localFileOutputStream = null;
		ObjectOutputStream localObjectOutputStream = null;
		File rsaPairSaveFile = null;
		try {
			rsaPairSaveFile = new File(RSA_PAIR_FILENAME);
			localFileOutputStream = new FileOutputStream(rsaPairSaveFile);
			localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
			localObjectOutputStream.writeObject(paramKeyPair);
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
		}
	}

	public static KeyPair getKeyPair() {
		if (oneKeyPair != null) {
			return oneKeyPair;
		}
		return readKeyPair();
	}

	/**
	 * 读取密钥对
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.readKeyPair(...)
	 *
	 * @return
	 */
	private static KeyPair readKeyPair() {
		ObjectInputStream localObjectInputStream = null;
		try {
			rsaPairInputStream = RSAUtils.class.getClassLoader().getResourceAsStream(RSA_PAIR_FILENAME);
			localObjectInputStream = new ObjectInputStream(rsaPairInputStream);
			oneKeyPair = (KeyPair) localObjectInputStream.readObject();
			KeyPair localKeyPair = oneKeyPair;
			return localKeyPair;
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			FileUtils.close(localObjectInputStream);
			FileUtils.close(rsaPairInputStream);
		}
		return null;
	}

	/**
	 * 使用模和指数生成RSA公钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding 】
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.generateRSAPublicKey(...)
	 *
	 * @param modulusBytes
	 * @param publicExponentBytes
	 * @return
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulusBytes, byte[] publicExponentBytes) {
		RSAPublicKeySpec localRSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(modulusBytes),
		        new BigInteger(publicExponentBytes));
		try {
			return ((RSAPublicKey) keyFactory.generatePublic(localRSAPublicKeySpec));
		} catch (InvalidKeySpecException localInvalidKeySpecException) {
			LOGGER.error("RSAPublicKeySpec is unavailable.", localInvalidKeySpecException);
		} catch (NullPointerException localNullPointerException) {
			LOGGER.error("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.",
			        localNullPointerException);
		}
		return null;
	}

	/**
	 * 使用模和指数生成RSA私钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA/None/NoPadding】
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.generateRSAPrivateKey(...)
	 *
	 * @param modulusBytes
	 * @param privateExponentBytes
	 * @return
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulusBytes, byte[] privateExponentBytes) {
		RSAPrivateKeySpec localRSAPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulusBytes),
		        new BigInteger(privateExponentBytes));
		try {
			return ((RSAPrivateKey) keyFactory.generatePrivate(localRSAPrivateKeySpec));
		} catch (InvalidKeySpecException localInvalidKeySpecException) {
			LOGGER.error("RSAPrivateKeySpec is unavailable.", localInvalidKeySpecException);
		} catch (NullPointerException localNullPointerException) {
			LOGGER.error("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.",
			        localNullPointerException);
		}
		return null;
	}

	public static RSAPrivateKey getRSAPrivateKey(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString2 == null)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
				        "hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
			}
			return null;
		}
		byte[] arrayOfByte1 = null;
		byte[] arrayOfByte2 = null;
		try {
			arrayOfByte1 = StringUtil.parseHexStr2Byte(paramString1);
			arrayOfByte2 = StringUtil.parseHexStr2Byte(paramString2);
		} catch (Exception localException) {
			LOGGER.error("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
		}
		if ((arrayOfByte1 != null) && (arrayOfByte2 != null)) {
			return generateRSAPrivateKey(arrayOfByte1, arrayOfByte2);
		}
		return null;
	}

	public static RSAPublicKey getRSAPublidKey(String paramString1, String paramString2) {
		if (!StringUtil.checkParams(paramString1, paramString1)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
			}
			return null;
		}
		byte[] arrayOfByte1 = null;
		byte[] arrayOfByte2 = null;
		try {
			arrayOfByte1 = StringUtil.parseHexStr2Byte(paramString1);
			arrayOfByte2 = StringUtil.parseHexStr2Byte(paramString2);
		} catch (Exception localException) {
			LOGGER.error("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
		}
		if ((arrayOfByte1 != null) && (arrayOfByte2 != null)) {
			return generateRSAPublicKey(arrayOfByte1, arrayOfByte2);
		}
		return null;
	}

	/**
	 * 使用公钥进行加密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.encrypt(...)
	 *
	 * @param publicKey
	 * @param sourceBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] sourceBytes) throws Exception {
		Cipher localCipher = Cipher.getInstance(AsymmetricAlgoritom.ALGORITOM_NAME_RSA, DEFAULT_PROVIDER);
		localCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return localCipher.doFinal(sourceBytes);
	}

	/**
	 * 使用私钥进行解密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.decrypt(...)
	 *
	 * @param paramPrivateKey
	 * @param paramArrayOfByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[] sourceBytes) throws Exception {
		Cipher localCipher = Cipher.getInstance(AsymmetricAlgoritom.ALGORITOM_NAME_RSA, DEFAULT_PROVIDER);
		localCipher.init(Cipher.DECRYPT_MODE, privateKey);
		return localCipher.doFinal(sourceBytes);
	}

	/**
	 * 使用公钥进行加密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.encryptString(...)
	 *
	 * @param paramPublicKey
	 * @param paramString
	 * @return
	 */
	public static String encryptString(PublicKey publicKey, String sourceString) {
		if ((publicKey == null) || (sourceString == null)) {
			return null;
		}
		byte[] arrayOfByte1 = sourceString.getBytes();
		try {
			byte[] arrayOfByte2 = encrypt(publicKey, arrayOfByte1);
			return new String(StringUtil.parseByte2HexStr(arrayOfByte2));
		} catch (Exception localException) {
			LOGGER.error(localException.getCause().getMessage());
		}
		return null;
	}

	/**
	 * 使用默认公钥私钥进行加密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.encryptString(...)
	 *
	 * @param paramString
	 * @return
	 */
	public static String encryptString(String sourceString) {
		if (sourceString == null) {
			return null;
		}
		try {
			byte[] arrayOfByte1 = sourceString.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME);
			KeyPair localKeyPair = getKeyPair();
			byte[] arrayOfByte2 = encrypt(localKeyPair.getPublic(), arrayOfByte1);
			return new String(StringUtil.parseByte2HexStr(arrayOfByte2));
		} catch (NullPointerException localNullPointerException) {
			LOGGER.error("keyPair cannot be null.");
		} catch (Exception localException) {
			LOGGER.error(localException.getCause().getMessage());
		}
		return null;
	}

	/**
	 * 使用私钥进行解密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.decryptString(...)
	 *
	 * @param paramPrivateKey
	 * @param paramString
	 * @return
	 */
	public static String decryptString(PrivateKey privateKey, String sourceString) {
		if (privateKey == null || StringUtil.isBlank(sourceString)) {
			return null;
		}
		try {
			byte[] arrayOfByte1 = StringUtil.parseHexStr2Byte(sourceString);
			byte[] arrayOfByte2 = decrypt(privateKey, arrayOfByte1);
			return new String(arrayOfByte2);
		} catch (Exception localException) {
			LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s",
			        new Object[] { sourceString, localException.getCause().getMessage() }));
		}
		return null;
	}

	/**
	 * 使用默认私钥进行解密
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.decryptString(...)
	 *
	 * @param paramString
	 * @return
	 */
	public static String decryptString(String sourceString) {
		if (StringUtil.isBlank(sourceString)) {
			return null;
		}
		KeyPair localKeyPair = getKeyPair();
		try {
			byte[] arrayOfByte1 = StringUtil.parseHexStr2Byte(sourceString);
			byte[] arrayOfByte2 = decrypt(localKeyPair.getPrivate(), arrayOfByte1);
			return new String(arrayOfByte2, "UTF-8");
		} catch (NullPointerException localNullPointerException) {
			localNullPointerException.printStackTrace();
		} catch (Exception localException) {
			localException.printStackTrace();
			LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s",
			        new Object[] { sourceString, localException.getMessage() }));
		}
		return null;
	}

	/**
	 * 获取默认公钥
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.getDefaultPublicKey(...)
	 *
	 * @return
	 */
	public static RSAPublicKey getDefaultPublicKey() {
		KeyPair localKeyPair = getKeyPair();
		if (localKeyPair != null) {
			return ((RSAPublicKey) localKeyPair.getPublic());
		}
		return null;
	}

	/**
	 * 获取默认私钥
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.asymmetric.RSA.RSAUtils.getDefaultPrivateKey(...)
	 *
	 * @return
	 */
	public static RSAPrivateKey getDefaultPrivateKey() {
		KeyPair localKeyPair = getKeyPair();
		if (localKeyPair != null) {
			return ((RSAPrivateKey) localKeyPair.getPrivate());
		}
		return null;
	}

	static {
		try {
			keyPairGen = KeyPairGenerator.getInstance(AsymmetricAlgoritom.ALGORITOM_NAME_RSA, DEFAULT_PROVIDER);
			keyFactory = KeyFactory.getInstance(AsymmetricAlgoritom.ALGORITOM_NAME_RSA, DEFAULT_PROVIDER);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			LOGGER.error(localNoSuchAlgorithmException.getMessage());
		}
	}

	public static void main(String[] paramArrayOfString) throws UnsupportedEncodingException, Exception {
		// RSAUtils.generateKeyPair("!9I9GreaTgAs~..".getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));

		String str = "ysj850620";
		RSAPrivateKey privateKey = RSAUtils.getDefaultPrivateKey();
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
		String strEncode = StringUtil
		        .parseByte2HexStr(RSAUtils.encrypt(publicKey, str.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME)));
		System.out.println(strEncode);

		String strEncode2 = RSAUtils.encryptString(str);
		System.out.println(strEncode2);

		String strDecode = new String(RSAUtils.decrypt(privateKey, StringUtil.parseHexStr2Byte(strEncode)),
		        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		System.out.println(strDecode);

		String strDecode2 = new String(RSAUtils.decryptString(strEncode));
		System.out.println(strDecode2);

		System.out.println(StringUtil.parseByte2HexStr(publicKey.getEncoded()));
		System.out.println(Base64Utils.encodeToString(publicKey.getEncoded()));
	}
}