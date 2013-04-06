package security.manager;

import java.util.ArrayList;
import java.util.Collection;

import model.OssUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import service.OssUserService;

/**
 * 在这个类中，你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 * 
 * @author yanbin
 * 
 */
public class OssUserDetailsService implements UserDetailsService {

	@Autowired
	private OssUserService ossUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		OssUser ossUser = ossUserService.getByUserName(username);

		/**
		 * 将角色和用户关联起来，可以在数据库中配置用户角色关系，直接load进行
		 */
		Collection<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();

		SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_ADMIN");
		authoritys.add(authUser);

		User user = new User(ossUser.getUserName(), ossUser.getPassWord(), true, true, true, true, authoritys);
		return user;
	}

}
