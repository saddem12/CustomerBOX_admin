package com.example.j2eeapp.services;

import javax.faces.event.AjaxBehaviorEvent;

import com.example.j2eeapp.domain.UserEntity;


public interface UserService {

	
	boolean createUser(UserEntity userEntity);
	
	
	boolean checkAvailable(AjaxBehaviorEvent event);
	
	public boolean returnrole(String email);
	
	UserEntity loadUserEntityByUsername(String userName);
}
