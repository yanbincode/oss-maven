package dao;

import java.util.Date;
import java.util.List;

import model.MonthRecord;

import common.support.BaseHibernateDao;

/**
 * 月报dao接口
 * 
 * @author yanbin
 * 
 */
public interface MonthRecordDao<T> extends BaseHibernateDao<T> {

	/**
	 * 根据月时间获取月统计信息
	 * 
	 * @param dateTime
	 * @return
	 */
	public MonthRecord selectByMonthTime(Date dateTime);

	/**
	 * 月统计
	 * 
	 * @param dateTime
	 */
	public void statisticsMonth(Date dateTime);

	/**
	 * 更新统计
	 * 
	 * @param dateTime
	 */
	public void reStatisticsMonth(Date dateTime);

	/**
	 * 获取指定条数的月统计记录
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<MonthRecord> selectMonthRecords(Long startIndex, Long endIndex);

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public Long selectCount();

}
