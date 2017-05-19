/*
 * @title com.enn.greatframework.authorize.bean.vo.CustomerSession.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月18日 下午4:56:24
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.bean.vo;

import java.io.Serializable;

import com.enn.greatframework.authorize.bean.CCustomer;

/**
 * @ClassName CustomerSession
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月18日 下午4:56:24
 *
 */
public class CustomerSession implements Serializable {

	/**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = -6395887862703629552L;

	private String sessionId;
	private CCustomer customer;

	/**
	 * @return the cCustomer
	 * @since JDK1.5
	 */
	public CCustomer getCustomer() {
		return customer;
	}

	/**
	 * @param cCustomer the cCustomer to set
	 * @since JDK1.5
	 */
	public void setCustomer(CCustomer cCustomer) {
		this.customer = cCustomer;
	}

	/**
	 * @return the sessionId
	 * @since JDK1.5
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 * @since JDK1.5
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
