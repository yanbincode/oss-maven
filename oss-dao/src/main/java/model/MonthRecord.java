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

import org.hibernate.validator.NotNull;

/**
 * 月总结信息
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "MONTH_RECORD")
public class MonthRecord implements Serializable {

	private static final long serialVersionUID = -1872899209002804542L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MONTH_RECORD")
	@SequenceGenerator(name = "S_MONTH_RECORD", allocationSize = 1, sequenceName = "S_MONTH_RECORD")
	private Long recordId;
	@NotNull
	private Date monthTime;
	@NotNull
	private BigDecimal monthEarnCount;
	@NotNull
	private BigDecimal monthPayCount;
	@NotNull
	private BigDecimal monthCount;
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

	public Date getMonthTime() {
		return monthTime;
	}

	public void setMonthTime(Date monthTime) {
		this.monthTime = monthTime;
	}

	public BigDecimal getMonthEarnCount() {
		return monthEarnCount;
	}

	public void setMonthEarnCount(BigDecimal monthEarnCount) {
		this.monthEarnCount = monthEarnCount;
	}

	public BigDecimal getMonthPayCount() {
		return monthPayCount;
	}

	public void setMonthPayCount(BigDecimal monthPayCount) {
		this.monthPayCount = monthPayCount;
	}

	public BigDecimal getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(BigDecimal monthCount) {
		this.monthCount = monthCount;
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
