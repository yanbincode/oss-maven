package service;

import java.util.List;

import model.OssUser;

/**
 * 人员业务类
 * 
 * @author yanbin
 * 
 */
public interface OssUserService {

	/**
	 * 获取所有人员
	 * 
	 * @return
	 */
	public List<OssUser> getAllUsers();

	/**
	 * 根据ID获取对应记录
	 * 
	 * @param userId
	 * @return
	 */
	public OssUser get(Long userId);

	/**
	 * 添加成员
	 * 
	 * @param ossUser
	 */
	public void add(OssUser ossUser);

	/**
	 * 修改成员
	 * 
	 * @param ossUser
	 */
	public void modify(OssUser ossUser);

	/**
	 * 根据用户名和密码获取用户
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public OssUser getByUserNameAndPassWord(String userName, String passWord);

}
