package common.validation;

import java.util.ResourceBundle;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.springframework.util.Assert;

import common.exception.OssCoreErrors;
import common.exception.OssErrors;

/**
 * 进行对注解的实体验证
 * 
 * @author yanbin
 * 
 */
public class EntityAnnotationValidator {

	private static final String VALIDATE_MESSAGE = "properties.hibernate_validator";

	/**
	 * 实体验证的方法
	 * 
	 * @param entity
	 * @return
	 */
	public static OssErrors validate(Object entity) {
		OssErrors errors = null;
		if (null != entity) {
			InvalidValue[] invalidValues = getInvalidValues(entity);
			errors = new OssCoreErrors(entity.getClass().getName(), entity);
			addErrors(invalidValues, errors);
		} else {
			errors = new OssCoreErrors(null, null);
		}
		return errors;
	}

	/**
	 * 将InvalidValue 转换到 OssError
	 * 
	 * @param invalidValues
	 * @param errors
	 */
	protected static void addErrors(InvalidValue[] invalidValues, OssErrors errors) {
		Assert.notNull(errors);
		Assert.notNull(invalidValues);
		for (InvalidValue invalidValue : invalidValues) {
			errors.rejectValue(invalidValue.getPropertyPath(), OssErrors.ERROR_CODE_INVALID, invalidValue.getMessage());
		}
	}

	/**
	 * 获取验证信息
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static InvalidValue[] getInvalidValues(Object entity) {
		InvalidValue[] invalidValues = null;
		try {
			// 指定资源绑定
			ResourceBundle resourceBundle = ResourceBundle.getBundle(VALIDATE_MESSAGE);
			// 指定需要验证的类和绑定器
			ClassValidator classValidator = new ClassValidator(entity.getClass(), resourceBundle);
			// 获取验证结果集
			invalidValues = classValidator.getInvalidValues(entity);
		} catch (Exception e) {
			throw new IllegalArgumentException("properties file is not exists");
		}
		return invalidValues;
	}

}
