/*
 * @title com.enn.greatframework.common.security.digest.DigestPoolManager.java
 *
 * @Copy.Right (c)2016.Jedidiah
 *
 * @date 2016年10月18日 下午2:28:32
 *
 * @author yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.digest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.GreatFrameworkException;
import com.enn.greatframework.common.lang.StringUtil;

/**
 * @ClassName DigestPoolManager
 * @Description TODO
 * @author yinshijie
 * @date 2016年10月18日 下午2:28:32
 *
 */
public class DigestorManager {

	private static final Integer GREAT_PASSWORD_RANDOM_SEED = Integer.valueOf(1);

	private static final Logger LOGGER = Logger.getLogger(DigestorManager.class);

	public Map<String, DigestorPool> pools = new HashMap<String, DigestorPool>();

	private DigestorManager() {
		init();
	}

	/**
	 * 初始化
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.init(...)
	 *
	 */
	private void init() {
		Digestor digestor_md5 = new Digestor(Digestor.ALGORITHM_NAME_MD5);
		digestor_md5.setCheakPool(true);
		digestor_md5.setCheckDelayTime(60 * 1000);
		digestor_md5.setCheckPeriodTime(120 * 1000);
		digestor_md5.setInitialSize(5);
		digestor_md5.setMaxActive(20);
		digestor_md5.setMaxIdle(10);
		digestor_md5.setMaxWait(1 * 1000);
		digestor_md5.setMinIdle(3);
		pools.put(digestor_md5.getAlgorithmName(), new DigestorPool(digestor_md5));

		Digestor digestor_sha1 = new Digestor(Digestor.ALGORITHM_NAME_SHA1);
		digestor_sha1.setCheakPool(true);
		digestor_sha1.setCheckDelayTime(60 * 1000);
		digestor_sha1.setCheckPeriodTime(30 * 60 * 1000);
		digestor_sha1.setInitialSize(2);
		digestor_sha1.setMaxActive(20);
		digestor_sha1.setMaxIdle(10);
		digestor_sha1.setMaxWait(1 * 1000);
		digestor_sha1.setMinIdle(3);
		pools.put(digestor_sha1.getAlgorithmName(), new DigestorPool(digestor_sha1));
	}

	/**
	 * 获取单例实例
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.
	 *       DigestorManager.getInstance(...)
	 *
	 * @return
	 */
	public static DigestorManager getInstance() {
		return Singtonle.instance;
	}

	/**
	 * 单例
	 *
	 * @ClassName Singtonle
	 * @Description TODO
	 * @author yinshijie
	 * @date 2016年10月19日 下午1:54:53
	 *
	 */
	private static class Singtonle {
		private static DigestorManager instance = new DigestorManager();
	}

	/**
	 * 添加资源池
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.
	 *       DigestorManager.addPool(...)
	 *
	 * @param digestor
	 */
	public void addPool(Digestor digestor) {
		pools.put(digestor.getAlgorithmName(), new DigestorPool(digestor));
	}

	/**
	 * 获取资源池
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.
	 *       DigestorManager.getPool(...)
	 *
	 * @param poolName
	 * @return
	 */
	protected DigestorPool getPool(String algorithmName) {
		DigestorPool pool = null;
		pool = pools.get(algorithmName);
		return pool;
	}

	/**
	 * 根据算法名称获取消息签名实例
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.getMessageDigest(...)
	 *
	 * @param algorithmName
	 * @return
	 */
	protected MessageDigest getMessageDigest(String algorithmName) {
		return getPool(algorithmName).getMessageDigest();
	}

