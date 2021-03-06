package dao.impl;

import java.util.List;

import model.OssUser;

import org.springframework.stereotype.Repository;

import common.support.OssHibernateDaoSupport;

import dao.OssUserDao;

@Repository
public class OssUserDaoImpl extends OssHibernateDaoSupport<OssUser> implements OssUserDao<OssUser> {

	@SuppressWarnings("unchecked")
	@Override
	public List<OssUser> selectAll() {
		String hql = "from OssUser";
		List<OssUser> ossUsers = getCurrentSession().createQuery(hql).list();
		return ossUsers;
	}

	@Override
	public OssUser selectByUserNameAndPassWord(String userName, String passWord) {
		String hql = "from OssUser u where u.userName = ? and u.passWord = ?";
		OssUser ossUser = (OssUser) getCurrentSession().createQuery(hql).setString(0, userName).setString(1, passWord)
				.uniqueResult();
		return ossUser;
	}

	@Override
	public OssUser selectByUserName(String userName) {
		String hql = "from OssUser u where u.userName = ?";
		OssUser ossUser = (OssUser) getCurrentSession().createQuery(hql).setString(0, userName).uniqueResult();
		return ossUser;
	}

}
