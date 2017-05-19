/*
 * @title com.enn.greatframework.authorize.service.CustomerService.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年2月14日 下午4:13:32
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.service;

import java.util.List;

import com.enn.greatframework.authorize.bean.CCustomer;
import com.enn.greatframework.authorize.bean.vo.CustomerSession;
import com.enn.greatframework.common.GreatFrameworkException;

/**
 * @ClassName CustomerService
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年2月14日 下午4:13:32
 *
 */
public interface CustomerService {

	static final String DEFAULT_CUSTOMER_ICON_PATH = "https://www.greatgas.cn/web/resource/customer/img/license/addCustomer.png";

	/**
	 * 创建用户，子帐号创建时需要填写customerParentId
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.createCustomer(...)
	 *
	 * @param customer
	 * @return
	 */
	CCustomer createCustomer(CCustomer customer);

	/**
	 * 根据用户ID获取用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.getCustomerInfoById(...)
	 *
	 * @param customerId
	 * @return
	 */
	CCustomer getCustomerById(String customerId);

	/**
	 * 根据父帐号ID查询所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.getChildCustomers(...)
	 *
	 * @param customerId
	 * @return
	 */
	List<CCustomer> getChildCustomers(String customerId);

	/**
	 * 更新用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.updateCustomerInfo(...)
	 *
	 * @param customer
	 * @return
	 */
	CCustomer updateCustomer(CCustomer customer);

	/**
	 * 根据用户登录token获取用户消息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.getCustomerInfoByToken(...)
	 *
	 * @param token
	 * @return
	 * @throws GreatFrameworkException
	 */
	CCustomer getCustomerBySessionId(String sessionId) throws GreatFrameworkException;

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerLogin(...)
	 *
	 * @param appToken
	 * @param loginName 邮箱、手机号、登录用户名
	 * @param loginPassword 加密后密码
	 * @param sessionId
	 * @return
	 */
	CustomerSession customerLogin(String appToken, String loginName, String loginPassword, String sessionId)
	        throws GreatFrameworkException;

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerLogin(...)
	 *
	 * @param appToken
	 * @param loginName 邮箱、手机号、登录用户名
	 * @param loginPassword 加密后密码
	 * @return
	 */
	CustomerSession customerLogin(String appToken, String loginName, String loginPassword)
	        throws GreatFrameworkException;

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerLogin_NoEncode(...)
	 *
	 * @param appToken
	 * @param loginName 邮箱、手机号、登录用户名
	 * @param loginPassword 密码明文
	 * @return
	 * @throws GreatFrameworkException
	 */
	CustomerSession customerLogin_NoEncode(String appToken, String loginName, String loginPassword)
	        throws GreatFrameworkException;

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerLogin_NoEncode(...)
	 *
	 * @param appToken
	 * @param loginName 邮箱、手机号、登录用户名
	 * @param loginPassword 密码明文
	 * @return
	 * @throws GreatFrameworkException
	 */
	CustomerSession customerLogin_NoEncode(String appToken, String loginName, String loginPassword, String sessionId)
	        throws GreatFrameworkException;

	/**
	 * 用户校验，仅做密码验证，不做登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerValidate(...)
	 *
	 * @param appToken
	 * @param loginName
	 * @param loginPassword
	 * @return
	 * @throws GreatFrameworkException
	 */
	CCustomer customerValidate(String appToken, String loginName, String loginPassword) throws GreatFrameworkException;

	/**
	 * 用户登出
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.CustomerService.customerLogout(...)
	 *
	 * @param sessionId
	 */
	void customerLogout(String sessionId);
}
