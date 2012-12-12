package common.support;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import common.utils.GenericsUtils;

@SuppressWarnings("unchecked")
public class OssHibernateDaoSupport<T> {

	@Autowired
	@Qualifier("ossSessionFactory")
	private SessionFactory sessionFactory;

	/** Dao所管理的实体 */
	private Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass());

	/**
	 * 获取Hibernate模板
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate() {
		if (null == sessionFactory) {
			return null;
		}
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	/**
	 * 获取Hibernate session
	 * 
	 * @return
	 */
	public Session getCurrentSession() {
		if (null == sessionFactory) {
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	/**
	 * 获取sessionFactory
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 添加
	 * 
	 * @param t
	 */
	public void insert(T t) {
		getCurrentSession().save(t);
	}

	/**
	 * 修改
	 * 
	 * @param t
	 */
	public void update(T t) {
		getCurrentSession().merge(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 */
	public T select(Serializable id) {
		return (T) getCurrentSession().get(entityClass, id);
	}

	/**
	 * 添加或修改
	 * 
	 * @param t
	 */
	public void insertOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

}
