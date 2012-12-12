package common.type;

/**
 * 逻辑字符串的枚举类
 * 
 * @author yanbin
 * 
 */
public enum LogicType {

	/** 逻辑是：1 */
	LOGIC_YES("1"),
	/** 逻辑否：0 */
	LOGIC_NO("0");

	private String logicType;

	private LogicType(String logicType) {
		this.logicType = logicType;
	}

	@Override
	public String toString() {
		return logicType;
	}

}
