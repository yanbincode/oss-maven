package common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 复制所有的属性
	 * 
	 * @param target
	 * @param source
	 */
	public static void copyAllProperties(Object target, Object source) {
		try {
			copyProperties(target, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将原对象指定需要复制的字段到目标对象中
	 * 
	 * @param target
	 *            目标对象
	 * @param source
	 *            源对象
	 * @param specialProperties
	 */
	public static void copySpecialProperties(Object target, Object source, String specialProperties) {
		String[] properties = specialProperties.split(",");
		for (String property : properties) {
			// 去除空格的影响
			property = StringUtils.trim(property);
			try {
				copyProperties(target, source, property);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将原对象指定需要复制的字段到目标对象中。排除不复制的字段
	 * 
	 * @param target
	 *            目标对象
	 * @param source
	 *            源对象
	 * @param exceptProperties
	 */
	public static void copyExceptProperties(Object target, Object source, String exceptProperties) {
		String[] properties = getNeedProperties(source, exceptProperties.split(",")).split(",");

		for (String property : properties) {
			try {
				// 去除空格的影响
				property = StringUtils.trim(property);
				copyProperties(target, source, property);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重载父类方法
	 * 
	 * @param target
	 * @param source
	 * @param property
	 */
	protected static void copyProperties(Object dest, Object orig, String property) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtilsBean bean = BeanUtilsBean.getInstance();
		if (orig instanceof DynaBean) {
			if (bean.getPropertyUtils().isReadable(orig, property)
					&& bean.getPropertyUtils().isWriteable(dest, property)) {
				Object value = ((DynaBean) orig).get(property);
				bean.copyProperty(dest, property, value);
			}
		} else {
			if ("class".equals(property)) {
				return;
			}
			if (bean.getPropertyUtils().isReadable(orig, property)
					&& bean.getPropertyUtils().isWriteable(dest, property)) {

				try {
					Object value = bean.getPropertyUtils().getSimpleProperty(orig, property);
					bean.copyProperty(dest, property, value);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从所有的属性中排除 指定的属性
	 * 
	 * @param entity
	 *            实体
	 * @param exceptProperties
	 *            需要排除的属性
	 * @return
	 */
	private static String getNeedProperties(Object entity, String[] exceptProperties) {
		String needPSroperties = "";
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			boolean isNeedExcept = false;
			for (String exceptProperty : exceptProperties) {
				// 去除空格的影响
				exceptProperty = StringUtils.trim(exceptProperty);
				if (field.getName().equals(exceptProperty)) {
					isNeedExcept = true;
					break;
				}
			}
			if (!isNeedExcept && !field.getName().equals("serialVersionUID")) {
				needPSroperties += field.getName() + ",";
			}
		}
		return StringUtils.trimEndChar(needPSroperties, ",");
	}

}
