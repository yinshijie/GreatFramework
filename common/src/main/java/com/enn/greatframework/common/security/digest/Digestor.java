/*
 * @title com.enn.greatframework.common.security.digest.MessageDigestFactory.java
 * 
 * @Copy.Right (c)2016.Jedidiah
 * 
 * @date 2016年10月14日 下午2:29:12
 * 
 * @author yinshijie
 * 
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MessageDigestFactory
 * @Description TODO
 * @author yinshijie
 * @date 2016年10月14日 下午2:29:12
 *
 */
public class Digestor {

	public static final String ALGORITHM_NAME_MD5 = "MD5";
	public static final String ALGORITHM_NAME_SHA1 = "SHA-1";

	/**
	 * 消息加密算法名称
	 */
	private String algorithmName;
	private MessageDigest messageDigest;

	/**
	 * 初始化大小
	 */
	private int initialSize = 2;
	/**
	 * 最小空闲数
	 */
	private int minIdle = 2;
	/**
	 * 最大空闲数
	 */
	private int maxIdle = 5;
	/**
	 * 最大大小
	 */
	private int maxActive = 10;
	/**
	 * 最长等待时间
	 */
	private long maxWait = 5 * 1000;

	/**
	 * 检查资源池
	 */
	private boolean cheakPool = true;

	/**
	 * 检查延迟时长
	 */
	private long checkDelayTime = 30 * 1000;

	/**
	 * 检查周期时长
	 */
	private long checkPeriodTime = 5 * 1000;

	/**
	 * 创建一个实例 MessageDigestor.
	 *
	 * @Description: TODO
	 * @throws NoSuchAlgorithmException
	 */
	public Digestor() {
		this.algorithmName = ALGORITHM_NAME_MD5;
	}

	/**
	 * 创建一个实例 MessageDigestor.
	 *
	 * @Description: TODO
	 * @param algorithmName
	 *            消息加密算法名称
	 *
	 * @throws NoSuchAlgorithmException
	 */
	public Digestor(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	/**
	 * 创建一个实例 MessageDigestor.
	 *
	 * @Description: TODO
	 * @param algorithmName
	 *            消息加密算法名称
	 * @param initialSize
	 *            初始化大小
	 * @param minIdle
	 *            最小空闲数
	 * @param maxIdle
	 *            最大空闲数
	 * @param maxActive
	 *            最大大小
	 * @throws NoSuchAlgorithmException
	 */
	public Digestor(String algorithmName, int initialSize, int minIdle, int maxIdle, int maxActive)
			throws NoSuchAlgorithmException {
		this.algorithmName = algorithmName;
		this.initialSize = initialSize;
		this.minIdle = minIdle;
		this.maxIdle = maxIdle;
		this.maxActive = maxActive;
	}

	/**
	 * @return the algorithmName
	 * @since JDK1.5
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * @param algorithmName
	 *            the algorithmName to set
	 * @since JDK1.5
	 */
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	/**
	 * @return the messageDigest
	 * @since JDK1.5
	 */
	public MessageDigest getMessageDigest() {
		return messageDigest;
	}

	/**
	 * @param messageDigest
	 *            the messageDigest to set
	 * @since JDK1.5
	 */
	public void setMessageDigest(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}

	/**
	 * @return the initialSize
	 * @since JDK1.5
	 */
	public int getInitialSize() {
		return initialSize;
	}

	/**
	 * @param initialSize
	 *            the initialSize to set
	 * @since JDK1.5
	 */
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * @return the minIdle
	 * @since JDK1.5
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * @param minIdle
	 *            the minIdle to set
	 * @since JDK1.5
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * @return the maxIdle
	 * @since JDK1.5
	 */
	public int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @param maxIdle
	 *            the maxIdle to set
	 * @since JDK1.5
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the maxActive
	 * @since JDK1.5
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive
	 *            the maxActive to set
	 * @since JDK1.5
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the maxWait
	 * @since JDK1.5
	 */
	public long getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait
	 *            the maxWait to set
	 * @since JDK1.5
	 */
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the cheakPool
	 * @since JDK1.5
	 */
	public boolean isCheakPool() {
		return cheakPool;
	}

	/**
	 * @param cheakPool
	 *            the cheakPool to set
	 * @since JDK1.5
	 */
	public void setCheakPool(boolean cheakPool) {
		this.cheakPool = cheakPool;
	}

	/**
	 * @return the checkDelayTime
	 * @since JDK1.5
	 */
	public long getCheckDelayTime() {
		return checkDelayTime;
	}

	/**
	 * @param checkDelayTime
	 *            the checkDelayTime to set
	 * @since JDK1.5
	 */
	public void setCheckDelayTime(long checkDelayTime) {
		this.checkDelayTime = checkDelayTime;
	}

	/**
	 * @return the checkPeriodTime
	 * @since JDK1.5
	 */
	public long getCheckPeriodTime() {
		return checkPeriodTime;
	}

	/**
	 * @param checkPeriodTime
	 *            the checkPeriodTime to set
	 * @since JDK1.5
	 */
	public void setCheckPeriodTime(long checkPeriodTime) {
		this.checkPeriodTime = checkPeriodTime;
	}
}
