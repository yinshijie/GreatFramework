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

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enn.greatframework.authorize.bean.CCustomer;
import com.enn.greatframework.authorize.bean.vo.CustomerSession;
import com.enn.greatframework.authorize.service.CustomerService;
import com.enn.greatframework.common.data.GreatCurrentPage;
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

	// /**
	// * 根据用户ID查询用户信息
	// * @Description TODO
	// * @Call com.enn.greatframework.authorize.controller.CustomerController.getCustomerInfo(...)
	// *
	// * @param requestContent
	// * @return
	// */
	// @ResponseBody
	// @RequestMapping(value = "/getCustomerInfo", method = RequestMethod.POST, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public String getCustomerInfo(HttpServletRequest request) {
	// ResponseContent responseContent = null;
	// try {
	// // 检查传入参数
	// boolean checkResult = Boolean.TRUE;
	// RequestContent requestBody = getRequestBody(request);
	// String customerId = requestBody.getParam("customerId");
	// checkResult = StringUtil.checkParams(customerId);
	//
	// if (!checkResult) {
	// responseContent = ResponseContent.PARAMS_ERROR();
	// } else {
	// CCustomer customerInfo = customerService.getCustomerById(customerId);
	//
	// responseContent = ResponseContent.SUCCESS();
	// responseContent.addResultObject("customer", customerInfo);
	// }
	// } catch (Exception e) {
	// LOGGER.error(e.getMessage(), e);
	// responseContent = ResponseContent.INNER_ERROR();
	// responseContent.setDescribe(e.getMessage());
	// }
	//
	// return responseContent.toString();
	// }

	/**
	 * 根据用户ID查询用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.getCustomerInfo(...)
	 *
	 * @param requestContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCustomerInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseContent getCustomerInfo(@RequestBody RequestContent requestContent) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			String customerId = requestContent.getParam("customerId");
			checkResult = StringUtil.checkParams(customerId);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.getCustomerById(customerId);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent;
	}

	/**
	 * 创建用户
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String createCustomer(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			CCustomer customerInfo = requestBody.getParam("customer", CCustomer.class);
			checkResult = StringUtil.checkObjectParams(customerInfo);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				customerInfo = customerService.createCustomer(customerInfo);
				if (customerInfo != null) {
					responseContent = ResponseContent.SUCCESS();
					responseContent.addResultObject("customer", customerInfo);
				} else {
					responseContent = ResponseContent.DATABASE_ERROR();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 根据父帐号ID查询所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildCustomers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getChildCustomers(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String customerId = requestBody.getParam("customerId");
			String pageNoStr = requestBody.getParam("pageNo");
			String pageSizeStr = requestBody.getParam("pageSize");
			checkResult = StringUtil.checkParams(customerId);
			int pageNo = 1;
			int pageSize = 20;
			if (StringUtil.checkParams(pageNoStr, pageSizeStr)) {
				pageNo = Integer.parseInt(pageNoStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				GreatCurrentPage<CCustomer> page = customerService.getChildCustomers(customerId, pageNo, pageSize);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("page", page);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 根据父帐号ID查询所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String searchCustomer(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String searchWord = requestBody.getParam("searchWord");
			String pageNoStr = requestBody.getParam("pageNo");
			String pageSizeStr = requestBody.getParam("pageSize");
			int pageNo = 1;
			int pageSize = 20;
			if (StringUtil.isBlank(searchWord)) {
				searchWord = StringUtil.EMPTY;
			}
			if (StringUtil.checkParams(pageNoStr, pageSizeStr)) {
				pageNo = Integer.parseInt(pageNoStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				GreatCurrentPage<CCustomer> page = customerService.searchCustomer(searchWord, pageNo, pageSize);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("page", page);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 根据父帐号ID查询所有子帐号信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAllCustomers(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String pageNoStr = requestBody.getParam("pageNo");
			String pageSizeStr = requestBody.getParam("pageSize");
			int pageNo = 1;
			int pageSize = 20;
			if (StringUtil.checkParams(pageNoStr, pageSizeStr)) {
				pageNo = Integer.parseInt(pageNoStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				GreatCurrentPage<CCustomer> page = customerService.getAllCustomers(pageNo, pageSize);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("page", page);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 修改用户信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateCustomer(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			CCustomer customerInfo = requestBody.getParam("customer", CCustomer.class);
			checkResult = StringUtil.checkObjectParams(customerInfo);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				customerInfo = customerService.updateCustomer(customerInfo);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 根据用户登录token获取用户消息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/getCustomerBySessionId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getCustomerBySessionId(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String sessionId = requestBody.getParam("sessionId");
			checkResult = StringUtil.checkParams(sessionId);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.getCustomerBySessionId(sessionId);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @PathParam("appToken") String appToken) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			String sessionId = requestBody.getParam("sessionId");
			checkResult = StringUtil.checkParams(appToken, loginName, loginPwd);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				CustomerSession customerSession = null;
				if (StringUtil.isNotBlank(sessionId)) {
					customerSession = customerService.customerLogin(appToken, loginName, loginPwd, sessionId);
				} else {
					customerSession = customerService.customerLogin(appToken, loginName, loginPwd);
				}

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("sessionId", customerSession.getSessionId());
				responseContent.addResultObject("customer", customerSession.getCustomer());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 用户登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/loginNoEncode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String loginNoEncode(HttpServletRequest request, @PathParam("appToken") String appToken) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			String sessionId = requestBody.getParam("sessionId");
			checkResult = StringUtil.checkParams(appToken, loginName, loginPwd);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				CustomerSession customerSession = null;
				if (StringUtil.isNotBlank(sessionId)) {
					customerSession = customerService.customerLogin_NoEncode(appToken, loginName, loginPwd, sessionId);
				} else {
					customerSession = customerService.customerLogin_NoEncode(appToken, loginName, loginPwd);
				}

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("sessionId", customerSession.getSessionId());
				responseContent.addResultObject("customer", customerSession.getCustomer());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 用户校验，仅做密码验证，不做登录
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String validate(HttpServletRequest request, @PathParam("appToken") String appToken) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			RequestContent requestBody = getRequestBody(request);
			String loginName = requestBody.getParam("loginName");
			String loginPwd = requestBody.getParam("loginPwd");
			checkResult = StringUtil.checkParams(appToken, loginName, loginPwd);

			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				CCustomer customerInfo = customerService.customerValidate(appToken, loginName, loginPwd);

				responseContent = ResponseContent.SUCCESS();
				responseContent.addResultObject("customer", customerInfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}

	/**
	 * 用户登出
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.CustomerController.createCustomer(...)
	 *
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String logout(HttpServletRequest request) {
		ResponseContent responseContent = null;
		try {
			RequestContent requestBody = getRequestBody(request);
			String sessionId = requestBody.getParam("sessionId");
			if (!StringUtil.checkParams(sessionId)) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				customerService.customerLogout(sessionId);

				responseContent = ResponseContent.SUCCESS();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
			responseContent.setDescribe(e.getMessage());
		}

		return responseContent.toString();
	}
}
