package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * 收入信息
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "EARN_INFO")
public class EarnInfo implements Serializable {

	private static final long serialVersionUID = -3808549116685881530L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EARN_INFO")
	@SequenceGenerator(name = "S_EARN_INFO", allocationSize = 1, sequenceName = "S_EARN_INFO")
	private Long recordId;
	@NotNull
	private Date dayTime;
	@NotEmpty
	private String earnContent;
	@NotNull
	private BigDecimal earnNumber;
	private String remark;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "EARN_MEMBER_ID")
	@NotNull
	private OssUser earner;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "EARN_TYPE")
	@NotNull
	private AccountType accountType;
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

	public Date getDayTime() {
		return dayTime;
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	public String getEarnContent() {
		return earnContent;
	}

	public void setEarnContent(String earnContent) {
		this.earnContent = earnContent;
	}

	public BigDecimal getEarnNumber() {
		return earnNumber;
	}

	public void setEarnNumber(BigDecimal earnNumber) {
		this.earnNumber = earnNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OssUser getEarner() {
		return earner;
	}

	public void setEarner(OssUser earner) {
		this.earner = earner;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
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
