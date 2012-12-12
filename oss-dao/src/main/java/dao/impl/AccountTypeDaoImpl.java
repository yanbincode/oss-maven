package dao.impl;

import java.util.List;

import model.AccountType;

import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;

import dao.AccountTypeDao;

@Repository
public class AccountTypeDaoImpl extends OssHibernateDaoSupport<AccountType> implements AccountTypeDao<AccountType> {

	@SuppressWarnings("unchecked")
	public List<AccountType> selectPayInfos(Long startIndex, Long endIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT RS.*, ROWNUM RN FROM (SELECT * FROM ACCOUNT_TYPE ");
		sql.append("ORDER BY USE_PLACE, TYPE_ID ASC) RS WHERE ROWNUM <= ?) WHERE RN > ? ");
		List<AccountType> accountTypes = getCurrentSession().createSQLQuery(sql.toString())
				.addEntity(AccountType.class).setLong(0, endIndex).setLong(1, startIndex).list();
		return accountTypes;
	}

	public Long selectCount() {
		String sql = "select count(1) from account_type";
		Object object = getCurrentSession().createSQLQuery(sql).uniqueResult();
		if (null != object) {
			return Long.parseLong(object.toString());
		}
		return 0l;
	}

	@SuppressWarnings("unchecked")
	public List<AccountType> selectAccountTypesByUse(String usePlace) {
		String hql = "from AccountType where usePlace = ?";
		List<AccountType> accountTypes = getCurrentSession().createQuery(hql).setString(0, usePlace).list();
		return accountTypes;
	}

}
