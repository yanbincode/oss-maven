package common.exception;

/**
 * 需要事务回滚的checked异常
 * 
 * @author yanbin
 * 
 */
public class OssRollbackCheckedException extends AbstractCheckedException {

	private static final long serialVersionUID = -1990927546211646253L;

	/**
	 * 默认的提示信息的构造函数
	 */
	public OssRollbackCheckedException() {
		super();
	}

	/**
	 * 抛出提示信息
	 * 
	 * @param message
	 *            对应exception.property文件中的key
	 */
	public OssRollbackCheckedException(String messageKey) {
		super(messageKey);
	}

	/**
	 * 默认提示信息
	 * 
	 * @param errors
	 *            框架验证信息
	 */
	public OssRollbackCheckedException(OssErrors errors) {
		super(errors);
	}

	/**
	 * 抛出异常
	 * 
	 * @param message
	 *            提示信息
	 * @param errors
	 *            验证框架信息
	 */
	public OssRollbackCheckedException(String message, OssErrors errors) {
		super(message, errors);
	}

	/**
	 * 抛出异常导致的原因
	 * 
	 * @param cause
	 */
	public OssRollbackCheckedException(Throwable cause) {
		super(cause);
	}

}
