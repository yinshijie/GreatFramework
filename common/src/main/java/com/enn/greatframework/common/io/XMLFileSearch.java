/*
 * @title com.enn.greatframework.common.io.XMLFileSearch.java
 *
 * @Copy.Right (c)2017.好买气电子商务有限公司
 *
 * @Department 技术开发部
 *
 * @date 2017年4月19日 上午10:57:54
 *
 * @author Enn.HowMuch.yinshijie
 *
 * @version V0.1.0
 */
package com.enn.greatframework.common.io;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class XMLFileSearch {
	private String fileName = null;
	private String xmlPath;
	private static volatile XMLFileSearch instance = null;

	public void init(String paramString) {
		URL localURL;
		if (this.fileName != null) {
			localURL = super.getClass().getClassLoader().getResource(this.fileName);
			if (localURL != null) {
				this.xmlPath = localURL.getPath();
			}
		} else {
			localURL = XMLFileSearch.class.getProtectionDomain().getCodeSource().getLocation();
			String str1 = localURL.toString();
			if (str1.startsWith("zip")) {
				str1 = str1.substring(4);
			} else if (str1.startsWith("file")) {
				str1 = str1.substring(6);
			} else if (str1.startsWith("jar")) {
				str1 = str1.substring(10);
			}
			try {
				str1 = URLDecoder.decode(str1, "UTF-8");
			} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
				localUnsupportedEncodingException.printStackTrace();
			}
			str1 = str1.substring(0, str1.length() - 1);
			int i = str1.lastIndexOf("/");
			str1 = str1.substring(0, i);
			String str2 = str1 + "/" + paramString;
			if (!(str2.startsWith("/"))) {
				str2 = "/" + str2;
			}
			if (str2.contains("vfs:")) {
				str2 = str2.substring(5, str2.length());
			}
			this.xmlPath = str2;
		}
	}

	public static XMLFileSearch getInstance() {
		if (instance == null) {
			synchronized (XMLFileSearch.class) {
				if (instance == null) {
					instance = new XMLFileSearch();
				}
			}
		}
		return instance;
	}

	void setFileName(String paramString) {
		if ((paramString == null) || ("".equals(paramString))) {
			return;
		}
		this.fileName = paramString;
	}

	public String getXmlPath() {
		return this.xmlPath;
	}

	public static void main(String[] args) {
		XMLFileSearch.getInstance().init("rsa/__RSA_PAIR.txt");
	}
}