package service;

import java.util.Date;
import java.util.List;

import model.YearRecord;

import common.exception.OssRollbackCheckedException;

/**
 * 年报service 接口
 * 
 * @author yanbin
 * 
 */
public interface YearRecordService {

	/**
	 * 获取年报信息
	 * 
	 * @param record
	 * @return
	 */
	public YearRecord get(Long recordId);

	/**
	 * 分页获取年报信息
	 * 
	 * @param pageIndex
	 * @return
	 */
	public List<YearRecord> getShowList(Long pageIndex);

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
	 * 根据dateTime获取年统计信息
	 * 
	 * @param dateTime
	 * @return
	 */
	public YearRecord getByYearTime(Date dateTime);

	/**
	 * 添加统计年信息
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
	 * @param yearRecord
	 * @throws OssRollbackCheckedException
	 */
	public void modify(YearRecord yearRecord) throws OssRollbackCheckedException;

	/**
	 * 删除操作
	 * 
	 * @param yearRecord
	 * @throws OssRollbackCheckedException
	 */
	public void delete(YearRecord yearRecord) throws OssRollbackCheckedException;

	/**
	 * 校验实体非空
	 * 
	 * @param yearRecord
	 * @throws OssRollbackCheckedException
	 */
	public void validateNotNull(YearRecord yearRecord) throws OssRollbackCheckedException;

}
