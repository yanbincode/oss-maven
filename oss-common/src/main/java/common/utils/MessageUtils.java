package common.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

/**
 * 资源文件读取类
 * 
 * @author yanbin
 * 
 */
public class MessageUtils {

	private static Properties exceptionPro = null;
	private static Properties infoPro = null;
	private static Properties validatorPro = null;

	private Resource exceptionResource = null;
	private Resource infoResource = null;
	private Resource validatorResource = null;

	/** 记录日志 */
	protected static Log log = LogFactory.getLog(MessageUtils.class);

	/**
	 * 容器启动时调用该方法，初始化读入资源文件
	 * 
	 * @throws IOException
	 */
	public synchronized void init() throws IOException {
		Assert.notNull(exceptionResource);
		Assert.notNull(infoResource);
		Assert.notNull(validatorResource);
		infoPro = PropertyUtils.readFromResource(infoResource);
		infoPro = newForNull(infoPro);
		exceptionPro = PropertyUtils.readFromResource(exceptionResource);
		exceptionPro = newForNull(exceptionPro);
		validatorPro = PropertyUtils.readFromResource(validatorResource);
		validatorPro = newForNull(validatorPro);
	}

	/**
	 * 通过key获取info.property文件中的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getInfoValue(String key) {
		return PropertyUtils.getProperty(infoPro, key);
	}

	/**
	 * 供异常调用，通过key获取exception.property文件中的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getExceptionValue(String key) {
		return PropertyUtils.getProperty(exceptionPro, key);
	}

	/**
	 * 获取验证信息调用，通过key获取exception.property文件中的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValidatorValue(String key) {
		return PropertyUtils.getProperty(validatorPro, key);
	}

	/**
	 * 如果未空，则new一个对象返回
	 * 
	 * @param properties
	 * @return
	 */
	private Properties newForNull(Properties properties) {
		if (null == properties) {
			properties = new Properties();
		}
		return properties;
	}

	public void setExceptionResource(Resource exceptionResource) {
		this.exceptionResource = exceptionResource;
	}

	public void setInfoResource(Resource infoResource) {
		this.infoResource = infoResource;
	}

	public void setValidatorResource(Resource validatorResource) {
		this.validatorResource = validatorResource;
	}

}
