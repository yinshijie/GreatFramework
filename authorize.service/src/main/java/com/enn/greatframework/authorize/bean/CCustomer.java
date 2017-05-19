/*
 * @title com.enn.greatframework.authorize.bean.CCustomer.java
 * @Copy.Right (c)2015.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年02月14日 下午4:07:01
 * @author Enn.HowMuch.MybatisGenerator
 * @version V0.1.0
 *
 */
package com.enn.greatframework.authorize.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName org.mybatis.generator.api.dom.java.TopLevelClass
 * @TableName c_customer
 * @Department c_customer
 * @version V0.1.0
 * @Enn.Howmuch.MybatisGenerator
 */
public class CCustomer implements Serializable {
    /**
	 * @Fields serialVersionUID TODO
	 */
	private static final long serialVersionUID = 1116339233178495354L;

	/**
	 * @Department 不带符号（大括号，横杠）的GUID
	 * @DatabaseTable c_customer
	 * @DatabaseColumn CUSTOMER_ID
	 * @Enn.Howmuch.MybatisGenerator
	 */
    private String customerId;

    /**
     * @Department 用户账号
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerName;

    /**
     * @Department 用户昵称
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_TITLE
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerTitle;

    /**
     * @Department 账号头像
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_ICON_PATH
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerIconPath;

    /**
     * @Department 登录密码，加过密
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerPassword;

    /**
     * @Department 登录手机号
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerMobile;

    /**
     * @Department 登录邮箱
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerEmail;

    /**
     * @Department 注册时间
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_CREATETIME
     * @Enn.Howmuch.MybatisGenerator
     */
    private Date customerCreatetime;

    /**
     * @Department 父账号
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_PARENT_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerParentId;

    /**
     * @Department 安全手机
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_SEC_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerSecMobile;

    /**
     * @Department 安全邮箱
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_SEC_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    private String customerSecEmail;

    /**
     * @Department 账号状态:0冻结;1激活;2未激活;3枚举扩展
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_STATE
     * @Enn.Howmuch.MybatisGenerator
     */
    private Integer customerState;

    /**
     * @Department 安全问题是否启用
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_SEC_QA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    private Boolean customerSecQaEnable;

    /**
     * @Department 安全控件是否启用
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_SEC_CONTROL_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    private Boolean customerSecControlEnable;

    /**
     * @Department 数字证书是否启用
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_SEC_CA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    private Boolean customerSecCaEnable;

    /**
     * @Department
     * @DatabaseTable c_customer
     * @DatabaseColumn CUSTOMER_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    private Integer customerEnable;

    /**
     * @Department 更新时间戳字段
     * @DatabaseTable c_customer
     * @DatabaseColumn update_time
     * @Enn.Howmuch.MybatisGenerator
     */
    private Date updateTime;

    /**
     * 获取c_customer.CUSTOMER_ID
     *
     * @return c_customer.CUSTOMER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置c_customer.CUSTOMER_ID
     *
     * @param setc_customer.CUSTOMER_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_NAME
     *
     * @return c_customer.CUSTOMER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置c_customer.CUSTOMER_NAME
     *
     * @param setc_customer.CUSTOMER_NAME
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_TITLE
     *
     * @return c_customer.CUSTOMER_TITLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerTitle() {
        return customerTitle;
    }

    /**
     * 设置c_customer.CUSTOMER_TITLE
     *
     * @param setc_customer.CUSTOMER_TITLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle == null ? null : customerTitle.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_ICON_PATH
     *
     * @return c_customer.CUSTOMER_ICON_PATH
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerIconPath() {
        return customerIconPath;
    }

    /**
     * 设置c_customer.CUSTOMER_ICON_PATH
     *
     * @param setc_customer.CUSTOMER_ICON_PATH
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerIconPath(String customerIconPath) {
        this.customerIconPath = customerIconPath == null ? null : customerIconPath.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_PASSWORD
     *
     * @return c_customer.CUSTOMER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * 设置c_customer.CUSTOMER_PASSWORD
     *
     * @param setc_customer.CUSTOMER_PASSWORD
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword == null ? null : customerPassword.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_MOBILE
     *
     * @return c_customer.CUSTOMER_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerMobile() {
        return customerMobile;
    }

    /**
     * 设置c_customer.CUSTOMER_MOBILE
     *
     * @param setc_customer.CUSTOMER_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_EMAIL
     *
     * @return c_customer.CUSTOMER_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * 设置c_customer.CUSTOMER_EMAIL
     *
     * @param setc_customer.CUSTOMER_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail == null ? null : customerEmail.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_CREATETIME
     *
     * @return c_customer.CUSTOMER_CREATETIME
     * @Enn.Howmuch.MybatisGenerator
     */
    public Date getCustomerCreatetime() {
        return customerCreatetime;
    }

