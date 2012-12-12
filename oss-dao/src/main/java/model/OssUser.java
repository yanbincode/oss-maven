package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 成员表
 * 
 * @author yanbin
 * 
 */
@Entity
@Table(name = "MEMBER_INFO")
public class OssUser implements Serializable {

	private static final long serialVersionUID = -1578851057043285070L;

	/** 系统管理员的ID */
	public static final Long SYSTEM_ADMIN_ID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MEMBER_INFO")
	@SequenceGenerator(name = "S_MEMBER_INFO", allocationSize = 1, sequenceName = "S_MEMBER_INFO")
	@Column(name = "MEMBER_ID")
	private Long userId;
	private String name;
	private Long age;
	private String gender;
	private Long phone;
	private Long idCard;
	private String userName;
	private String passWord;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getIdCard() {
		return idCard;
	}

	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
