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

/**
 * 角色表
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "OSS_ROLE")
public class OssRole implements Serializable {

	private static final long serialVersionUID = 5181428366894632432L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_OSS_ROLE")
	@SequenceGenerator(name = "S_OSS_ROLE", allocationSize = 1, sequenceName = "S_OSS_ROLE")
	private Long roleId;
	private String name;
	private String roleLevel;
	private String active;
	/** 多对多关联关系：角色资源关系 */
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "OSS_ROLE_RESOURCE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	private Set<OssResource> ossResource;
	/** 多对多关联关系：角色人员关系 */
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "OSS_MEMBER_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "MEMBER_ID"))
	private Set<OssUser> ossUsers;
	private String description;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set<OssResource> getOssResource() {
		return ossResource;
	}

	public void setOssResource(Set<OssResource> ossResource) {
		this.ossResource = ossResource;
	}

	public Set<OssUser> getOssUsers() {
		return ossUsers;
	}

	public void setOssUsers(Set<OssUser> ossUsers) {
		this.ossUsers = ossUsers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
