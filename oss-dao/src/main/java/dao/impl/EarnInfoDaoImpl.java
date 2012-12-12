package dao.impl;

import java.util.List;

import model.EarnInfo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;

import dao.EarnInfoDao;

/**
 * 收入信息
 * 
 * @author yanbin
 * 
 */
@Repository
public class EarnInfoDaoImpl extends OssHibernateDaoSupport<EarnInfo> implements EarnInfoDao<EarnInfo> {

	@SuppressWarnings("unchecked")
	public List<EarnInfo> selectAllPayInfos() {
		String hql = "from EarnInfo";
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<EarnInfo> selectPayInfos(Long startIndex, Long endIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select tn.*, rownum as rn from (select * from earn_info ");
		sql.append("order by day_time desc) tn where rownum <= ?) where rn > ? ");
		Query query = getCurrentSession().createSQLQuery(sql.toString()).addEntity(EarnInfo.class).setLong(0, endIndex)
				.setLong(1, startIndex);
		return query.list();
	}

	public Long selectCount() {
		String sql = "select count(1) from earn_info";
		Object object = getCurrentSession().createSQLQuery(sql).uniqueResult();
		if (null != object) {
			return Long.parseLong(object.toString());
		}
		return 0l;
	}

}
