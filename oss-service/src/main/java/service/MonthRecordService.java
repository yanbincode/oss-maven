package service;

import java.util.Date;
import java.util.List;

import model.MonthRecord;

import common.exception.OssRollbackCheckedException;

/**
 * 月报接口类
 * 
 * @author yanbin
 * 
 */
public interface MonthRecordService {

	/**
	 * 分页获取月报信息
	 * 
	 * @param pageIndex
	 * @return
	 */
	public List<MonthRecord> getShowList(Long pageIndex);

	/**
	 * 获取总数
	 * 
	 * @return
	 */
	public Long getCount();

	/**
	 * 获取最后一页页码
	 * 
	 * @param count
	 * @return
	 */
	public Long getLastPage(Long count);

	/**
	 * 获取月报
	 * 
	 * @param recordId
	 * @return
	 */
	public MonthRecord get(Long recordId);

	/**
	 * 根据dateTime获取 月统计信息
	 * 
	 * @param dateTime
	 * @return
	 */
	public MonthRecord getByMonthTime(Date dateTime);

	/**
	 * 添加月统计
	 * 
	 * @param dateTime
	 * @throws OssRollbackCheckedException
	 */
	public void add(Date dateTime) throws OssRollbackCheckedException;

	/**
	 * 重新统计
	 * 
	 * @param dateTime
	 * @throws OssRollbackCheckedException
	 */
	public void reStatistics(Date dateTime) throws OssRollbackCheckedException;

	/**
	 * 修改操作
	 * 
	 * @param monthRecord
	 * @throws OssRollbackCheckedException
	 */
	public void modify(MonthRecord monthRecord) throws OssRollbackCheckedException;

	/**
	 * 刪除操作
	 * 
	 * @param monthRecord
	 * @throws OssRollbackCheckedException
	 */
	public void delete(MonthRecord monthRecord) throws OssRollbackCheckedException;

	/**
	 * 实体校验
	 * 
	 * @param monthRecord
	 */
	public void validateNotNull(MonthRecord monthRecord) throws OssRollbackCheckedException;

}
