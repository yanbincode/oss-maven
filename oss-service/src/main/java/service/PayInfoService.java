package service;

import java.util.List;

import model.PayInfo;

import common.exception.OssRollbackCheckedException;

/**
 * 支出信息service接口
 * 
 * @author yanbin
 * 
 */
public interface PayInfoService {

	/**
	 * 获取指出信息
	 * 
	 * @param recordId
	 * @return
	 */
	public PayInfo get(Long recordId);

	/**
	 * 获取所有的支出信息
	 * 
	 * @return
	 */
	public List<PayInfo> getAllPayInfos();

	/**
	 * 分页查找，显示列表
	 * 
	 * @return
	 */
	public List<PayInfo> getShowList(Long index);

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public Long getCount();

	/**
	 * 获取最后一页
	 * 
	 * @param count
	 * @return
	 */
	public Long getLastPage(Long count);

	/**
	 * 添加支出信息
	 * 
	 * @param payInfo
	 * @throws OssRollbackCheckedException
	 */
	public void add(PayInfo payInfo) throws OssRollbackCheckedException;

	/**
	 * 修改支出信息
	 * 
	 * @param payInfo
	 * @throws OssRollbackCheckedException
	 */
	public void modify(PayInfo payInfo) throws OssRollbackCheckedException;

	/**
	 * 删除支出信息
	 * 
	 * @param payInfo
	 * @throws OssRollbackCheckedException
	 */
	public void delete(PayInfo payInfo) throws OssRollbackCheckedException;

	/**
	 * 验证
	 * 
	 * @param payInfo
	 * @throws OssRollbackCheckedException
	 */
	public void validateNotNull(PayInfo payInfo) throws OssRollbackCheckedException;

}
