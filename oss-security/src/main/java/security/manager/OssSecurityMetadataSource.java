package security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 加载资源与权限的对应关系
 * 
 * @author yanbin
 * 
 */
public class OssSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	/** 存放load的自定义资源权限规则 */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public OssSecurityMetadataSource() {
		loadResourceDefine();
	}

	/**
	 * load自定义的资源，即对访问的请求，url设定权限。<br>
	 * 这可以直接在数据库中配置资源角色表，直接load。原理是一样的
	 */
	private void loadResourceDefine() {
		System.out.println("========SimSecurityMetadataSource=============");

		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> attrRoleUsers = new ArrayList<ConfigAttribute>();
		ConfigAttribute attrRoleUser = new SecurityConfig("ROLE_USER");
		attrRoleUsers.add(attrRoleUser);
		resourceMap.put("/resource.do?method=add", attrRoleUsers);
		resourceMap.put("/resource.do?method=details", attrRoleUsers);

		Collection<ConfigAttribute> attrRoleAdmins = new ArrayList<ConfigAttribute>();
		ConfigAttribute attrRoleAdmin = new SecurityConfig("ROLE_ADMIN");
		attrRoleAdmins.add(attrRoleAdmin);
		resourceMap.put("/resource.do?method=modify", attrRoleAdmins);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (null == resourceMap || resourceMap.size() == 0) {
			return null;
		}
		return resourceMap.get(requestUrl);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
