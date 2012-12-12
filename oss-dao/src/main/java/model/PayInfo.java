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
 * 实体类，对应数据库pay_info表
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "PAY_INFO")
public class PayInfo implements Serializable {

	private static final long serialVersionUID = -2020843499350726609L;
	/**
	 * 设置主键的规则
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PAY_INFO")
	@SequenceGenerator(name = "S_PAY_INFO", allocationSize = 1, sequenceName = "S_PAY_INFO")
	private Long recordId;
	@NotNull
	private Date dayTime;
	@NotEmpty
	private String payContent;
	@NotNull
	private BigDecimal payNumber;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PAY_MEMBER_ID")
	@NotNull
	private OssUser payer;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PAY_TYPE")
	@NotNull
	private AccountType accountType;
	private String payValue;
	private String remark;
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

	public String getPayContent() {
		return payContent;
	}

	public void setPayContent(String payContent) {
		this.payContent = payContent;
	}

	public BigDecimal getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(BigDecimal payNumber) {
		this.payNumber = payNumber;
	}

	public OssUser getPayer() {
		return payer;
	}

	public void setPayer(OssUser payer) {
		this.payer = payer;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getPayValue() {
		return payValue;
	}

	public void setPayValue(String payValue) {
		this.payValue = payValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
