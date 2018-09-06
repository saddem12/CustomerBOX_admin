package com.example.j2eeapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "Agent")
public class AgentEntity extends UserEntity{


	private static final long serialVersionUID = 4386766029788762015L;
	
	
	@Transient
	private AgentEntity selected;
	
	
	public AgentEntity getSelected() {
		return selected;
	}
	public void setSelected(AgentEntity selected) {
		this.selected = selected;
	}
	

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	private List<MeetingsEntity> agent;


	public List<MeetingsEntity> getAgent() {
		return agent;
	}
	public void setAgent(List<MeetingsEntity> agent) {
		this.agent = agent;
	}
	

	
}
