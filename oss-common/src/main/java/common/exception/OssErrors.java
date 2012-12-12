package common.exception;

import java.util.List;

/**
 * 异常接口
 * 
 * @author yanbin
 * 
 */
public interface OssErrors {

	/** 错误码：必填 */
	public static final String ERROR_CODE_REQUIRED = "required";
	/** 错误码：不合法 */
	public static final String ERROR_CODE_INVALID = "invalid";
	/** 错误码：重复 */
	public static final String ERROR_CODE_DUPLICATE = "duplicate";

	/**
	 * 获取绑定对象的名称
	 * 
	 * @return 绑定对象名称
	 */
	public String getObjectName();

	/**
	 * 获取绑定的对象
	 * 
	 * @return 绑定的对象
	 */
	public Object getTarget();

	/**
	 * 在ossError中注入一个错误（required，invalid，duplicate）
	 * 错误的信息根据错误的类别有默认的信息，如果有相同的字段的错误信息，则不再注入，同时也不会有任何提示信息<br>
	 * 
	 * 
	 * @param field
	 * @param errorCode
	 * @param defaultMessage
	 */
	public void rejectValue(String field, String errorCode);

	/**
	 * 重构方法<br>
	 * 在ossError中注入一个错误（required，invalid，duplicate）
	 * 错误的信息根据错误的类别有默认的信息，如果有相同的字段的错误信息，则不再注入，同时也不会有任何提示信息<br>
	 * 可以自定义错误信息
	 * 
	 * @param field
	 * @param errorCode
	 * @param defaultMessage
	 */
	public void rejectValue(String field, String errorCode, String defaultMessage);

	/**
	 * 返回是否有错误信息
	 * 
	 * @return
	 */
	public boolean hasErrors();

	/**
	 * 返回有多少个错误信息
	 * 
	 * @return
	 */
	public int getErrorCount();

	/**
	 * 返回所有的错误信息(FieldError)
	 * 
	 * @return
	 */
	public List<FieldError> getAllErrors();

	/**
	 * 返回指定的错误信息（如果field字段有多个错误信息，则返回一个）
	 * 
	 * @param field
	 * @return
	 */
	public FieldError getFieldError(String field);

	/**
	 * 返回指定的字段的所有错误信息
	 * 
	 * @param field
	 * @return
	 */
	public List<FieldError> getFieldErrors(String field);

	/**
	 * 添加新的错误信息。添加不同类型的异常会出错。如果相同的异常会存在两份
	 * 
	 * @param errors
	 */
	public void addErrors(OssErrors errors);

	/**
	 * 获取指定字段错误的提示信息
	 * 
	 * @param field
	 * @return
	 */
	public String getErrorMessage(String field);

	/**
	 * 获取所有的验证信息，各错误信息之间会以中文分号隔开
	 * 
	 * @return
	 */
	public String getAllErrorMessage();

}
