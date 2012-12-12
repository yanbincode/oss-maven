package common.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.utils.StringUtils;

public class Validator {

	/** 对应验证方法 */

	/** 是否为空 */
	public static final String NOT_NULL = "isNull";

	public static final String NUMERIC = "isNumber";
	/** 是否整数 */
	public static final String INTEGER = "isInteger";
	/** 是否小数 */
	public static final String FLOAT = "isFloat";

	/** ===============================正则匹配================================= */
	/** 数字 */
	public static final Pattern _NUMERIC = Pattern.compile("(\\-)?[0-9]+(\\.[0-9]+)?");
	/** 整型 */
	public static final Pattern _INTEGER = Pattern.compile("(\\-)?[0-9]+");
	/** 非负数字 */
	public static final Pattern _NOT_NEGATIVE_NUMERIC = Pattern.compile("[0-9]+(\\.[0-9]+)");
	/** 非负整数 */
	public static final Pattern _NOT_NEGATIVE_INTEGER = Pattern.compile("[0-9]+");
	/** 网址 */
	public static final Pattern _WEBSITE = Pattern.compile(
			"(((https|http|rtsp|mms)://)|(ftp://[0-9a-z_!~*'().&=+$%-] + (:([0-9a-zA-Z]\\.?)+)?@))?" // ftp的user@
					+ "((([0-9]{1,3}\\.){3}[0-9]{1,3})"// IP形式的URL - 192.169.1.1
					+ "|" + "(([0-9a-z_!~*'()-]+\\.)+" // 域名 - www.
					+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
					+ "[a-z]{2,6}" // .com
					+ "(:[0-9]{1,4})?" // 端口号
					+ "((/?)|((/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?))", Pattern.CASE_INSENSITIVE);
	// TODO:
	/** 匹配EMAIL */
	public static final Pattern _EMAIL = Pattern.compile("\\w+(");
	/** 含有中文字符 */
	public static final Pattern _CHINESE = Pattern.compile("[\u4E00-\u9FA5]+");
	/** 中国邮政编码 */
	public static final Pattern _CHINESE_POST_CODE = Pattern.compile("[1-9][0-9]{4,5}");
	/** 完整的电话号码 */
	public static final Pattern _WHOLE_TEL = Pattern.compile("[0]?[0-9]?[1-9][0-9]");

	/**
	 * 判断是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (null == obj) {
			return false;
		}
		if (obj instanceof String) {
			return StringUtils.isNotEmpty((String) obj);
		}
		return true;
	}

	/**
	 * 判断是否为数字，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(Object obj) {
		return check(obj, _NUMERIC, true);
	}

	/**
	 * 判断是否为整型,当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isInteger(Object obj) {
		return check(obj, _INTEGER, true);
	}

	/**
	 * 验证是否为小数，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isFloat(Object obj) {
		return check(obj, _NUMERIC, true);
	}

	/**
	 * 验证是否为非负数，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNegativeNumber(Object obj) {
		return check(obj, _NOT_NEGATIVE_NUMERIC, true);
	}

	/**
	 * 验证是否为非负整数，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNegativeInteger(Object obj) {
		return check(obj, _NOT_NEGATIVE_INTEGER, true);
	}

	/**
	 * 验证是否为非负小数，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNegativeFloat(Object obj) {
		return check(obj, _NOT_NEGATIVE_NUMERIC, true);
	}

	/**
	 * 验证是否为合法的网址，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isWebsite(Object obj) {
		return check(obj, _WEBSITE, true);
	}

	/**
	 * 验证是否为中国邮政编码，当obj为null时，返回true
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isChinesePostCode(Object obj) {
		return check(obj, _CHINESE_POST_CODE, true);
	}

	/**
	 * 验证字符串是否包含汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		Matcher matcher = _CHINESE.matcher(str);
		return matcher.find();
	}

	/**
	 * 验证是否为传真，传真和完整电话是一样的
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isFax(Object obj) {
		return check(obj, _WHOLE_TEL, true);
	}

	/**
	 * 验证是否是Email
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmail(Object obj) {
		if (obj == null) {
			return true;
		}
		String email = String.valueOf(obj);
		return check(email, _EMAIL, true);
	}

	/**
	 * 统一处理数字，字符串的格式验证
	 * 
	 * @param obj
	 *            需要验证的字符串
	 * @param p
	 *            指定的正则匹配模式
	 * @param isAllowEmpty
	 *            返回处理结果
	 * @return
	 */
	private static boolean check(Object obj, Pattern p, boolean isAllowEmpty) {
		String str = "";
		if (obj == null) {
			return isAllowEmpty;
		}
		if ((obj instanceof Number) || (obj instanceof String)) {
			str = String.valueOf(obj);
		} else {
			return false;
		}
		if (StringUtils.isEmpty(str)) {
			return isAllowEmpty;
		}
		// 正则匹配
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}

}
