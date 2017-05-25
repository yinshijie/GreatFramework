/*
 * @title com.enn.greatframework.authorize.dao.CCustomerDAO.java
 *
 * @Copy.Right (c)2015.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年02月14日 下午4:07:02
 *
 * @author Enn.HowMuch.MybatisGenerator
 *
 * @version V0.1.0
 *
 */
package com.enn.greatframework.authorize.dao;

import java.util.List;

import com.enn.greatframework.authorize.bean.CCustomer;

/**
 * @InterfaceName org.mybatis.generator.api.dom.java.Interface
 * @TableName c_customer
 * @Department c_customer
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public interface CCustomerDAO {
	/**
	 * @MethodName deleteByPrimaryKey
	 * @Description TODO
	 * @param java.lang.String
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	int deleteByPrimaryKey(String customerId);

	/**
	 * @MethodName insert
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.CCustomer
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	int insert(CCustomer record);

	/**
	 * @MethodName insertSelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.CCustomer
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	int insertSelective(CCustomer record);

	/**
	 * @MethodName selectByPrimaryKey
	 * @Description TODO
	 * @param java.lang.String
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	CCustomer selectByPrimaryKey(String customerId);

	/**
	 * @MethodName updateByPrimaryKeySelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.CCustomer
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	int updateByPrimaryKeySelective(CCustomer record);

	/**
	 * @MethodName updateByPrimaryKey
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.CCustomer
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for c_customer
	 * @Enn.Howmuch.MybatisGenerator
	 */
	int updateByPrimaryKey(CCustomer record);

	/**
	 * 根据用户手机号、邮箱、登录名查询用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.selectForLogin(...)
	 *
	 * @param loginName
	 * @return
	 */
	CCustomer selectForLogin(String loginName);

	/**
	 * 根据主账号ID获取所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.getChildCustomers(...)
	 *
	 * @param customerId
	 * @return
	 */
	List<CCustomer> getChildCustomers(String customerId, int pageNo, int pageSize);

	/**
	 * 根据主账号ID获取所有子帐号总数
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.getChildCustomersCount(...)
	 *
	 * @param customerId
	 * @return
	 */
	int getChildCustomersCount(String customerId);

	/**
	 * 根据登录帐号、手机号、昵称、邮件进行模糊查询
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.searchCustomer(...)
	 *
	 * @param searchWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<CCustomer> searchCustomer(String searchWord, int pageNo, int pageSize);

	/**
	 * 根据登录帐号、手机号、昵称、邮件进行模糊查询
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.searchCustomer(...)
	 *
	 * @param searchWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	int searchCustomerCount(String searchWord);

	/**
	 * 查询所有主账号
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.searchCustomer(...)
	 *
	 * @param searchWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<CCustomer> getAllCustomer(int pageNo, int pageSize);

	/**
	 * 查询所有主账号总数
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.CCustomerDAO.searchCustomer(...)
	 *
	 * @param searchWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	int getAllCustomerCount();

}