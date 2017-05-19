package com.enn.greatframework.common.lang;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 序列化类文件读取工具
 *
 * @ClassName ObjectReader
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2015年12月10日 下午1:48:24
 *
 */
public class ObjectReader {
	/**
	 * 从文件中读取一个OBJECT
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObjectFromFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		return object;
	}

	/**
	 * 从byte数组读取一个OBJECT
	 *
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObjectFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		byteArrayInputStream.close();
		return object;
	}

	/**
	 * 从一个输入流读取一个OBJECT
	 *
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObjectFromInputStream(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
}
