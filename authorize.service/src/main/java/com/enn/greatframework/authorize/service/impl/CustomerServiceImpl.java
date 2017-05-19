/*
 * @title com.enn.greatframework.authorize.service.impl.CustomerServiceImpl.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年2月14日 下午4:13:47
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.enn.greatframework.authorize.bean.CCustomer;
import com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo;
import com.enn.greatframework.authorize.bean.vo.CustomerSession;
import com.enn.greatframework.authorize.dao.CCustomerDAO;
import com.enn.greatframework.authorize.service.CustomerService;
import com.enn.greatframework.authorize.service.OpenApplicationService;
import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.GreatFrameworkException;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * @ClassName CustomerServiceImpl
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年2月14日 下午4:13:47
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CCustomerDAO cCustomerDAO;
	@Autowired
	private OpenApplicationService openApplicationService;
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	/** 默认session有效时间(秒)--30分钟 */
	private static final long _sessionExpireSecond = 60 * 30;

	/*
	 * @Title: createCustomer
	 *
	 * @Description TODO
	 *
	 * @param customer
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.CustomerService#createCustomer(com.enn.greatframework.authorize.bean.
	 * CCustomer)
	 */
	@Override
	@CachePut(value = "user.info.customerId", key = "#result.getCustomerId()", condition = "#result != null")
	public CCustomer createCustomer(CCustomer customer) {
		Date now = new Date();
		if (StringUtil.isBlank(customer.getCustomerId())) {
			customer.setCustomerId(DigestorManager.GUID());
		}
		// 用户密码加密
		try {
			String pwdResource = new String(Base64Utils.decodeFromString(customer.getCustomerPassword()),
			        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
			String pwdEncode = DigestorManager.greatPasswordCreate(pwdResource);
			customer.setCustomerPassword(pwdEncode);
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn(e.getMessage(), e);
		}

		// 设置用户常量数据
		customer.setCustomerState(Integer.parseInt(GreatFrameworkConst.STATE_ENABLED));
		customer.setCustomerSecQaEnable(Boolean.FALSE);
		customer.setCustomerSecControlEnable(Boolean.FALSE);
		customer.setCustomerSecCaEnable(Boolean.FALSE);
		customer.setCustomerEnable(Integer.parseInt(GreatFrameworkConst.STATE_ENABLED));
		customer.setCustomerIconPath(DEFAULT_CUSTOMER_ICON_PATH);

		customer.setCustomerSecEmail(customer.getCustomerEmail());
		customer.setCustomerSecMobile(customer.getCustomerMobile());
		customer.setCustomerCreatetime(now);
		customer.setUpdateTime(now);
		int updateCnt = cCustomerDAO.insertSelective(customer);
		if (updateCnt > 0) {
			return customer;
		} else {
			return null;
		}
	}

	/*
	 * @Title: getChildCustomers
	 *
	 * @Description TODO
	 *
	 * @param customerId
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#getChildCustomers(java.lang.String)
	 */
	@Override
	public List<CCustomer> getChildCustomers(String customerId) {
		List<CCustomer> childCustomers = cCustomerDAO.getChildCustomers(customerId);
		return childCustomers;
	}

	/*
	 * @Title: getCustomerInfo
	 *
	 * @Description TODO
	 *
	 * @param customerId
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#getCustomerInfo(java.lang.String)
	 */
	@Override
	@Cacheable(value = "user.info.customerId", key = "#customerId")
	public CCustomer getCustomerById(String customerId) {
		return cCustomerDAO.selectByPrimaryKey(customerId);
	}

	/*
	 * @Title: updateCustomerInfo
	 *
	 * @Description TODO
	 *
	 * @param customer
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.CustomerService#updateCustomerInfo(com.enn.greatframework.authorize.bean
	 * .CCustomer)
	 */
	@Override
	@CachePut(value = "user.info.customerId", key = "#customer.getCustomerId()", condition = "#result != null")
	public CCustomer updateCustomer(CCustomer customer) {
		Date now = new Date();
		customer.setUpdateTime(now);
		int updateCnt = cCustomerDAO.updateByPrimaryKeySelective(customer);
		if (updateCnt > 0) {
			customer = cCustomerDAO.selectByPrimaryKey(customer.getCustomerId());
			return customer;
		} else {
			return null;
		}
	}

	/*
	 * @Title: getCustomerInfoByToken
	 *
	 * @Description TODO
	 *
	 * @param token
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#getCustomerInfoByToken(java.lang.String)
	 */
	@Override
	public CCustomer getCustomerBySessionId(String sessionId) throws GreatFrameworkException {
		String customerId = getCustomerIdBySessionId(sessionId);
		if (StringUtil.isBlank(customerId)) {
			throw new GreatFrameworkException("用户登录状态已失效，请重新登录!");
		}
		return getCustomerById(customerId);
	}

	/*
	 * @Title: customerLogin
	 *
	 * @Description TODO
	 *
	 * @param loginName
	 *
	 * @param loginPassword
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public CustomerSession customerLogin(String appToken, String loginName, String loginPassword, String sessionId)
	        throws GreatFrameworkException {
		CCustomer customer = cCustomerDAO.selectForLogin(loginName);
		if (customer == null) {
			throw new GreatFrameworkException("根据手机号、邮箱、用户名未找到用户信息");
		}
		String realPwd = customer.getCustomerPassword();
		try {
			loginPassword = new String(
			        Base64Utils.decode(loginPassword.getBytes(GreatFrameworkConst.DEFAULT_CHARSET_NAME)),
			        GreatFrameworkConst.DEFAULT_CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			throw new GreatFrameworkException("用户登录密码Base64解密失败", e);
		}
		boolean validateResult = DigestorManager.greatPasswordValidate(loginPassword, realPwd);
		if (!validateResult) {
			throw new GreatFrameworkException("密码错误");
		}

		/** 获取应用信息 */
		OpenDeveloperApplicationInfo appInfo = openApplicationService.getApplicationInfoByToken(appToken);
		String appId = appInfo.getApplicationId();
		String customerId = customer.getCustomerId();
		/** 缓存session到redis */
		setSessionCache(appId, customerId, sessionId);

		/** 组装返回的用户session信息 */
		CustomerSession customerSession = new CustomerSession();
		customerSession.setSessionId(sessionId);
		customerSession.setCustomer(customer);

		return customerSession;
	}

	/*
	 * @Title: customerLogin
	 *
	 * @Description TODO
	 *
	 * @param appToken
	 *
	 * @param loginName
	 *
	 * @param loginPassword
	 *
	 * @return
	 *
	 * @throws GreatFrameworkException
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerLogin(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CustomerSession customerLogin(String appToken, String loginName, String loginPassword)
	        throws GreatFrameworkException {
		String sessionId = UUID.randomUUID().toString();
		return customerLogin(appToken, loginName, loginPassword, sessionId);
	}

	/*
	 * @Title: customerLogin_NoEncode
	 *
	 * @Description TODO
	 *
	 * @param loginName
	 *
	 * @param loginPassword
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerLogin_NoEncode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CustomerSession customerLogin_NoEncode(String appToken, String loginName, String loginPassword,
	        String sessionId) throws GreatFrameworkException {
		CCustomer customer = cCustomerDAO.selectForLogin(loginName);
		if (customer == null) {
			throw new GreatFrameworkException("根据手机号、邮箱、用户名未找到用户信息");
		}
		String realPwd = customer.getCustomerPassword();
		boolean validateResult = DigestorManager.greatPasswordValidate(loginPassword, realPwd);
		if (!validateResult) {
			throw new GreatFrameworkException("密码错误");
		}

		/** 获取应用信息 */
		OpenDeveloperApplicationInfo appInfo = openApplicationService.getApplicationInfoByToken(appToken);
		String appId = appInfo.getApplicationId();
		String customerId = customer.getCustomerId();
		/** 缓存session到redis */
		setSessionCache(appId, customerId, sessionId);

		/** 组装返回的用户session信息 */
		CustomerSession customerSession = new CustomerSession();
		customerSession.setSessionId(sessionId);
		customerSession.setCustomer(customer);

		return customerSession;
	}

	/*
	 * @Title: customerLogin_NoEncode
	 *
	 * @Description TODO
	 *
	 * @param appToken
	 *
	 * @param loginName
	 *
	 * @param loginPassword
	 *
	 * @param sessionId
	 *
	 * @return
	 *
	 * @throws GreatFrameworkException
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerLogin_NoEncode(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CustomerSession customerLogin_NoEncode(String appToken, String loginName, String loginPassword)
	        throws GreatFrameworkException {
		String sessionId = UUID.randomUUID().toString();
		return customerLogin_NoEncode(appToken, loginName, loginPassword, sessionId);
	}

	/*
	 * @Title: customerValidate
	 *
	 * @Description TODO
	 *
	 * @param loginName
	 *
	 * @param loginPassword
	 *
	 * @return
	 *
	 * @throws GreatFrameworkException
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerValidate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CCustomer customerValidate(String appToken, String loginName, String loginPassword)
	        throws GreatFrameworkException {
		/** 登录验证处理 */
		CCustomer customer = cCustomerDAO.selectForLogin(loginName);
		if (customer == null) {
			throw new GreatFrameworkException("根据手机号、邮箱、用户名未找到用户信息");
		}
		String realPwd = customer.getCustomerPassword();
		boolean validateResult = DigestorManager.greatPasswordValidate(loginPassword, realPwd);
		if (!validateResult) {
			throw new GreatFrameworkException("密码错误");
		}

		return customer;
	}

	/*
	 * @Title: customerLogout
	 *
	 * @Description TODO
	 *
	 * @param sessionId
	 *
	 * @see com.enn.greatframework.authorize.service.CustomerService#customerLogout(java.lang.String)
	 */
	@Override
	public void customerLogout(String sessionId) {
		delSessionCache(sessionId);
	}

	/**
	 * 根据传入的sessionId获取customerId，并重置缓存有效时间
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.impl.CustomerServiceImpl.expireSessionCache(...)
	 *
	 * @param sessionId
	 */
	private String getCustomerIdBySessionId(String sessionId) {
		RedisConnection jedisConn = null;
		try {
			jedisConn = jedisConnectionFactory.getConnection();

			byte[] sessionIdBytes = redisTemplate.getStringSerializer().serialize(sessionId);
			if (jedisConn.exists(sessionIdBytes)) {
				String uniquenessId = redisTemplate.getStringSerializer().deserialize(jedisConn.get(sessionIdBytes));
				String[] uniqIdSplit = uniquenessId.split("\\|");
				// String appId = uniqIdSplit[0];
				String customerId = uniqIdSplit[1];
				byte[] uniquenessIdBytes = redisTemplate.getStringSerializer().serialize(uniquenessId);

				jedisConn.expire(uniquenessIdBytes, _sessionExpireSecond);
				jedisConn.expire(sessionIdBytes, _sessionExpireSecond);
				return customerId;
			} else {
				return null;
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
			return null;
		} finally {
			jedisConn.close();
		}
	}

	/**
	 * 设置session信息缓存
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.impl.CustomerServiceImpl.setSessionCache(...)
	 *
	 * @param appId
	 * @param customerId
	 * @param sessionId
	 */
	private void setSessionCache(String appId, String customerId, String sessionId) {
		RedisConnection jedisConn = null;
		try {
			jedisConn = jedisConnectionFactory.getConnection();

			String uniquenessId = appId.concat("|").concat(customerId);
			byte[] uniquenessIdBytes = redisTemplate.getStringSerializer().serialize(uniquenessId);
			byte[] sessionIdBytes = redisTemplate.getStringSerializer().serialize(sessionId);
			// 如果用户已存在登录记录，删除原登录信息缓存
			if (jedisConn.exists(uniquenessIdBytes)) {
				String oldSessionId = redisTemplate.getStringSerializer().deserialize(jedisConn.get(uniquenessIdBytes));
				byte[] oldSessionIdBytes = redisTemplate.getStringSerializer().serialize(oldSessionId);
				jedisConn.del(uniquenessIdBytes, oldSessionIdBytes);
			}
			// 将seesionId缓存到redis
			jedisConn.set(uniquenessIdBytes, sessionIdBytes);
			jedisConn.set(sessionIdBytes, uniquenessIdBytes);
			jedisConn.expire(uniquenessIdBytes, _sessionExpireSecond);
			jedisConn.expire(sessionIdBytes, _sessionExpireSecond);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
		} finally {
			jedisConn.close();
		}
	}

	/**
	 * 根据传入sessionId删除缓存信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.impl.CustomerServiceImpl.delSessionCache(...)
	 *
	 * @param sessionId
	 */
	private void delSessionCache(String sessionId) {
		RedisConnection jedisConn = null;
		try {
			jedisConn = jedisConnectionFactory.getConnection();

			byte[] sessionIdBytes = redisTemplate.getStringSerializer().serialize(sessionId);
			if (jedisConn.exists(sessionIdBytes)) {
				String uniquenessId = redisTemplate.getStringSerializer().deserialize(jedisConn.get(sessionIdBytes));
				byte[] uniquenessIdBytes = redisTemplate.getStringSerializer().serialize(uniquenessId);

				jedisConn.del(uniquenessIdBytes, sessionIdBytes);
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
		} finally {
			jedisConn.close();
		}
	}
}
