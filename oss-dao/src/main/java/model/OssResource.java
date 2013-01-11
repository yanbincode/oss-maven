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
 * 资源表
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "OSS_RESOURCE")
public class OssResource implements Serializable {

	private static final long serialVersionUID = -4173747450974761232L;

	@Id
	@SequenceGenerator(name = "S_OSS_RESOURCE", allocationSize = 1, sequenceName = "S_MONTH_RECORD")
	@GeneratedValue(generator = "S_OSS_RESOURCE", strategy = GenerationType.SEQUENCE)
	private Long resourceId;
	private String name;
	private String resInterface;
	private String type;
	private String active;
	private String description;
	/** 多对多关联关系：和角色关联 */
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "OSS_ROLE_RESOURCE", joinColumns = @JoinColumn(name = "RESOURCE_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<OssRole> ossRoles;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResInterface() {
		return resInterface;
	}

	public void setResInterface(String resInterface) {
		this.resInterface = resInterface;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<OssRole> getOssRoles() {
		return ossRoles;
	}

	public void setOssRoles(Set<OssRole> ossRoles) {
		this.ossRoles = ossRoles;
	}

}
