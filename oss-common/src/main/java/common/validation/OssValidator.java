package common.validation;

import java.lang.reflect.Method;

import common.exception.OssCoreErrors;
import common.exception.OssErrors;
import common.utils.Assert;
import common.utils.StringUtils;

public class OssValidator extends Validator {

	/**
	 * 进行验证：多个字段对应多种验证方式(验证按顺序进行)
	 * 
	 * @param entity
	 * @param members
	 * @param validateTypes
	 * @return
	 */
	public static OssErrors validate(Object entity, String fields, String[] validateTypes) {
		Assert.notEmpty(validateTypes, "validateType can not be empty");
		Assert.notEmpty(fields, "members can not be empty");
		Assert.notNull(entity, "entity can not be null");
		if (fields.length() != validateTypes.length) {
			throw new IllegalArgumentException("error: fields and validateTypes is not same size !");
		}
		OssErrors ossErrors = new OssCoreErrors(entity.getClass().getName(), entity);
		String[] preFields = fields.split(",");
		for (int i = 0; i < preFields.length; i++) {
			addOssError(ossErrors, entity, preFields[i], validateTypes[i]);
		}
		return ossErrors;
	}

	/**
	 * 进行验证：一个或者多个字段对应一种验证方式
	 * 
	 * @param entity
	 * @param fields
	 * @param validateType
	 * @return
	 */
	public static OssErrors validate(Object entity, String fields, String validateType) {
		Assert.notEmpty(validateType, "validateType can not be empty");
		Assert.notEmpty(fields, "members can not be empty");
		Assert.notNull(entity, "entity can not be null");

		OssErrors ossErrors = new OssCoreErrors(entity.getClass().getName(), entity);
		String[] preFields = fields.split(",");

		for (int i = 0; i < preFields.length; i++) {
			addOssError(ossErrors, entity, preFields[i], validateType);
		}
		return ossErrors;
	}

	/**
	 * 进行单个验证，将验证结果添加进Errors对象中
	 * 
	 * @param ossErrors
	 * @param entity
	 * @param field
	 * @param validateType
	 */
	protected static void addOssError(OssErrors ossErrors, Object entity, String field, String validateType) {
		// 组装字段的get方法
		String fieldMethod = "get".concat(field.substring(0, 1).toUpperCase().concat(field.substring(1)));
		Object obj = null;
		String errorMessage = "";
		try {
			// 通过反射获取实体对应的get方法，获取字段的值
			obj = entity.getClass().getDeclaredMethod(fieldMethod).invoke(entity);
		} catch (Exception e) {
			throw new IllegalArgumentException("error：" + e.getMessage() + "The Method : " + fieldMethod
					+ "is not exists");
		}
		if (excuteValidate(validateType, obj)) {
			errorMessage = "DEFAULT_ERROR_" + validateType;
		}
		if (StringUtils.isNotEmpty(errorMessage)) {
			ossErrors.rejectValue(field, OssErrors.ERROR_CODE_INVALID, errorMessage);
		}
	}

	/**
	 * 利用反射调用实际的验证
	 * 
	 * @param methodName
	 * @param obj
	 * @return
	 */
	protected static Boolean excuteValidate(String methodName, Object obj) {
		Object result = false;
		try {
			Method method = Validator.class.getDeclaredMethod(methodName);
			result = method.invoke(obj);
		} catch (Exception e) {
			throw new IllegalArgumentException("error：" + e.getMessage() + "The Method : " + methodName
					+ "is not exists");
		}
		return (Boolean) result;
	}

}
