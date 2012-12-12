package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotEmpty;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType implements Serializable {

	private static final long serialVersionUID = 5428443937228948303L;

	/** 用于支出的类型 */
	public static final String USE_PLACE_PAY = "1";
	/** 用于收入的类型 */
	public static final String USE_PLACE_EARN = "2";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ACCOUNT_TYPE")
	@SequenceGenerator(name = "S_ACCOUNT_TYPE", allocationSize = 1, sequenceName = "S_ACCOUNT_TYPE")
	private Long recordId;
	@NotEmpty
	private String typeId;
	@NotEmpty
	private String typeName;
	@NotEmpty
	private String usePlace;
	@NotEmpty
	private String active;
	@Column(name = "TYPE_DESCRIPTION")
	private String description;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CREATOR_ID", updatable = false)
	private OssUser creator;
	private Date createdTime;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MODIFIER_ID")
	private OssUser modifier;
	private Date modifiedTime;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUsePlace() {
		return usePlace;
	}

	public void setUsePlace(String usePlace) {
		this.usePlace = usePlace;
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

	public OssUser getCreator() {
		return creator;
	}

	public void setCreator(OssUser creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public OssUser getModifier() {
		return modifier;
	}

	public void setModifier(OssUser modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
