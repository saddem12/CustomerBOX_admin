package com.example.j2eeapp.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.j2eeapp.commons.domain.BaseEntity;

@Entity
@Table(name="agence")
public class AgenceEntity extends BaseEntity {
	
	/*
	 * in this class, under the com.example.j2eeapp.domain package, 
	 we're going to create our entities (agence, users, agent ....)
	 * first wwe declare the attributes, then create the getters and setters
	 * 
	 * 
	 * (name="agence") the value of the name, is the name of this entity in the database (the table)

	 * as you can see, i declared all the attributes exept the ID, it will be inherated from the baseEntity
	 * 
	 *  now we're going to add this enttiy to the persistance file.
	 *  
	 * i don't recall all the attributes, so please correct them,

	 */

	private static final long serialVersionUID = -616186859383847142L;
	
	/**********************************/
	
	
	@OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
	private List<UserEntity> agence;
	
	public List<UserEntity> getAgence() {
		return agence;
	}
	public void setAgence(List<UserEntity> agence) {
		this.agence = agence;
	}
	
	
	/**********************************/
	
	
	
	
	private String adress;
	private String name;
	private String tel;
	private String region;
	
	/*
	 * i created the selected attribut, with type of this class, to use it to stock the entity selected by admin (to delete/update)
	 * @Transient annotation is used so it won't be read as an column for the agence table in the database
	 */
	@Transient
	private AgenceEntity selected;
	
	
	public AgenceEntity getSelected() {
		return selected;
	}
	public void setSelected(AgenceEntity selected) {
		this.selected = selected;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	

	@Override
	public boolean equals(Object object) {
	    if (object == null) {
	        return true;
	    }
	    if (getClass() != object.getClass()) {
	        return true;
	    }
	    final AgenceEntity other = (AgenceEntity) object;
	    if (!Objects.equals(this.getId(), other.getId())) {
	        return true;
	    }
	    if (!Objects.equals(this.getName(), other.getName())) {
	        return true;
	    }
	    return true;
	}
	
	
}
