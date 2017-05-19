/*
 * @title com.enn.greatframework.authorize.service.OpenApplicationService.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 上午9:51:22
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.service;

import com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo;

/**
 * 开放平台应用服务
 * @ClassName OpenApplicationService
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 上午9:51:22
 *
 */
public interface OpenApplicationService {

	/**
	 * 获取开发平台应用信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenApplicationService.getApplicationInfo(...)
	 *
	 * @param applicationId
	 * @return
	 */
	OpenDeveloperApplicationInfo getApplicationInfo(String applicationId);

	/**
	 * 新增应用
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenApplicationService.addApplication(...)
	 *
	 * @param applicationInfo
	 * @return
	 */
	OpenDeveloperApplicationInfo addApplication(OpenDeveloperApplicationInfo applicationInfo);

	/**
	 * 修改应用信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenApplicationService.updateApplication(...)
	 *
	 * @param applicationInfo
	 * @return
	 */
	OpenDeveloperApplicationInfo updateApplication(OpenDeveloperApplicationInfo applicationInfo);

	/**
	 * 根据APP_TOKEN获取应用信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenApplicationService.getApplicationInfoByToken(...)
	 *
	 * @param appToken
	 * @return
	 */
	OpenDeveloperApplicationInfo getApplicationInfoByToken(String appToken);
}
