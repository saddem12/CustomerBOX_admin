package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.example.j2eeapp.dao.UserDao;
import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserService;


public class UserServiceImpl implements UserService, UserDetailsService {

	private UserDao userDao;

	
	public boolean createUser(UserEntity userEntity) {
		
		if (!userDao.checkAvailable(userEntity.getUserName())) {
			FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("userExistsMsg"), userEntity.getUserName()), null);
			getFacesContext().addMessage(null, message);
			
			return false;
		}
		
		try {
			userDao.save(userEntity);
		} catch(Exception e) {
			FacesMessage message = constructFatalMessage(e.getMessage(), null);
			getFacesContext().addMessage(null, message);
			
			return false;
		}
		
		return true;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	


	public boolean returnrole(String email){

		UserEntity user = userDao.loadUserByUserName(email);
		try {
			if (user.getROLE().equals("webmaster"))
			{					
				return true;
			}
			else 
			{	
				return false;
			}
		} catch (Exception e) {
			System.out.print("\n\n\n*-*-*-*-*-*-*-*-"+e+"*-*-*-*-*-*-*-\n\n\n");
		}
		return false;
	}


	
	public boolean checkAvailable(AjaxBehaviorEvent event) {
		
		InputText inputText = (InputText) event.getSource();
		String value = (String) inputText.getValue();
		
		boolean available = userDao.checkAvailable(value);
		
		if (!available) {
			FacesMessage message = constructErrorMessage(null, String.format(getMessageBundle().getString("userExistsMsg"), value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
		} else {
			FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("userAvailableMsg"), value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
		}
		
		return available;
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserEntity user = userDao.loadUserByUserName(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format(getMessageBundle().getString("badCredentials"), userName));
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		User userDetails = new User(user.getUserName(), user.getPassword(), authorities);
		
		return userDetails;
	}
	

	public UserEntity loadUserEntityByUsername(String userName) {
		return userDao.loadUserByUserName(userName);
	}

	protected FacesMessage constructErrorMessage(String message, String detail){
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail);
	}
	
	protected FacesMessage constructInfoMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
	}
	
	protected FacesMessage constructFatalMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail);
	}
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	protected ResourceBundle getMessageBundle() {
		return ResourceBundle.getBundle("message-labels");
	}



}
