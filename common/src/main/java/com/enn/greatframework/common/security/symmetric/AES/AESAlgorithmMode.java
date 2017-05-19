package com.enn.greatframework.common.security.symmetric.AES;

/**
 * 算法类型
 * @ClassName AESAlgorithmMode
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月8日 下午4:57:23
 *
 */
public enum AESAlgorithmMode {
	AES_CBC_NOPADDING("AES/CBC/NoPadding"), AES_CBC_PKCS5("AES/CBC/PKCS5Padding"), AES_CBC_ISO10126(
	        "AES/CBC/ISO10126Padding"), AES_CFB_NOPADDING("AES/CFB/NoPadding"), AES_CFB_PKCS5(
	                "AES/CFB/PKCS5Padding"), AES_CFB_ISO10126("AES/CFB/ISO10126Padding"), AES_ECB_NOPADDING(
	                        "AES/ECB/NoPadding"), AES_ECB_PKCS5("AES/ECB/PKCS5Padding"), AES_ECB_ISO10126(
	                                "AES/ECB/ISO10126Padding"), AES_OFB_NOPADDING("AES/OFB/NoPadding"), AES_OFB_PKCS5(
	                                        "AES/OFB/PKCS5Padding"), AES_OFB_ISO10126(
	                                                "AES/OFB/ISO10126Padding"), AES_PCBC_NOPADDING(
	                                                        "AES/PCBC/NoPadding"), AES_PCBC_PKCS5(
	                                                                "AES/PCBC/PKCS5Padding"), AES_PCBC_ISO10126(
	                                                                        "AES/PCBC/ISO10126Padding");

	private String modeName;

	private AESAlgorithmMode(String modeName) {
		this.modeName = modeName;
	}

	public String getModeName() {
		return this.modeName;
	}
}
