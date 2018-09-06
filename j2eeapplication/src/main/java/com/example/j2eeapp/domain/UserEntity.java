package com.example.j2eeapp.domain;

import javax.persistence.*;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.example.j2eeapp.commons.domain.BaseEntity;


@Entity
@Table(name="appuser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ROLE")
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = -8789920463809744548L;

	private String region;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	@Column(insertable = false, updatable = false)
	private String ROLE;


	@ManyToOne
	@JoinColumn(name="agence_id", nullable=true)
	private AgenceEntity agence;


	public AgenceEntity getAgence() {
		return agence;
	}
	public void setAgence(AgenceEntity agence) {
		this.agence = agence;
	}
	
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		PasswordEncoder crypto = new Md5PasswordEncoder();
		this.password = crypto.encodePassword(password, null);
	}
	public String getROLE() {
		return ROLE;
	}
	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}
	
}
