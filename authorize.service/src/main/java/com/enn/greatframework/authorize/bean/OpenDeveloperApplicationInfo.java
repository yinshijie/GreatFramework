/*
 * @title com.enn.greatframework.gateway.bean.OpenDeveloperApplicationInfo.java
 * @Copy.Right (c)2015.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年04月05日 上午11:27:48
 * @author Enn.HowMuch.MybatisGenerator
 * @version V0.1.0
 *
 */
package com.enn.greatframework.authorize.bean;

import java.io.Serializable;

/**
 * @ClassName org.mybatis.generator.api.dom.java.TopLevelClass
 * @TableName open_developer_application_info
 * @Department open_developer_application_info
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public class OpenDeveloperApplicationInfo implements Serializable {
    /**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = -7393605553985913768L;

	/**
	 * @Department 应用ID
	 * @DatabaseTable open_developer_application_info
	 * @DatabaseColumn APPLICATION_ID
	 * @Enn.Howmuch.MybatisGenerator
	 */
    private String applicationId;

    /**
     * @Department 开发者ID
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn DEVELOPER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    private String developerId;

    /**
     * @Department 应用名称
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn APPLICATION_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    private String applicationName;

    /**
     * @Department 应用推送消息入口
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn APPLICATION_PUSH_URL
     * @Enn.Howmuch.MybatisGenerator
     */
    private String applicationPushUrl;

    /**
     * @Department 应用TOKEN
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn APPLICATION_TOKEN
     * @Enn.Howmuch.MybatisGenerator
     */
    private String applicationToken;

    /**
	 * @Department 消息加密模式:0-不加密1-AES加密2-BASE64加密
	 * @DatabaseTable open_developer_application_info
	 * @DatabaseColumn ENCODE_TYPE
	 * @Enn.Howmuch.MybatisGenerator
	 */
    private String encodeType;

    /**
     * @Department 加密AES密钥
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn ENCODE_AES_KEY
     * @Enn.Howmuch.MybatisGenerator
     */
    private String encodeAesKey;

    /**
     * @Department 时间戳，格式：yyyyMMddHHmmss，可使用公用方法DateTimeFormator.TIMESTAMP()获取
     * @DatabaseTable open_developer_application_info
     * @DatabaseColumn TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    private String timeStamp;

    /**
     * 获取open_developer_application_info.APPLICATION_ID
     *
     * @return open_developer_application_info.APPLICATION_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * 设置open_developer_application_info.APPLICATION_ID
     *
     * @param setopen_developer_application_info.APPLICATION_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }

    /**
     * 获取open_developer_application_info.DEVELOPER_ID
     *
     * @return open_developer_application_info.DEVELOPER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getDeveloperId() {
        return developerId;
    }

    /**
     * 设置open_developer_application_info.DEVELOPER_ID
     *
     * @param setopen_developer_application_info.DEVELOPER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setDeveloperId(String developerId) {
        this.developerId = developerId == null ? null : developerId.trim();
    }

    /**
     * 获取open_developer_application_info.APPLICATION_NAME
     *
     * @return open_developer_application_info.APPLICATION_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * 设置open_developer_application_info.APPLICATION_NAME
     *
     * @param setopen_developer_application_info.APPLICATION_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    /**
     * 获取open_developer_application_info.APPLICATION_PUSH_URL
     *
     * @return open_developer_application_info.APPLICATION_PUSH_URL
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getApplicationPushUrl() {
        return applicationPushUrl;
    }

    /**
     * 设置open_developer_application_info.APPLICATION_PUSH_URL
     *
     * @param setopen_developer_application_info.APPLICATION_PUSH_URL
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setApplicationPushUrl(String applicationPushUrl) {
        this.applicationPushUrl = applicationPushUrl == null ? null : applicationPushUrl.trim();
    }

    /**
     * 获取open_developer_application_info.APPLICATION_TOKEN
     *
     * @return open_developer_application_info.APPLICATION_TOKEN
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getApplicationToken() {
        return applicationToken;
    }

    /**
     * 设置open_developer_application_info.APPLICATION_TOKEN
     *
     * @param setopen_developer_application_info.APPLICATION_TOKEN
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken == null ? null : applicationToken.trim();
    }

    /**
     * 获取open_developer_application_info.ENCODE_TYPE
     *
     * @return open_developer_application_info.ENCODE_TYPE
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getEncodeType() {
        return encodeType;
    }

    /**
     * 设置open_developer_application_info.ENCODE_TYPE
     *
     * @param setopen_developer_application_info.ENCODE_TYPE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType == null ? null : encodeType.trim();
    }

    /**
     * 获取open_developer_application_info.ENCODE_AES_KEY
     *
     * @return open_developer_application_info.ENCODE_AES_KEY
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getEncodeAesKey() {
        return encodeAesKey;
    }

    /**
     * 设置open_developer_application_info.ENCODE_AES_KEY
     *
     * @param setopen_developer_application_info.ENCODE_AES_KEY
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setEncodeAesKey(String encodeAesKey) {
        this.encodeAesKey = encodeAesKey == null ? null : encodeAesKey.trim();
    }

    /**
     * 获取open_developer_application_info.TIME_STAMP
     *
     * @return open_developer_application_info.TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置open_developer_application_info.TIME_STAMP
     *
     * @param setopen_developer_application_info.TIME_STAMP
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp == null ? null : timeStamp.trim();
    }
}