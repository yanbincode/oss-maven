package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OSS_ROLE")
public class OssRole implements Serializable {

	private static final long serialVersionUID = 5181428366894632432L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_OSS_ROLE")
	@SequenceGenerator(name = "S_OSS_ROLE", allocationSize = 1, sequenceName = "S_OSS_ROLE")
	private Long roleId;
	private String roleName;
	private String status;
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "OSS_USER_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private Set<OssUser> ossUsers;
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "OSS_ROLE_RESOURCE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	private Set<OssResource> ossResource;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<OssUser> getOssUsers() {
		return ossUsers;
	}

	public void setOssUsers(Set<OssUser> ossUsers) {
		this.ossUsers = ossUsers;
	}

	public Set<OssResource> getOssResource() {
		return ossResource;
	}

	public void setOssResource(Set<OssResource> ossResource) {
		this.ossResource = ossResource;
	}

}