    /**
     * 设置c_customer.CUSTOMER_CREATETIME
     *
     * @param setc_customer.CUSTOMER_CREATETIME
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerCreatetime(Date customerCreatetime) {
        this.customerCreatetime = customerCreatetime;
    }

    /**
     * 获取c_customer.CUSTOMER_PARENT_ID
     *
     * @return c_customer.CUSTOMER_PARENT_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerParentId() {
        return customerParentId;
    }

    /**
     * 设置c_customer.CUSTOMER_PARENT_ID
     *
     * @param setc_customer.CUSTOMER_PARENT_ID
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerParentId(String customerParentId) {
        this.customerParentId = customerParentId == null ? null : customerParentId.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_SEC_MOBILE
     *
     * @return c_customer.CUSTOMER_SEC_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerSecMobile() {
        return customerSecMobile;
    }

    /**
     * 设置c_customer.CUSTOMER_SEC_MOBILE
     *
     * @param setc_customer.CUSTOMER_SEC_MOBILE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerSecMobile(String customerSecMobile) {
        this.customerSecMobile = customerSecMobile == null ? null : customerSecMobile.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_SEC_EMAIL
     *
     * @return c_customer.CUSTOMER_SEC_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    public String getCustomerSecEmail() {
        return customerSecEmail;
    }

    /**
     * 设置c_customer.CUSTOMER_SEC_EMAIL
     *
     * @param setc_customer.CUSTOMER_SEC_EMAIL
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerSecEmail(String customerSecEmail) {
        this.customerSecEmail = customerSecEmail == null ? null : customerSecEmail.trim();
    }

    /**
     * 获取c_customer.CUSTOMER_STATE
     *
     * @return c_customer.CUSTOMER_STATE
     * @Enn.Howmuch.MybatisGenerator
     */
    public Integer getCustomerState() {
        return customerState;
    }

    /**
     * 设置c_customer.CUSTOMER_STATE
     *
     * @param setc_customer.CUSTOMER_STATE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }

    /**
     * 获取c_customer.CUSTOMER_SEC_QA_ENABLE
     *
     * @return c_customer.CUSTOMER_SEC_QA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public Boolean getCustomerSecQaEnable() {
        return customerSecQaEnable;
    }

    /**
     * 设置c_customer.CUSTOMER_SEC_QA_ENABLE
     *
     * @param setc_customer.CUSTOMER_SEC_QA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerSecQaEnable(Boolean customerSecQaEnable) {
        this.customerSecQaEnable = customerSecQaEnable;
    }

    /**
     * 获取c_customer.CUSTOMER_SEC_CONTROL_ENABLE
     *
     * @return c_customer.CUSTOMER_SEC_CONTROL_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public Boolean getCustomerSecControlEnable() {
        return customerSecControlEnable;
    }

    /**
     * 设置c_customer.CUSTOMER_SEC_CONTROL_ENABLE
     *
     * @param setc_customer.CUSTOMER_SEC_CONTROL_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerSecControlEnable(Boolean customerSecControlEnable) {
        this.customerSecControlEnable = customerSecControlEnable;
    }

    /**
     * 获取c_customer.CUSTOMER_SEC_CA_ENABLE
     *
     * @return c_customer.CUSTOMER_SEC_CA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public Boolean getCustomerSecCaEnable() {
        return customerSecCaEnable;
    }

    /**
     * 设置c_customer.CUSTOMER_SEC_CA_ENABLE
     *
     * @param setc_customer.CUSTOMER_SEC_CA_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerSecCaEnable(Boolean customerSecCaEnable) {
        this.customerSecCaEnable = customerSecCaEnable;
    }

    /**
     * 获取c_customer.CUSTOMER_ENABLE
     *
     * @return c_customer.CUSTOMER_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public Integer getCustomerEnable() {
        return customerEnable;
    }

    /**
     * 设置c_customer.CUSTOMER_ENABLE
     *
     * @param setc_customer.CUSTOMER_ENABLE
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setCustomerEnable(Integer customerEnable) {
        this.customerEnable = customerEnable;
    }

    /**
     * 获取c_customer.update_time
     *
     * @return c_customer.update_time
     * @Enn.Howmuch.MybatisGenerator
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置c_customer.update_time
     *
     * @param setc_customer.update_time
     * @Enn.Howmuch.MybatisGenerator
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}