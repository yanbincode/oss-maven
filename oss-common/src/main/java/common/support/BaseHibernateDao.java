package common.support;

import java.io.Serializable;

/**
 * Hibernate基本的支持方法，供每一个dao接口继承
 * 
 * @author yanbin
 * 
 * @param <T>
 */
public interface BaseHibernateDao<T> {

	/**
	 * 添加
	 * 
	 * @param t
	 */
	public void insert(T t);

	/**
	 * 修改
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(T t);

	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 */
	public T select(Serializable id);

	/**
	 * 添加或修改
	 * 
	 * @param t
	 */
	public void insertOrUpdate(T t);

}