	/**
	 * 对传入信息进行签名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.degest(...)
	 *
	 * @param message
	 * @param charSetName
	 * @return
	 */
	public static final String degest(String algorithmName, String message, String charSetName) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = getInstance().getMessageDigest(algorithmName);
			messageDigest.update(message.getBytes(charSetName));
			byte[] bs = messageDigest.digest();
			return StringUtil.parseByte2HexStr(bs);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				getInstance().getPool(algorithmName).returnDigest(messageDigest);
			} catch (GreatFrameworkException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 对传入信息进行签名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.degest(...)
	 *
	 * @param messages
	 * @param charSetName
	 * @return
	 */
	public static final String degest(String algorithmName, Object[] messages, String charSetName) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = getInstance().getMessageDigest(algorithmName);
			for (Object message : messages) {
				messageDigest.update(message.toString().getBytes(charSetName));
			}
			byte[] bs = messageDigest.digest();
			return StringUtil.parseByte2HexStr(bs);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				getInstance().getPool(algorithmName).returnDigest(messageDigest);
			} catch (GreatFrameworkException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 对传入信息使用默认字符集进行签名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.degest(...)
	 *
	 * @param message
	 * @return
	 */
	public static final String degest(String algorithmName, String message) {
		return degest(algorithmName, message, Charset.defaultCharset().name());
	}

	/**
	 * 对传入信息进行签名
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.degest(...)
	 *
	 * @param messages
	 * @return
	 */
	public static final String degest(String algorithmName, Object[] messages) {
		return degest(algorithmName, messages, Charset.defaultCharset().name());
	}

	/**
	 * 对文件进行签名算法
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.degest(...)
	 *
	 * @param algorithmName
	 * @param file
	 * @return
	 */
	public static final String degest(String algorithmName, File file) {
		MessageDigest messageDigest = null;
		FileInputStream fileInputStream = null;
		FileChannel ch = null;
		MappedByteBuffer byteBuffer = null;
		try {
			messageDigest = getInstance().getMessageDigest(algorithmName);
			fileInputStream = new FileInputStream(file);
			ch = fileInputStream.getChannel();
			byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			messageDigest.update(byteBuffer);
			return StringUtil.parseByte2HexStr(messageDigest.digest());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				byteBuffer = null;
				if (ch != null) {
					ch.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
					fileInputStream = null;
				}
				getInstance().getPool(algorithmName).returnDigest(messageDigest);
			} catch (IOException | GreatFrameworkException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 生成一个GUID
	 *
	 * @Description TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.GUID(...)
	 *
	 * @return
	 */
	public static final String GUID() {
		return degest(Digestor.ALGORITHM_NAME_MD5, String.valueOf(System.nanoTime())).toUpperCase();
	}

	/**
	 * 根据原字符串加密获取密文
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.greatPasswordCreate(...)
	 *
	 * @param source
	 * @return
	 */
	public static final String greatPasswordCreate(String sourceStr) {
		MessageDigest messageDigest = null;
		try {
			SecureRandom localSecureRandom = new SecureRandom();
			byte[] randomBytes = new byte[GREAT_PASSWORD_RANDOM_SEED.intValue()];
			localSecureRandom.nextBytes(randomBytes);

			messageDigest = getInstance().getMessageDigest(Digestor.ALGORITHM_NAME_MD5);
			messageDigest.update(randomBytes);
			messageDigest.update(sourceStr.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));
			byte[] digestBytes = messageDigest.digest();

			byte[] resultBytes = new byte[randomBytes.length + digestBytes.length];
			System.arraycopy(randomBytes, 0, resultBytes, 0, 1);
			System.arraycopy(digestBytes, 0, resultBytes, 1, digestBytes.length);

			return StringUtil.parseByte2HexStr(resultBytes).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				getInstance().getPool(Digestor.ALGORITHM_NAME_MD5).returnDigest(messageDigest);
			} catch (GreatFrameworkException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 根据原字符串加密获取密文
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.digest.DigestorManager.greatPasswordValidate(...)
	 *
	 * @param sourceStr 明文密码
	 * @param digestStr 加密后密文
	 * @return
	 */
	public static final boolean greatPasswordValidate(String sourceStr, String digestStr) {
		MessageDigest messageDigest = null;
		try {
			byte[] digestBytes = StringUtil.parseHexStr2Byte(digestStr);
			byte[] randomBytes = new byte[1];
			System.arraycopy(digestBytes, 0, randomBytes, 0, 1);

			messageDigest = getInstance().getMessageDigest(Digestor.ALGORITHM_NAME_MD5);
			messageDigest.update(randomBytes);
			messageDigest.update(sourceStr.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME));
			byte[] realDigestBytes = messageDigest.digest();

			byte[] arrayOfByte4 = new byte[digestBytes.length - 1];
			System.arraycopy(digestBytes, 1, arrayOfByte4, 0, arrayOfByte4.length);
			return Arrays.equals(realDigestBytes, arrayOfByte4);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			return Boolean.FALSE;
		} finally {
			try {
				getInstance().getPool(Digestor.ALGORITHM_NAME_MD5).returnDigest(messageDigest);
			} catch (GreatFrameworkException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(greatPasswordCreate("ysj850620"));
		System.out.println(greatPasswordValidate("ysj850620", "C391DA267256CADCF7DCC749E771E2DE19"));
	}
}
