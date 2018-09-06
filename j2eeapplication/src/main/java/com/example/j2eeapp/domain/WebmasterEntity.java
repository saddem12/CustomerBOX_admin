package com.example.j2eeapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "webmaster")
public class WebmasterEntity extends UserEntity{


	private static final long serialVersionUID = 4386766029788762015L;
	
	

	
}
