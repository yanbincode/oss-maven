package dao.impl;

import java.util.List;

import model.PayInfo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;

import dao.PayInfoDao;

/**
 * 支出dao实现类
 * 
 * @author yanbin
 * 
 */
@Repository
public class PayInfoDaoImpl extends OssHibernateDaoSupport<PayInfo> implements PayInfoDao<PayInfo> {

	@SuppressWarnings("unchecked")
	public List<PayInfo> selectAllPayInfos() {
		String hql = "from PayInfo";
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<PayInfo> selectPayInfos(Long startIndex, Long endIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select tn.*, rownum as rn from (select * from pay_info ");
		sql.append("order by day_time desc) tn where rownum <= ?) where rn > ? ");
		Query query = getCurrentSession().createSQLQuery(sql.toString()).addEntity(PayInfo.class).setLong(0, endIndex)
				.setLong(1, startIndex);
		return query.list();
	}

	public Long selectCount() {
		String sql = "select count(1) from pay_info";
		Object object = getCurrentSession().createSQLQuery(sql).uniqueResult();
		if (null != object) {
			return Long.parseLong(object.toString());
		}
		return 0l;
	}

}
