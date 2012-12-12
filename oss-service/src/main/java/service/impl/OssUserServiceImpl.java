package service.impl;

import java.util.List;

import model.OssUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.OssUserService;

import common.support.AbstractServiceSupport;

import dao.OssUserDao;

/**
 * 人员业务实现类
 * 
 * @author yanbin
 * 
 */
@Transactional
@Service
public class OssUserServiceImpl extends AbstractServiceSupport implements OssUserService {

	@Autowired
	private OssUserDao<OssUser> ossUserDao;

	public List<OssUser> getAllUsers() {
		return ossUserDao.selectAll();
	}

	@Transactional(readOnly = true)
	public OssUser get(Long userId) {
		if (null == userId) {
			// throw new
			// OssRollbackCheckedException(MessageUtils.getExceptionValue("ID_NOT_EXISTS"));
		}
		OssUser ossUser = ossUserDao.select(userId);
		if (null == ossUser) {
			// throw
		}
		ossUser.setPhone(110l);
		return ossUser;
	}

	public void add(OssUser ossUser) {
		ossUser = ossUserDao.select(1L);
	}

	public void modify(OssUser ossUser) {

	}

	@Transactional(readOnly = true)
	public OssUser getByUserNameAndPassWord(String userName, String passWord) {
		return ossUserDao.selectByUserNameAndPassWord(userName, passWord);
	}

}
