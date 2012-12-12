package common.utils;

/**
 * 字符串工具类<br>
 * 继承自org.apache.commons.lang.StringUtils
 * 
 * @author yanbin
 * 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 取出指定字符串的最后一个指定的字符。
	 * 
	 * @param str
	 * @param chr
	 * @return 如果为空，则返回空值
	 */
	public static String trimEndChar(String str, String chr) {
		if (isEmpty(str)) {
			return str;
		}
		if (str.endsWith(chr) && (str.lastIndexOf(chr) != -1)) {
			str = str.substring(0, str.lastIndexOf(chr));
		}
		return str;
	}

}
