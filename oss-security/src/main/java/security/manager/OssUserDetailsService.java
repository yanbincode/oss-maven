package security.manager;

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
		System.out.println("username is " + username);
		/**
		 * 将角色和用户关联起来，可以在数据库中配置用户角色关系，直接load进行
		 */
		Collection<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
		if (username.equals("yanbin")) {
			SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_USER");
			authoritys.add(authUser);
		}

		if (username.equals("admin")) {
			SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("ROLE_ADMIN");
			authoritys.add(authUser);
		}

		User user = new User(username, "123456", true, true, true, true, authoritys);
		return user;
	}

}
