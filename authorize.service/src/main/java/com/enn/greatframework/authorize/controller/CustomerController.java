/*
 * @title com.enn.greatframework.authorize.controller.CustomerController.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年2月15日 上午9:30:47
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enn.greatframework.authorize.bean.CCustomer;
import com.enn.greatframework.authorize.bean.vo.CustomerSession;
import com.enn.greatframework.authorize.service.CustomerService;
import com.enn.greatframework.common.GreatFrameworkConst;
import com.enn.greatframework.common.http.context.RequestContent;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.controller.AbstractController;
import com.enn.greatframework.common.lang.StringUtil;

/**
 * @ClassName CustomerController
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年2月15日 上午9:30:47
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * 根据用户ID查询用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.getCustomerInfo(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCustomerInfo", method = RequestMethod.POST)
	public void getCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			String customerId = requestBody.getParam("customerId");
			if (!StringUtil.checkParams(customerId)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.getCustomerById(customerId);

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 创建用户
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public void createCustomer(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			CCustomer customerInfo = requestBody.getParam("customer", CCustomer.class);
			if (!StringUtil.checkObjectParams(customerInfo)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				customerInfo = customerService.createCustomer(customerInfo);
				if (customerInfo != null) {
					responseBody = ResponseContent.SUCCESS();
					responseBody.addResultObject("customer", customerInfo);
				} else {
					responseBody = ResponseContent.DATABASE_ERROR();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 根据父帐号ID查询所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getChildCustomers", method = RequestMethod.POST)
	public void getChildCustomers(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			String customerId = requestBody.getParam("customerId");
			if (!StringUtil.checkParams(customerId)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				List<CCustomer> customers = customerService.getChildCustomers(customerId);

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("customerList", customers);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 修改用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			CCustomer customerInfo = requestBody.getParam("customer", CCustomer.class);
			if (!StringUtil.checkObjectParams(customerInfo)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				customerInfo = customerService.updateCustomer(customerInfo);

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 根据用户登录token获取用户消息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCustomerBySessionId", method = RequestMethod.POST)
	public void getCustomerBySessionId(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			String sessionId = requestBody.getParam("sessionId");
			if (!StringUtil.checkParams(sessionId)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.getCustomerBySessionId(sessionId);

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			String appToken = request.getParameter(GreatFrameworkConst.REQUEST_PARAM_APPTOKEN);
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			String sessionId = requestBody.getParam("sessionId");
			if (!StringUtil.checkParams(appToken, loginName, loginPwd)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				CustomerSession customerSession = null;
				if (StringUtil.isNotBlank(sessionId)) {
					customerSession = customerService.customerLogin(appToken, loginName, loginPwd, sessionId);
				} else {
					customerSession = customerService.customerLogin(appToken, loginName, loginPwd);
				}

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("sessionId", customerSession.getSessionId());
				responseBody.addResultObject("customer", customerSession.getCustomer());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/loginNoEncode", method = RequestMethod.POST)
	public void loginNoEncode(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			String appToken = request.getParameter(GreatFrameworkConst.REQUEST_PARAM_APPTOKEN);
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			String sessionId = requestBody.getParam("sessionId");
			if (!StringUtil.checkParams(appToken, loginName, loginPwd)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				CustomerSession customerSession = null;
				if (StringUtil.isNotBlank(sessionId)) {
					customerSession = customerService.customerLogin_NoEncode(appToken, loginName, loginPwd, sessionId);
				} else {
					customerSession = customerService.customerLogin_NoEncode(appToken, loginName, loginPwd);
				}

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("sessionId", customerSession.getSessionId());
				responseBody.addResultObject("customer", customerSession.getCustomer());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 用户校验，仅做密码验证，不做登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public void validate(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			String appToken = request.getParameter(GreatFrameworkConst.REQUEST_PARAM_APPTOKEN);
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			if (!StringUtil.checkParams(appToken, loginName, loginPwd)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.customerValidate(appToken, loginName, loginPwd);

				responseBody = ResponseContent.SUCCESS();
				responseBody.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}

	/**
	 * 用户登出
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		ResponseContent responseBody = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			String sessionId = requestBody.getParam("sessionId");
			if (!StringUtil.checkParams(sessionId)) {
				responseBody = ResponseContent.PARAMS_ERROR();
			} else {
				customerService.customerLogout(sessionId);

				responseBody = ResponseContent.SUCCESS();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseBody = ResponseContent.INNER_ERROR();
			responseBody.setDescribe(e.getMessage());
		}

		responseWriter(request, response, responseBody);
	}
}
