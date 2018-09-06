package com.example.j2eeapp.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.j2eeapp.commons.domain.BaseEntity;

@Entity
@Table(name="meetings")

public class MeetingsEntity extends BaseEntity {
	
	
	private static final long serialVersionUID = -8763095690833759901L;
	private String subject;
	private String details;
	private String date;
	private String time;
	private String duration;
	private String vocalMsg;
	private String status;
	@Transient
	private String search;
	


	@ManyToOne
	@JoinColumn(name="agent_id", nullable=false)
	private AgentEntity agent;

	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private CustomerEntity customer;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getVocalMsg() {
		return vocalMsg;
	}
	public void setVocalMsg(String vocalMsg) {
		this.vocalMsg = vocalMsg;
	}
	public AgentEntity getAgent() {
		return agent;
	}
	public void setAgent(AgentEntity agent) {
		this.agent = agent;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
	

}
