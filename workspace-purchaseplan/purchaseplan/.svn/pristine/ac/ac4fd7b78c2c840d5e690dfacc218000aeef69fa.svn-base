package com.youzhicai.purchaseplan.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 读取config.properties配置文件中的数据
 * 
 * @author Administrator
 *
 */
public class ConfigUtil {
	private static Properties pro = new Properties();

	static {
		Resource resource = new ClassPathResource("application.properties");
		InputStream ips = null;
		try {
			ips = resource.getInputStream();
			pro.load(new InputStreamReader(ips, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ips != null) {
					ips.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取配置文件的中配置的值
	public static String getValue(String key) {
		String value;
		return (value = pro.getProperty(key)) == null ? value : value.trim();
	}
}
