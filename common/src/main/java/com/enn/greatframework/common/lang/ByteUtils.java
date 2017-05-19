/*
 * @title com.enn.greatframework.common.lang.ByteUtils.java
 * @Copy.Right (c)2017.好买气电子商务有限公司
 * @Department 技术开发部
 * @date 2017年3月9日 上午8:57:30
 * @author Enn.HowMuch.yinshijie
 * @version V0.1.0
 */
package com.enn.greatframework.common.lang;

/**
 * @ClassName ByteUtils
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2017年3月9日 上午8:57:30
 *
 */
public class ByteUtils {

	/**
	 * 生成4个字节的网络字节序
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.lang.ByteUtils.getNetworkBytesOrder(...)
	 *
	 * @param source
	 * @return
	 */
	public static final byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	/**
	 * 还原4个字节的网络字节序
	 * @Description  TODO
	 * @Call com.enn.greatframework.common.lang.ByteUtils.recoverNetworkBytesOrder(...)
	 *
	 * @param orderBytes
	 * @return
	 */
	public static final int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}
}
