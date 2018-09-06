package com.example.j2eeapp.services.impl;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;

import com.example.j2eeapp.dao.AgenceDao;
import com.example.j2eeapp.domain.AgenceEntity;
import com.example.j2eeapp.services.AgenceService;

public class AgenceServiceImpl implements AgenceService {

	/*
	 * this class, is the implementation of the interface
	 * where we're going to code the declared methodes
	 * we declare the dao, genrate getter and setter
	 * now we''re going to call the dao methodes 
	 * 
	 * 
	 * for the save, update,delate,findAll methodes, as you can see in the dao layer
	 * we still didn't create any methodes, but since it's extended from the GenericDao.java
	 * it inherate all it methodes
	 */
	
	private AgenceDao agenceDao;
	

	

	private String region;
	private List<AgenceEntity> agences;
	
	public void onRegionChange() {
		try {
			setAgences(GetListByRegion(region));
		} catch(Exception e) {
			FacesMessage message = constructFatalMessage(e.getMessage(), null);
			getFacesContext().addMessage(null, message);
			
		}
		
	}

	public List<AgenceEntity> GetListByRegion(String region) {
		try {
			return agenceDao.findByRegion(region);
		} catch(Exception e) {
			FacesMessage message = constructFatalMessage(e.getMessage(), null);
			getFacesContext().addMessage(null, message);
			return null;
		}
	}

	public List<AgenceEntity> getAgences() {
		return agences;
	}

	public void setAgences(List<AgenceEntity> agences) {
		this.agences = agences;
	}
	
	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
	public List<AgenceEntity> findAll() {
		return agenceDao.findAll();
	}

	public boolean createAgence(AgenceEntity agenceEntity) {
        if(agenceDao.checkAvailable(agenceEntity.getName())) {
            agenceDao.save(agenceEntity);

            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agenceAddedMsg"), agenceEntity.getName()), null);
    		getFacesContext().addMessage(null, message);
    		return true;
    		
        }else {
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agenceExistsMsg"), agenceEntity.getName()), null);
    		getFacesContext().addMessage(null, message);
    		return false;
        }		
	}

	public void updateAgence(AgenceEntity agenceEntity) {
        agenceDao.update(agenceEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agenceUpdatedMsg"), agenceEntity.getName()), null);
		getFacesContext().addMessage(null, message);
	}

	public void deleteAgence(AgenceEntity agenceEntity) {
        agenceDao.delete(agenceEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agenceDeletedMsg"), agenceEntity.getName()), null);
		getFacesContext().addMessage(null, message);		
	}

	public boolean checkAvailable(AjaxBehaviorEvent event) {
		/*
		 * we're goin to use the checkAvailable methode, to make sure that 
		 * new angences(that will be added by the webmaster) won't be duplicated  
		 */
		

		InputText inputText = (InputText) event.getSource();
		String value = (String) inputText.getValue();
		
		boolean available = agenceDao.checkAvailable(value);
		
		if (!available) {
			// if available= false (the agence already exist) the Message agenceExistsMsg will be showen to the webmastee

			FacesMessage message = constructErrorMessage(null, String.format(getMessageBundle().getString("agenceExistsMsg"), value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
		} else {
			// if available= true (the agence doesn't exist) the Message agenceAvailableMsg will be showen to the webmastee

			FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("agenceAvailableMsg"), value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
		}
		
		return available;	}

	public AgenceDao getAgenceDao() {
		return agenceDao;
	}

	public void setAgenceDao(AgenceDao agenceDao) {
		this.agenceDao = agenceDao;
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
