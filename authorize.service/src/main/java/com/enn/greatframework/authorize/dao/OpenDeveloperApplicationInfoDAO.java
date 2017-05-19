/*
 * @title com.enn.greatframework.authorize.dao.OpenDeveloperApplicationInfoDAO.java
 *
 * @Copy.Right (c)2015.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年04月05日 上午9:40:03
 *
 * @author Enn.HowMuch.MybatisGenerator
 *
 * @version V0.1.0
 *
 */
package com.enn.greatframework.authorize.dao;

import com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo;

/**
 * @InterfaceName org.mybatis.generator.api.dom.java.Interface
 * @TableName open_developer_application_info
 * @Department open_developer_application_info
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public interface OpenDeveloperApplicationInfoDAO {
    /**
     * @MethodName deleteByPrimaryKey
     * @Description TODO
     * @param java.lang.String
     * @author Enn.HowMuch.MybatisGenerator
     * Method for open_developer_application_info
     * @Enn.Howmuch.MybatisGenerator
     */
    int deleteByPrimaryKey(String applicationId);

    /**
	 * @MethodName insert
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_application_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int insert(OpenDeveloperApplicationInfo record);

    /**
	 * @MethodName insertSelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_application_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int insertSelective(OpenDeveloperApplicationInfo record);

    /**
     * @MethodName selectByPrimaryKey
     * @Description TODO
     * @param java.lang.String
     * @author Enn.HowMuch.MybatisGenerator
     * Method for open_developer_application_info
     * @Enn.Howmuch.MybatisGenerator
     */
    OpenDeveloperApplicationInfo selectByPrimaryKey(String applicationId);

    /**
	 * @MethodName updateByPrimaryKeySelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_application_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int updateByPrimaryKeySelective(OpenDeveloperApplicationInfo record);

    /**
	 * @MethodName updateByPrimaryKey
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperApplicationInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_application_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int updateByPrimaryKey(OpenDeveloperApplicationInfo record);

	/**
	 * 根据应用token获取应用信息
	 * @Description  TODO
	 * @Call com.enn.greatframework.authorize.dao.OpenDeveloperApplicationInfoDAO.selectByApplicationToken(...)
	 *
	 * @param appToken
	 * @return
	 */
	OpenDeveloperApplicationInfo selectByApplicationToken(String appToken);
}