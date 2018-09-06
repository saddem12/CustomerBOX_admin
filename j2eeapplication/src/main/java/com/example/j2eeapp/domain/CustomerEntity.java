package com.example.j2eeapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



@Entity
@DiscriminatorValue(value = "Customer")
public class CustomerEntity extends UserEntity{
    private static final long serialVersionUID = 7358028269482342847L;

	@Transient
	private CustomerEntity selected;
	
	
	public CustomerEntity getSelected() {
		return selected;
	}
	public void setSelected(CustomerEntity selected) {
		this.selected = selected;
	}
	

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<MeetingsEntity> customer;


	public List<MeetingsEntity> getCustomer() {
		return customer;
	}
	public void setCustomer(List<MeetingsEntity> customer) {
		this.customer = customer;
	}
	


}
