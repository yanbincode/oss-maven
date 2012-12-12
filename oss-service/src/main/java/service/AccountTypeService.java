package service;

import java.util.List;

import model.AccountType;

import common.exception.OssRollbackCheckedException;

public interface AccountTypeService {

	/**
	 * 获取单个实体
	 * 
	 * @param record
	 * @return
	 */
	public AccountType get(Long recordId);

	/**
	 * 根据类型获取所有的统计类型
	 * 
	 * @return
	 */
	public List<AccountType> getAccountTypesByUse(String usePlace);

	/**
	 * 获取指定页的列表
	 * 
	 * @param pageIndex
	 * @return
	 */
	public List<AccountType> getShowList(Long pageIndex);

	/**
	 * 获取记录总数
	 * 
	 * @param pageIndex
	 * @return
	 */
	public Long getCount(Long pageIndex);

	/**
	 * 用于计算最后一页
	 * 
	 * @param count
	 * @return
	 */
	public Long getLastPage(Long count);

	/**
	 * 非空验证
	 * 
	 * @param accountType
	 * @throws OssRollbackCheckedException
	 */
	public void validateNotNull(AccountType accountType) throws OssRollbackCheckedException;

	/**
	 * 添加操作
	 * 
	 * @param accountType
	 * @throws OssRollbackCheckedException
	 */
	public void add(AccountType accountType) throws OssRollbackCheckedException;

	/**
	 * 修改操作
	 * 
	 * @param accountType
	 * @throws OssRollbackCheckedException
	 */
	public void modify(AccountType accountType) throws OssRollbackCheckedException;

	/**
	 * 删除操作
	 * 
	 * @param accountType
	 * @throws OssRollbackCheckedException
	 */
	public void delete(AccountType accountType) throws OssRollbackCheckedException;

}
