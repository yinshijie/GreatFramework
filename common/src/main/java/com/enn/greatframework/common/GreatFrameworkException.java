/*
 * @title com.enn.greatframework.common.GreatFrameworkException.java
 * @Copy.Right (c)2017.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年1月24日 下午2:48:40
 * @author Enn.HowMuch.yinshijie
 * @version V0.1.0
 */
package com.enn.greatframework.common;

/**
 * 平台公用异常类
 * @ClassName GreatFrameworkException
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年1月24日 下午2:48:40
 *
 */
public class GreatFrameworkException extends Exception {

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = -5188055335623971772L;

	/**
	 * 创建一个实例 GreatFrameworkException.
	 *
	 * @Description: TODO
	 */
	public GreatFrameworkException() {
		super();
	}

	/**
	 * 创建一个实例 GreatFrameworkException.
	 *
	 * @Description: TODO
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public GreatFrameworkException(String message, Throwable cause, boolean enableSuppression,
	        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 创建一个实例 GreatFrameworkException.
	 *
	 * @Description: TODO
	 * @param message
	 * @param cause
	 */
	public GreatFrameworkException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 创建一个实例 GreatFrameworkException.
	 *
	 * @Description: TODO
	 * @param message
	 */
	public GreatFrameworkException(String message) {
		super(message);
	}

	/**
	 * 创建一个实例 GreatFrameworkException.
	 *
	 * @Description: TODO
	 * @param cause
	 */
	public GreatFrameworkException(Throwable cause) {
		super(cause);
	}

}
