/*
 * @title com.enn.greatframework.authorize.dao.OpenDeveloperUserInfoDAO.java
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

import com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo;

/**
 * @InterfaceName org.mybatis.generator.api.dom.java.Interface
 * @TableName open_developer_user_info
 * @Department open_developer_user_info
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public interface OpenDeveloperUserInfoDAO {
    /**
     * @MethodName deleteByPrimaryKey
     * @Description TODO
     * @param java.lang.String
     * @author Enn.HowMuch.MybatisGenerator
     * Method for open_developer_user_info
     * @Enn.Howmuch.MybatisGenerator
     */
    int deleteByPrimaryKey(String developerId);

    /**
	 * @MethodName insert
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_user_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int insert(OpenDeveloperUserInfo record);

    /**
	 * @MethodName insertSelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_user_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int insertSelective(OpenDeveloperUserInfo record);

    /**
     * @MethodName selectByPrimaryKey
     * @Description TODO
     * @param java.lang.String
     * @author Enn.HowMuch.MybatisGenerator
     * Method for open_developer_user_info
     * @Enn.Howmuch.MybatisGenerator
     */
    OpenDeveloperUserInfo selectByPrimaryKey(String developerId);

    /**
	 * @MethodName updateByPrimaryKeySelective
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_user_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int updateByPrimaryKeySelective(OpenDeveloperUserInfo record);

    /**
	 * @MethodName updateByPrimaryKey
	 * @Description TODO
	 * @param com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo
	 * @author Enn.HowMuch.MybatisGenerator
	 * Method for open_developer_user_info
	 * @Enn.Howmuch.MybatisGenerator
	 */
    int updateByPrimaryKey(OpenDeveloperUserInfo record);
}