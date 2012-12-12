package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

/**
 * 资源文件工具类
 * 
 * @author yanbin
 * 
 */
public class PropertyUtils {

	/** 记录日志 */
	protected static Log log = LogFactory.getLog(MessageUtils.class);

	/**
	 * 从propertits文件中读取信息
	 * 
	 * @param resource
	 * @return
	 */
	public static Properties readFromResource(Resource resource) throws IOException {
		Properties properties = new Properties();
		InputStream is = resource.getInputStream();
		if (null != is) {
			properties.load(is);
		} else {
			throw new IOException("inputstream resource is null");
		}
		return properties;
	}

	/**
	 * 根据属性文件中的环境变量关键字获取对应的环境变量值（获取字符串型的值）
	 * 
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(Properties properties, String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * 根据key到对应资源文件下获取value
	 * 
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties properties, String key) {
		if (StringUtils.isEmpty(key)) {
			return "";
		}
		String value = properties.getProperty(key);
		if (null == value) {
			log.warn("the key : " + key + "no value,check it");
			return "";
		}
		return value;
	}

	/**
	 * 根据属性文件中的环境变量关键字获取对应的环境变量（获取布尔型的值） 找不到返回默认值
	 * 
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanProperty(Properties properties, String key, boolean defaultValue) {
		// 类似getProperty的jdk源码实现
		String value = properties.getProperty(key);
		if (null == value) {
			return defaultValue;
		}
		// new Boolean(value):如果value值为true则返回true的类型，其他都返回false类型
		return (new Boolean(value)).booleanValue();
	}

	/**
	 * 根据属性文件中的环境变量关键字获取对应的环境变量（获取布尔型的值） 找不到返回false
	 * 
	 * @param properties
	 * @param key
	 * @return
	 */
	public static boolean getBooleanProperty(Properties properties, String key) {
		return getBooleanProperty(properties, key, false);
	}

	/**
	 * 根据属性文件中的环境变量关键字获取对应的环境变量（获取int型的值） 找不到返回默认值
	 * 
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntProperty(Properties properties, String key, int defaultValue) {
		// 类似getProperty的jdk源码实现
		String value = properties.getProperty(key);
		if (null == value) {
			return defaultValue;
		}
		// 这个转换如果获取的值是字符串或者其他不能转换为int型的类型。会抛出NumberFormatException异常
		return (new Integer(value)).intValue();
	}

	/**
	 * 根据属性文件中的环境变量关键字获取对应的环境变量（获取int型的值） 找不到返回0
	 * 
	 * @param properties
	 * @param key
	 * @return
	 */
	public static int getIntProperty(Properties properties, String key) {
		return getIntProperty(properties, key, 0);
	}

}
