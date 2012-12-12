package common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 
 * @author yanbin
 * 
 */
public class RegexUtils {

	/** 用于缓存模板 */
	private static Map<String, Pattern> patterns = new HashMap<String, Pattern>();

	/**
	 * 根据正则表达式，验证目标字符是否匹配<br>
	 * 为了提高性能，将正则表达式编译后形成的模板缓存<br>
	 * 如果参数为空，则返回false
	 * 
	 * @param regex
	 * @param target
	 * @return
	 */
	public static boolean patternMathch(String regex, String target) {
		if (StringUtils.isEmpty(regex) || StringUtils.isEmpty(target)) {
			return false;
		}
		// 先从缓存中取正则匹配器
		Pattern pattern = patterns.get(regex);
		if (null == pattern) {
			// 这个效率也高
			pattern = Pattern.compile(regex);
			patterns.put(regex, pattern);
		}
		// 进行正则匹配
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}

	/**
	 * 判断字符串是否都是字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchEnLetter(String input) {
		return patternMathch(input, "[a-zA-Z]*");
	}

	/**
	 * 判断匹配中文字
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchCnLetter(String input) {
		return patternMathch(input, "[\u4e00-\u9fa5]*");
	}

	/**
	 * 匹配单词字符：包括26个英文字母大小写，数字，下划线
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchWordChar(String input) {
		// 正则表达式中\w 等价于： [a-zA-Z_0-9]
		return patternMathch(input, "\\w*");
	}

	/**
	 * 判断字符串是否都是数字
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchNumber(String input) {
		return patternMathch(input, "[0-9]*");
	}

	/**
	 * 判断字符串是否为金额形式，小数点精确到最多两位
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchMoney(String input) {
		return patternMathch(input, "[0-9]*.[0-9]{1,2}");
	}

	/**
	 * 匹配身份证：身份证一般为15或18位的数字，最后patternMathch能为X<br>
	 * 验证比较简单
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchIdCard(String input) {
		// 正则表达式中\d 等价于[0-9]
		return patternMathch(input, "\\d{15}||\\d{18}||\\d{17}X");
	}

	/**
	 * 匹配手机：手机都用11位数字组成
	 * 
	 * @param input
	 * @return
	 */
	public static boolean matchPhoneNum(String input) {
		return patternMathch(input, "\\d{11}");
	}

}
