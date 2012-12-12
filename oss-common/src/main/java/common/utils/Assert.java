package common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 断言工具，用于对一些对象是否为空作判断
 * 
 * @author yanbin
 * 
 */
public class Assert {

	/**
	 * 不能为空
	 * 
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 重载如果没有传 message,则默认给出message
	 * 
	 * @param object
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - argument must not be null");
	}

	/**
	 * 判断类不能为空的许多方法
	 * 
	 * @param object
	 * @param message
	 */
	@SuppressWarnings({ "rawtypes" })
	public static void notEmpty(Object object, String message) {
		// 如果是对象
		if (null == object) {
			throw new IllegalArgumentException(message);
		}
		// 如果是数组
		if (object.getClass().isArray()) {
			if (Array.getLength(object) == 0) {
				throw new IllegalArgumentException(message);
			}
		}
		// 如果是集合
		if (object instanceof Collection) {
			Collection collenction = (Collection) object;
			if (collenction.size() == 0) {
				throw new IllegalArgumentException(message);
			}
		}

		// 如果是Map
		if (object instanceof Map) {
			Map map = (Map) object;
			if (0 == map.size()) {
				throw new IllegalArgumentException(message);
			}
		}

		// 如果是字符串
		if (object instanceof String) {
			String string = (String) object;
			if (string.length() == 0) {
				throw new IllegalArgumentException(message);
			}
		}

		// 最后判断只要对象的toString()方法等于0
		if (object.toString().length() == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Object object) {
		notEmpty(object, "[Assertion failed] - argument must not be null");
	}

}
