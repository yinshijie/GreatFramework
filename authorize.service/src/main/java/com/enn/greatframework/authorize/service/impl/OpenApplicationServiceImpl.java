/*
 * @title com.enn.greatframework.authorize.service.impl.OpenApplicationServiceImpl.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 上午9:54:11
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.authorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo;
import com.enn.greatframework.authorize.dao.OpenDeveloperApplicationInfoDAO;
import com.enn.greatframework.authorize.service.OpenApplicationService;
import com.enn.greatframework.common.date.DateTimeFormator;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * @ClassName OpenApplicationServiceImpl
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 上午9:54:11
 *
 */
@Service("openApplicationService")
public class OpenApplicationServiceImpl implements OpenApplicationService {

	@Autowired
	private OpenDeveloperApplicationInfoDAO openDeveloperApplicationInfoDAO;

	/*
	 * @Title: getApplicationInfo
	 *
	 * @Description TODO
	 *
	 * @param applicationId
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.OpenApplicationService#getApplicationInfo(java.lang.String)
	 */
	@Override
	public OpenDeveloperApplicationInfo getApplicationInfo(String applicationId) {
		return openDeveloperApplicationInfoDAO.selectByPrimaryKey(applicationId);
	}

	/*
	 * @Title: addApplication
	 *
	 * @Description TODO
	 *
	 * @param applicationInfo
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.OpenApplicationService#addApplication(com.enn.greatframework.authorize.
	 * bean.OpenDeveloperApplicationInfo)
	 */
	@Override
	@CachePut(value = "application.info", key = "#appToken.getApplicationToken()")
	public OpenDeveloperApplicationInfo addApplication(OpenDeveloperApplicationInfo applicationInfo) {
		if (StringUtil.isBlank(applicationInfo.getDeveloperId())) {
			applicationInfo.setApplicationId(DigestorManager.GUID());
		}
		applicationInfo.setTimeStamp(DateTimeFormator.TIMESTAMP());

		int insertNum = openDeveloperApplicationInfoDAO.insertSelective(applicationInfo);
		if (insertNum > 0) {
			return applicationInfo;
		} else {
			return null;
		}
	}

	/*
	 * @Title: updateApplication
	 *
	 * @Description TODO
	 *
	 * @param applicationInfo
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.OpenApplicationService#updateApplication(com.enn.greatframework.
	 * authorize.
	 * bean.OpenDeveloperApplicationInfo)
	 */
	@Override
	@CachePut(value = "application.info", key = "#appToken.getApplicationToken()")
	public OpenDeveloperApplicationInfo updateApplication(OpenDeveloperApplicationInfo applicationInfo) {
		applicationInfo.setTimeStamp(DateTimeFormator.TIMESTAMP());

		int updateNum = openDeveloperApplicationInfoDAO.updateByPrimaryKeySelective(applicationInfo);
		if (updateNum > 0) {
			return applicationInfo;
		} else {
			return null;
		}
	}

	/*
	 * @Title: getApplicationInfoByToken
	 *
	 * @Description TODO
	 *
	 * @param appToken
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.OpenApplicationService#getApplicationInfoByToken(java.lang.String)
	 */
	@Override
	@Cacheable(value = "application.info", key = "#appToken")
	public OpenDeveloperApplicationInfo getApplicationInfoByToken(String appToken) {
		return openDeveloperApplicationInfoDAO.selectByApplicationToken(appToken);
	}
}