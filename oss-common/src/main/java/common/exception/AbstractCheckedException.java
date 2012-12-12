package common.exception;

import java.io.Serializable;

import common.utils.MessageUtils;

/**
 * 受控异常基类<br>
 * 将构造方法处于保护状态，且不能被本包以外的类得到
 * 
 * @author yanbin
 * 
 */
public class AbstractCheckedException extends Exception implements Serializable {

	private static final long serialVersionUID = -7520364959198229812L;
	/** 验证框架信息 */
	private OssErrors errors;

	/**
	 * 受保护的构造方法，抛出默认异常信息
	 */
	protected AbstractCheckedException() {
		// property中的值
		super(MessageUtils.getExceptionValue("default.exception.message"));
	}

	/**
	 * 抛出资源文件中的值，传入key
	 * 
	 * @param messageKey
	 *            exception的key
	 */
	protected AbstractCheckedException(String messageKey) {
		super(MessageUtils.getExceptionValue(messageKey));
	}

	/**
	 * 默认提示信息
	 * 
	 * @param errors
	 */
	protected AbstractCheckedException(OssErrors errors) {
		super(MessageUtils.getExceptionValue("default.exception.message"));
		this.errors = errors;
	}

	/**
	 * 指定提示
	 * 
	 * @param messageKey
	 * @param errors
	 */
	protected AbstractCheckedException(String messageKey, OssErrors errors) {
		super(MessageUtils.getExceptionValue(messageKey));
		this.errors = errors;
	}

	/**
	 * 获取框架验证信息
	 * 
	 * @return
	 */
	public OssErrors getErrors() {
		return errors;
	}

	/**
	 * 判断是否有验证信息
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		if (null != errors && errors.hasErrors()) {
			return true;
		}
		return false;
	}

	/**
	 * 抛出异常导致的原因
	 * 
	 * @param cause
	 */
	protected AbstractCheckedException(Throwable cause) {
		super(cause);
	}

}
