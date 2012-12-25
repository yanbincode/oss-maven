package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author yanbin
 * 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	/** oracle 类型的24小时制时分秒 */
	public static final String ORACLE_DATE_TIME_24_PATTERN = "yyyy-mm-dd hh24:mi:ss";
	/** 格式完整的日期时分秒 */
	public static final String DATE_TIME_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式到天 */
	public static final String DATE_TIME_DAY = "yyyy-MM-dd";

	/**
	 * 将日期格式转换成String格式
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param pattern
	 *            转换的时间格式
	 * @param allowException
	 *            转为时为空，是否抛出异常。如果为false，则返回空字符串
	 * @return
	 */
	public static String parseDate2String(Date date, String pattern, boolean allowException) {
		if (null == date) {
			if (allowException) {
				throw new IllegalArgumentException("the date must not be null!");
			} else {
				return "";
			}
		}
		SimpleDateFormat dateFormart = new SimpleDateFormat(pattern);
		return dateFormart.format(date);
	}

	/**
	 * 将Date转换成指定格式的string
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param pattern
	 *            转换成的时间格式
	 * @return
	 */
	public static String parseDate2String(Date date, String pattern) {
		return parseDate2String(date, pattern, false);
	}

	/**
	 * 将一个Date格式的string 转换成 指定格式的string
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseStrDate2String(String date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date formatDate = null;
		try {
			formatDate = dateFormat.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("the date：" + date + "could not be parsed.", e);
		}
		return dateFormat.format(formatDate);
	}

	/**
	 * 计算两个日期之间相差的时间（毫秒）
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 返回两个时间相差的毫秒数
	 */
	public static long getInterval(Date startDate, Date endDate) {
		Assert.notNull(startDate);
		Assert.notNull(endDate);
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long interval = endTime - startTime;
		return interval;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 返回时间天数差
	 */
	public static long getIntervalDay(Date startDate, Date endDate) {
		Date startTime = truncate(startDate, Calendar.DATE);
		Date endTime = truncate(endDate, Calendar.DATE);
		long intervalTime = endTime.getTime() - startTime.getTime();
		return intervalTime / 1000 / 60 / 60 / 24;
	}

}
