/*
 * @title com.enn.greatframework.authorize.service.OpenDeveloperService.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 上午9:41:28
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.service;

import com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo;

/**
 * 开放平台开发者服务
 * @ClassName OpenDeveloperService
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 上午9:41:28
 *
 */
public interface OpenDeveloperService {

	/**
	 * 根据开发者ID获取开发者信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenDeveloperService.getDeveloperUserInfo(...)
	 *
	 * @param developerId
	 * @return
	 */
	OpenDeveloperUserInfo getDeveloperUserInfo(String developerId);

	/**
	 * 创建开发者
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenDeveloperService.createDeveloperUser(...)
	 *
	 * @param openDeveloperUserInfo
	 * @return
	 */
	OpenDeveloperUserInfo createDeveloperUser(OpenDeveloperUserInfo openDeveloperUserInfo);

	/**
	 * 修改开发者信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.service.OpenDeveloperService.updateDeveloperUser(...)
	 *
	 * @param openDeveloperUserInfo
	 * @return
	 */
	OpenDeveloperUserInfo updateDeveloperUser(OpenDeveloperUserInfo openDeveloperUserInfo);
}
