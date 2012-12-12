package dao;

import java.util.Date;
import java.util.List;

import model.YearRecord;

import common.support.BaseHibernateDao;

/**
 * 年报dao接口
 * 
 * @author yanbin
 * 
 * @param <T>
 */
public interface YearRecordDao<T> extends BaseHibernateDao<T> {

	/**
	 * 年统计
	 * 
	 * @param dateTime
	 */
	public void statisticsYear(Date dateTime);

	/**
	 * 更新统计
	 * 
	 * @param dateTime
	 */
	public void reStatisticsYear(Date dateTime);

	/**
	 * 获取指定条数的年统计记录
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<YearRecord> selectYearRecords(Long startIndex, Long endIndex);

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public Long selectCount();

	/**
	 * 根据年时间获取年统计信息
	 * 
	 * @param dateTime
	 * @return
	 */
	public YearRecord selectByYearTime(Date dateTime);

}
