package common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射获取类型的工具类
 * 
 * @author yanbin
 * 
 */
public class GenericsUtils {

	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz) {
		Type genricType = clazz.getGenericSuperclass();
		if (!(genricType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genricType).getActualTypeArguments();
		if (null == params || params.length == 0) {
			return Object.class;
		}
		if (!(params[0] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[0];
	}

}
