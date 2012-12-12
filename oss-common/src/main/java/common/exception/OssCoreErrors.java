package common.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import common.utils.Assert;
import common.utils.StringUtils;

public class OssCoreErrors implements OssErrors {

	private String objectName = null;
	private Object target = null;
	private List<FieldError> errors = new ArrayList<FieldError>();

	public OssCoreErrors(String objectName, Object target) {
		this.objectName = objectName;
		this.target = target;
	}

	public List<FieldError> getAllErrors() {
		return Collections.unmodifiableList(errors);
	}

	public int getErrorCount() {
		return errors.size();
	}

	public FieldError getFieldError(String field) {
		Iterator<FieldError> iterator = errors.iterator();
		while (iterator.hasNext()) {
			FieldError error = iterator.next();
			if (isMathchingFieldError(field, error)) {
				return error;
			}
		}
		return null;
	}

	public List<FieldError> getFieldErrors(String field) {
		List<FieldError> fieldErrorList = new ArrayList<FieldError>();
		Iterator<FieldError> iterator = errors.iterator();
		while (iterator.hasNext()) {
			FieldError error = iterator.next();
			if (isMathchingFieldError(field, error)) {
				fieldErrorList.add(error);
			}
		}
		return fieldErrorList;
	}

	public void rejectValue(String field, String errorCode) {
		rejectValue(field, errorCode, null);
	}

	public void rejectValue(String field, String errorCode, String defaultMessage) {
		// Assert.notNull(field, "Field must not be null", false);
		// Assert.notNull(errorCode, "ErrorCode must not be null", false);
		Assert.notNull(field);
		Assert.notNull(errorCode);
		addError(field, errorCode, defaultMessage);
	}

	public void addErrors(OssErrors errors) {
		if (null == errors) {
			return;
		}
		if (errors.hasErrors()) {
			if (StringUtils.isNotEmpty(objectName) && StringUtils.isNotEmpty(errors.getObjectName())
					&& !objectName.equals(errors.getObjectName())) {
				throw new IllegalArgumentException("can not binding the attributes between different objects!");
			}
		}
		for (FieldError fieldError : errors.getAllErrors()) {
			this.errors.add(fieldError);
		}
	}

	public void addError(String field, String errorCode, String defaultMessage) {
		defaultMessage = StringUtils.isEmpty(defaultMessage) ? "" : defaultMessage;
		boolean hasExists = false;
		for (FieldError fieldError : getFieldErrors(field)) {
			if ((StringUtils.isEmpty(defaultMessage) && StringUtils.isEmpty(fieldError.getRejectedValue()))
					|| defaultMessage.equals(fieldError.getRejectedValue())) {
				hasExists = true;
			}
			break;
		}
		if (!hasExists) {
			FieldError error = new FieldError(field, defaultMessage, errorCode);
			errors.add(error);
		}
	}

	public String getErrorMessage(String field) {
		String message = "";
		List<FieldError> list = getFieldErrors(field);
		for (FieldError fieldError : list) {
			String errorMessage = fieldError.getRejectedValue();
			message += StringUtils.isEmpty(errorMessage) ? fieldError.getDefaultMessage() : errorMessage + ",";
		}
		return StringUtils.trimEndChar(message, ",");
	}

	public String getAllErrorMessage() {
		String message = "";
		List<FieldError> list = getAllErrors();
		for (FieldError fieldError : list) {
			String field = fieldError.getField();
			String errorMessage = fieldError.getRejectedValue();
			errorMessage = StringUtils.isEmpty(errorMessage) ? fieldError.getDefaultMessage() : errorMessage + ",";
			message += field + ":" + errorMessage + ";";
		}
		return StringUtils.trimEndChar(message, ";");
	}

	public String getObjectName() {
		return objectName;
	}

	public Object getTarget() {
		return target;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	/**
	 * 验证字段属性和字段错误信息是否匹配
	 * 
	 * @param field
	 * @param fieldError
	 * @return
	 */
	protected boolean isMathchingFieldError(String field, FieldError fieldError) {
		field = StringUtils.trim(field);
		return field.equals(fieldError.getField()) || (field.endsWith("*"))
				&& fieldError.getField().startsWith(field.substring(0, field.length() - 1));

	}

}
