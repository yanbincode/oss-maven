package common.validation;

import org.springframework.validation.BindingResult;

import common.exception.FieldError;
import common.exception.OssErrors;
import common.utils.StringUtils;

/**
 * 用于Spring MVC中的controller验证，将验证信息用spring的errors返回
 * 
 * @author yanbin
 * 
 */
public class ValidatorUtils {

	/**
	 * 进行实体字段验证，对一个或多个字段进行单种方式的验证
	 * 
	 * @param entity
	 * @param members
	 * @param validateType
	 * @param result
	 * @return
	 */
	public static BindingResult validate(Object entity, String fields, String validateType, BindingResult result) {
		return validate(entity, fields, validateType, null, result);
	}

	/**
	 * 进行实体字段验证，多个字段对应多种方式的验证
	 * 
	 * @param entity
	 * @param fields
	 * @param validateTypes
	 * @param result
	 * @return
	 */
	public static BindingResult validate(Object entity, String fields, String[] validateTypes, BindingResult result) {
		return validate(entity, fields, null, validateTypes, result);
	}

	/**
	 * 抽象核心验证方法
	 * 
	 * @param entity
	 *            验证的实体
	 * @param fields
	 *            需要验证的字段
	 * @param validateType
	 *            单个验证方式
	 * @param validateTypes
	 *            多个验证方式
	 * @param result
	 *            结果
	 * @return
	 */
	private static BindingResult validate(Object entity, String fields, String validateType, String[] validateTypes,
			BindingResult result) {
		OssErrors ossErrors = null;
		if (null != validateTypes) {
			ossErrors = OssValidator.validate(entity, fields, validateTypes);
		}
		ossErrors = OssValidator.validate(entity, fields, validateType);
		convert2SpringError(ossErrors, result);
		return result;
	}

	/**
	 * 将ossErrors 添加到spring的errors中
	 * 
	 * @param ossErrors
	 * @param result
	 * @return
	 */
	public static BindingResult convert2SpringError(OssErrors ossErrors, BindingResult result) {
		if (ossErrors.hasErrors()) {
			for (FieldError error : ossErrors.getAllErrors()) {
				result.rejectValue(
						error.getField(),
						error.getErrorCode(),
						StringUtils.isEmpty(error.getRejectedValue()) ? error.getDefaultMessage() : error
								.getRejectedValue());
			}
		}
		return result;
	}

}
