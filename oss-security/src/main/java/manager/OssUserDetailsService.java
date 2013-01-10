package manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 在这个类中，你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 * 
 * @author yanbin
 * 
 */
public class OssUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("========SimUserDetailsService=============");

		System.out.println("username is " + username);
		/**
		 * 将角色和用户关联起来，可以在数据库中配置用户角色关系，直接load进行
		 */
		Collection<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
		// 获取登陆人名称，来获取当前登陆人的信息，然后根据角色判断，这个用户能拥有的资源
		if (username.equals("yanbin")) {
			// 登陆人为yanbin ，角色id为1
			SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_USER");
			authoritys.add(authUser);
		}

		// 如果登录人是admin，则是admin角色
		if (username.equals("admin")) {
			SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_ADMIN");
			authoritys.add(authUser);
		}

		// User(String username, String password, boolean enabled,
		// booleanaccountNonExpired,booleancredentialsNonExpired,
		// booleanaccountNonLocked, Collection<GrantedAuthority> authorities)
		User user = new User(username, "123456", true, true, true, true, authoritys);
		return user;
	}

}
