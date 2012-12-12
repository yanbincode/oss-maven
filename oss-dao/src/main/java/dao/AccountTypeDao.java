package dao;

import java.util.List;

import model.AccountType;

import common.support.BaseHibernateDao;

public interface AccountTypeDao<T> extends BaseHibernateDao<T> {

	/**
	 * 获取指定条数的记录
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<AccountType> selectPayInfos(Long startIndex, Long endIndex);

	/**
	 * 获取所有记录数
	 * 
	 * @return
	 */
	public Long selectCount();

	/**
	 * 获取所有类型，根据指定的类型
	 * 
	 * @return
	 */
	public List<AccountType> selectAccountTypesByUse(String usePlace);

}
