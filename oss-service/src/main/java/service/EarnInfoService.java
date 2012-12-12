package service;

import java.util.List;

import model.EarnInfo;

import common.exception.OssRollbackCheckedException;

/**
 * 收入信息service接口
 * 
 * @author yanbin
 * 
 */
public interface EarnInfoService {

	/**
	 * 分页获取收入信息
	 * 
	 * @param pageIndex
	 * @return
	 */
	public List<EarnInfo> getShowList(Long pageIndex);

	/**
	 * 获取总数
	 * 
	 * @return
	 */
	public Long getCount();

	/**
	 * 获取最后一页的页数
	 * 
	 * @param count
	 * @return
	 */
	public Long getLastPage(Long count);

	/**
	 * 获取收入信息
	 * 
	 * @param recordId
	 * @return
	 */
	public EarnInfo get(Long recordId);

	/**
	 * 添加收入信息
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	public void add(EarnInfo earnInfo) throws OssRollbackCheckedException;

	/**
	 * 修改收入信息
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	public void modify(EarnInfo earnInfo) throws OssRollbackCheckedException;

	/**
	 * 删除收入信息
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	public void delete(EarnInfo earnInfo) throws OssRollbackCheckedException;

	/**
	 * 验证非空
	 * 
	 * @param earnInfo
	 * @throws OssRollbackCheckedException
	 */
	public void validateNotNull(EarnInfo earnInfo) throws OssRollbackCheckedException;

}
