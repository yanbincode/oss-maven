package dao;

import java.util.List;

import model.PayInfo;

import common.support.BaseHibernateDao;

/**
 * 支出dao接口
 * 
 * @author yanbin
 * 
 */
public interface PayInfoDao<T> extends BaseHibernateDao<T> {

	/**
	 * 获取所有的支出信息
	 * 
	 * @return
	 */
	public List<PayInfo> selectAllPayInfos();

	/**
	 * 分页查找
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<PayInfo> selectPayInfos(Long startIndex, Long endIndex);

	/**
	 * 获取所有的条数
	 * 
	 * @return
	 */
	public Long selectCount();

}
