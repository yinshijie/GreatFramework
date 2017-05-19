/*
 * @title com.enn.greatframework.authorize.bean.OpenDeveloperUserInfo.java
 * @Copy.Right (c)2015.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年04月07日 上午11:12:39
 * @author Enn.HowMuch.MybatisGenerator
 * @version V0.1.0
 *
 */
package com.enn.greatframework.authorize.bean;

import java.io.Serializable;

/**
 * @ClassName org.mybatis.generator.api.dom.java.TopLevelClass
 * @TableName open_developer_user_info
 * @Department open_developer_user_info
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public class OpenDeveloperUserInfo implements Serializable {
    /**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = 6193889782694094186L;

	/**
	 * @Department 开发者ID
	 * @DatabaseTable open_developer_user_info
	 * @DatabaseColumn DEVELOPER_ID
	 * @Enn.Howmuch.MybatisGenerator
	 */
    private String developerId;

    /**
     * @Department 开发者登录ID
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_LOGIN_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerLoginId;

    /**
     * @Department 开发者密码
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerPassword;

    /**
     * @Department 开发者名称
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerName;

    /**
     * @Department 开发者联系方式
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_TELNO
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerTelno;

    /**
     * @Department 开发者所属公司
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn COMPANY_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    private String companyName;

    /**
     * @Department 公司营业执照
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn COMPANY_LICENSE_IMAGE
     * @Enn.Howmuch.MybatisGenerator
     */
    private String companyLicenseImage;

    /**
     * @Department
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerStatus;

    /**
     * @Department 开发者认证状态
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_AUTH_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerAuthStatus;

    /**
     * @Department 开发者认证级别
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn DEVELOPER_AUTH_LEVEL
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerAuthLevel;

    /**
     * @Department 时间戳，格式：yyyyMMddHHmmss，可使用公用方法DateTimeFormator.TIMESTAMP()获取
     * @DatabaseTable open_developer_user_info
     * @DatabaseColumn TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    private String timeStamp;

    /**
     * 获取open_developer_user_info.DEVELOPER_ID
     *
     * @return open_developer_user_info.DEVELOPER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperId() {
        return developerId;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_ID
     *
     * @param setopen_developer_user_info.DEVELOPER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperId(String developerId) {
        this.developerId = developerId == null ? null : developerId.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_LOGIN_ID
     *
     * @return open_developer_user_info.DEVELOPER_LOGIN_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperLoginId() {
        return developerLoginId;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_LOGIN_ID
     *
     * @param setopen_developer_user_info.DEVELOPER_LOGIN_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperLoginId(String developerLoginId) {
        this.developerLoginId = developerLoginId == null ? null : developerLoginId.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_PASSWORD
     *
     * @return open_developer_user_info.DEVELOPER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperPassword() {
        return developerPassword;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_PASSWORD
     *
     * @param setopen_developer_user_info.DEVELOPER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperPassword(String developerPassword) {
        this.developerPassword = developerPassword == null ? null : developerPassword.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_NAME
     *
     * @return open_developer_user_info.DEVELOPER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperName() {
        return developerName;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_NAME
     *
     * @param setopen_developer_user_info.DEVELOPER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperName(String developerName) {
        this.developerName = developerName == null ? null : developerName.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_TELNO
     *
     * @return open_developer_user_info.DEVELOPER_TELNO
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperTelno() {
        return developerTelno;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_TELNO
     *
     * @param setopen_developer_user_info.DEVELOPER_TELNO
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperTelno(String developerTelno) {
        this.developerTelno = developerTelno == null ? null : developerTelno.trim();
    }

    /**
     * 获取open_developer_user_info.COMPANY_NAME
     *
     * @return open_developer_user_info.COMPANY_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置open_developer_user_info.COMPANY_NAME
     *
     * @param setopen_developer_user_info.COMPANY_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取open_developer_user_info.COMPANY_LICENSE_IMAGE
     *
     * @return open_developer_user_info.COMPANY_LICENSE_IMAGE
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCompanyLicenseImage() {
        return companyLicenseImage;
    }

    /**
     * 设置open_developer_user_info.COMPANY_LICENSE_IMAGE
     *
     * @param setopen_developer_user_info.COMPANY_LICENSE_IMAGE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCompanyLicenseImage(String companyLicenseImage) {
        this.companyLicenseImage = companyLicenseImage == null ? null : companyLicenseImage.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_STATUS
     *
     * @return open_developer_user_info.DEVELOPER_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperStatus() {
        return developerStatus;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_STATUS
     *
     * @param setopen_developer_user_info.DEVELOPER_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperStatus(String developerStatus) {
        this.developerStatus = developerStatus == null ? null : developerStatus.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_AUTH_STATUS
     *
     * @return open_developer_user_info.DEVELOPER_AUTH_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperAuthStatus() {
        return developerAuthStatus;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_AUTH_STATUS
     *
     * @param setopen_developer_user_info.DEVELOPER_AUTH_STATUS
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperAuthStatus(String developerAuthStatus) {
        this.developerAuthStatus = developerAuthStatus == null ? null : developerAuthStatus.trim();
    }

    /**
     * 获取open_developer_user_info.DEVELOPER_AUTH_LEVEL
     *
     * @return open_developer_user_info.DEVELOPER_AUTH_LEVEL
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperAuthLevel() {
        return developerAuthLevel;
    }

    /**
     * 设置open_developer_user_info.DEVELOPER_AUTH_LEVEL
     *
     * @param setopen_developer_user_info.DEVELOPER_AUTH_LEVEL
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperAuthLevel(String developerAuthLevel) {
        this.developerAuthLevel = developerAuthLevel == null ? null : developerAuthLevel.trim();
    }

    /**
     * 获取open_developer_user_info.TIME_STAMP
     *
     * @return open_developer_user_info.TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置open_developer_user_info.TIME_STAMP
     *
     * @param setopen_developer_user_info.TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp == null ? null : timeStamp.trim();
    }
}