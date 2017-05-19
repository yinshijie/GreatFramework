/*
 * @title com.enn.greatframework.common.io.FileUtils.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月19日 上午10:41:42
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.io;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * 文件工具
 * @ClassName FileUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年4月19日 上午10:41:42
 *
 */
public class FileUtils {

	private static final Logger LOGGER = Logger.getLogger(FileUtils.class);

	/**
	 * 关闭传入的inputStream
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.io.FileUtils.close(...)
	 *
	 * @param paramInputStream
	 */
	public static void close(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 关闭传入的outputStream
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.io.FileUtils.close(...)
	 *
	 * @param outputStream
	 */
	public static void close(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
