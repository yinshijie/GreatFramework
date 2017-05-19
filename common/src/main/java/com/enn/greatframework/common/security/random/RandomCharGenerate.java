/*
 * @title com.enn.greatframework.common.security.random.RandomCharGenerate.java
 * @Copy.Right (c)2017.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年2月4日 上午9:34:31
 * @author Enn.HowMuch.yinshijie
 * @version V0.1.0
 */
package com.enn.greatframework.common.security.random;

import java.util.Random;

import com.enn.greatframework.common.lang.StringUtil;

/**
 * 随机字符生成工具
 * @ClassName RandomCharGenerate
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年2月4日 上午9:34:31
 *
 */
public class RandomCharGenerate {

	/**
	 * 默认随机字符串包含的字符
	 */
	public static final String RANDOM_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String RANDOM_CHARS_SYMBOL = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+=-[]{}*.";

	/**
	 * 根据指定的字符串长度和字符串包含字符生成指定的随机字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.random.RandomCharGenerate.getRandomChars(...)
	 *
	 * @param charsLength
	 * @param randomChars
	 * @return
	 */
	public static String getRandomChars(int charsLength, String randomChars) {
		if (StringUtil.isBlank(randomChars)) {
			randomChars = RANDOM_CHARS;
		}

		int codesLen = randomChars.length();
		Random rand = new Random(System.nanoTime());
		StringBuilder verifyCode = new StringBuilder(charsLength);
		for (int i = 0; i < charsLength; i++) {
			verifyCode.append(randomChars.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}

	/**
	 * 根据指定的字符串长度生成指定的随机字符串
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.security.random.RandomCharGenerate.getRandomChars(...)
	 *
	 * @param charsLength
	 * @return
	 */
	public static String getRandomChars(int charsLength) {
		return getRandomChars(charsLength, RANDOM_CHARS);
	}

	public static void main(String[] args) {
		System.out.println(getRandomChars(24));
	}
}
