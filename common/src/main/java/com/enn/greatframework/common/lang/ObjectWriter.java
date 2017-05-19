package com.enn.greatframework.common.lang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.enn.greatframework.common.GreatFrameworkException;

/**
 * 序列化类写入文件工具
 *
 * @ClassName ObjectWriter
 * @Description TODO
 * @author Enn.HowMuch.yinshijie
 * @date 2015年12月10日 下午1:48:52
 *
 */
public class ObjectWriter {

	private static final Logger LOGGER = Logger.getLogger(ObjectWriter.class);

	/**
	 * 将一个object实例写入一个文件中<br/>
	 * object必须继承java.io.Serializable
	 *
	 * @param object
	 * @param file
	 * @throws JedidiahException
	 * @throws IOException
	 */
	public static void writeObjectToFile(Object object, File file) throws GreatFrameworkException {
		if (!(object instanceof Serializable)) {
			throw new GreatFrameworkException("The object of the param must be to implement the java.io.Serializable!");
		}
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 将一个object实例写入一个byte数组中<br/>
	 * object必须继承java.io.Serializable
	 *
	 * @param object
	 * @param file
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object object) throws GreatFrameworkException, IOException {
		if (!(object instanceof Serializable)) {
			throw new GreatFrameworkException("The object of the param must be to implement the java.io.Serializable!");
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		byte[] bytes = outputStream.toByteArray();
		objectOutputStream.close();
		return bytes;
	}
}
