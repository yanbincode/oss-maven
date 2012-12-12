package common.exception;

import org.apache.commons.lang.StringUtils;

import common.utils.Assert;
import common.utils.MessageUtils;

/**
 * 验证异常信息类
 * 
 * @author yanbin
 * 
 */
public class FieldError {

	/** 错误信息的(字段)名称 */
	private final String field;
	/** 错误信息码，如果用户没有指定错误信息，或者错误信息不存在，则根据这个错误码获取默认错误信息 */
	private final String errorCode;
	/** 用户指定的错误信息提示 */
	private String rejectedValue;
	/** 默认错误信息，通过errCode读取资源文件获得 */
	private String defaultMessage;

	/**
	 * 构造新的错误实体
	 * 
	 * @param field
	 *            发生错误的字段
	 * @param rejectValue
	 *            注入自定义错误信息。
	 * @param errorCode
	 *            默认错误代码
	 */
	public FieldError(String field, String rejectValue, String errorCode) {
		Assert.notEmpty(field);
		Assert.notEmpty(errorCode);

		this.errorCode = errorCode;
		this.field = field;
		this.rejectedValue = rejectValue;
		this.defaultMessage = getMessage(errorCode);
	}

	/**
	 * 重写判断两个FieldError 是否相等
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		FieldError otherError = (FieldError) other;
		// 两个field相等
		return getField().equals(otherError.getField());
	}

	/**
	 * 根据错误类别，获取默认的错误信息
	 * 
	 * @param error
	 * @return
	 */
	private String getMessage(String error) {
		if (StringUtils.isEmpty(error)) {
			return "";
		}
		return MessageUtils.getValidatorValue(error);
	}

	public String getField() {
		return field;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
