/*
 * @title com.enn.greatframework.authorize.controller.OpenDeveloperController.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 下午2:51:08
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo;
import com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo;
import com.enn.greatframework.authorize.service.OpenApplicationService;
import com.enn.greatframework.authorize.service.OpenDeveloperService;
import com.enn.greatframework.common.http.context.RequestContent;
import com.enn.greatframework.common.http.context.ResponseContent;
import com.enn.greatframework.common.http.controller.AbstractController;
import com.enn.greatframework.common.lang.StringUtil;

/**
 * 开放平台
 * @ClassName OpenDeveloperController
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 下午2:51:08
 *
 */
@Controller
@RequestMapping("/open")
public class OpenDeveloperController extends AbstractController {

	@Autowired
	private OpenDeveloperService openDeveloperService;
	@Autowired
	private OpenApplicationService openApplicationService;

	/**
	 * 获取开发者信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.OpenDeveloperController.getDeveloperInfo(...)
	 *
	 * @param request
	 * @param response
	 * @param developerId
	 */
	@ResponseBody
	@RequestMapping(value = "/getDeveloperInfo/{developerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseContent getDeveloperInfo(@PathVariable("developerId") String developerId) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			checkResult = StringUtil.checkParams(developerId);
			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				OpenDeveloperUserInfo developerInfo = openDeveloperService.getDeveloperUserInfo(developerId);
				if (developerInfo == null) {
					responseContent = ResponseContent.INNER_ERROR();
				} else {
					// 装配结果集
					responseContent = ResponseContent.SUCCESS();
					responseContent.addResultObject("developerInfo", developerInfo);
				}
			}
		} catch (Exception e) {
			logError(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
		}

		return responseContent;
	}

	/**
	 * 根据tokenId获取应用信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.OpenApplicationController.getApplicationInfoByToken(...)
	 *
	 * @param request
	 * @param response
	 * @param appToken
	 */
	@ResponseBody
	@RequestMapping(value = "/getApplicationInfoByToken/{appToken}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseContent getApplicationInfoByToken(@PathVariable("appToken") String appToken) {
		ResponseContent responseContent = null;
		try {
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			checkResult = StringUtil.checkParams(appToken);
			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				OpenDeveloperApplicationInfo applicationInfo = openApplicationService
				        .getApplicationInfoByToken(appToken);
				if (applicationInfo == null) {
					responseContent = ResponseContent.INNER_ERROR();
				} else {
					// 装配结果集
					responseContent = ResponseContent.SUCCESS();
					responseContent.addResultObject("applicationInfo", applicationInfo);
				}
			}
		} catch (Exception e) {
			logError(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
		}

		return responseContent;
	}

	/**
	 * 创建开放平台开发者用户
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.controller.OpenDeveloperController.createDeveloperUser(...)
	 *
	 * @param requestContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createDeveloperUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseContent createDeveloperUser(@RequestBody RequestContent requestContent) {
		ResponseContent responseContent = null;
		try {
			// 解析传入参数
			OpenDeveloperUserInfo openDeveloper = requestContent.getParam("openDeveloper", OpenDeveloperUserInfo.class);
			// 检查传入参数
			boolean checkResult = Boolean.TRUE;
			if (!checkResult) {
				responseContent = ResponseContent.PARAMS_ERROR();
			} else {
				openDeveloper = openDeveloperService.createDeveloperUser(openDeveloper);
				if (openDeveloper == null) {
					responseContent = ResponseContent.INNER_ERROR();
				} else {
					// 装配结果集
					responseContent = ResponseContent.SUCCESS();
					responseContent.addResultObject("openDeveloper", openDeveloper);
				}
			}
		} catch (Exception e) {
			logError(e.getMessage(), e);
			responseContent = ResponseContent.INNER_ERROR();
		}

		return responseContent;
	}
}
