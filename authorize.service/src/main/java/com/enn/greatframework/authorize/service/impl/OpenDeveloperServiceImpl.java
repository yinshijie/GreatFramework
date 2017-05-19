/*
 * @title com.enn.greatframework.authorize.service.impl.OpenDeveloperServiceImpl.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月5日 上午9:50:57
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

import com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo;
import com.enn.greatframework.authorize.dao.OpenDeveloperUserInfoDAO;
import com.enn.greatframework.authorize.service.OpenDeveloperService;
import com.enn.greatframework.common.date.DateTimeFormator;
import com.enn.greatframework.common.lang.StringUtil;
import com.enn.greatframework.common.security.digest.DigestorManager;

/**
 * 开放平台开发者服务实现
 * @ClassName OpenDeveloperServiceImpl
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月5日 上午9:50:57
 *
 */
@Service("openDeveloperService")
public class OpenDeveloperServiceImpl implements OpenDeveloperService {

	@Autowired
	private OpenDeveloperUserInfoDAO openDeveloperUserInfoDAO;

	/*
	 * @Title: getDeveloperUserInfo
	 *
	 * @Description TODO
	 *
	 * @param developerId
	 *
	 * @return
	 *
	 * @see com.enn.greatframework.authorize.service.OpenDeveloperService#getDeveloperUserInfo(java.lang.String)
	 */
	@Override
	@Cacheable(value = "developer.info", key = "#developerId")
	public OpenDeveloperUserInfo getDeveloperUserInfo(String developerId) {
		return openDeveloperUserInfoDAO.selectByPrimaryKey(developerId);
	}

	/*
	 * @Title: createDeveloperUser
	 *
	 * @Description TODO
	 *
	 * @param openDeveloperUserInfo
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.OpenDeveloperService#createDeveloperUser(com.enn.greatframework.
	 * gateway.bean.OpenDeveloperUserInfo)
	 */
	@Override
	@CachePut(value = "developer.info", key = "#openDeveloperUserInfo.getDeveloperId()")
	public OpenDeveloperUserInfo createDeveloperUser(OpenDeveloperUserInfo openDeveloperUserInfo) {
		if (StringUtil.isBlank(openDeveloperUserInfo.getDeveloperId())) {
			openDeveloperUserInfo.setDeveloperId(DigestorManager.GUID());
		}
		openDeveloperUserInfo.setTimeStamp(DateTimeFormator.TIMESTAMP());

		int insertNum = openDeveloperUserInfoDAO.insertSelective(openDeveloperUserInfo);
		if (insertNum > 0) {
			return openDeveloperUserInfo;
		} else {
			return null;
		}
	}

	/*
	 * @Title: updateDeveloperUser
	 *
	 * @Description TODO
	 *
	 * @param openDeveloperUserInfo
	 *
	 * @return
	 *
	 * @see
	 * com.enn.greatframework.authorize.service.OpenDeveloperService#updateDeveloperUser(com.enn.greatframework.
	 * gateway.bean.OpenDeveloperUserInfo)
	 */
	@Override
	@CachePut(value = "developer.info", key = "#openDeveloperUserInfo.getDeveloperId()")
	public OpenDeveloperUserInfo updateDeveloperUser(OpenDeveloperUserInfo openDeveloperUserInfo) {
		openDeveloperUserInfo.setTimeStamp(DateTimeFormator.TIMESTAMP());

		int updateNum = openDeveloperUserInfoDAO.updateByPrimaryKeySelective(openDeveloperUserInfo);
		if (updateNum > 0) {
			return openDeveloperUserInfo;
		} else {
			return null;
		}
	}

}
