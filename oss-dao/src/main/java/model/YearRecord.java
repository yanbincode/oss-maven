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
 * 年报信息
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "YEAR_RECORD")
public class YearRecord implements Serializable {

	private static final long serialVersionUID = 7870291570220825666L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_YEAR_RECORD")
	@SequenceGenerator(name = "S_YEAR_RECORD", allocationSize = 1, sequenceName = "S_YEAR_RECORD")
	private Long recordId;
	@NotNull
	private Date yearTime;
	@NotNull
	private BigDecimal yearEarnCount;
	@NotNull
	private BigDecimal yearPayCount;
	@NotNull
	private BigDecimal yearCount;
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

	public Date getYearTime() {
		return yearTime;
	}

	public void setYearTime(Date yearTime) {
		this.yearTime = yearTime;
	}

	public BigDecimal getYearEarnCount() {
		return yearEarnCount;
	}

	public void setYearEarnCount(BigDecimal yearEarnCount) {
		this.yearEarnCount = yearEarnCount;
	}

	public BigDecimal getYearPayCount() {
		return yearPayCount;
	}

	public void setYearPayCount(BigDecimal yearPayCount) {
		this.yearPayCount = yearPayCount;
	}

	public BigDecimal getYearCount() {
		return yearCount;
	}

	public void setYearCount(BigDecimal yearCount) {
		this.yearCount = yearCount;
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