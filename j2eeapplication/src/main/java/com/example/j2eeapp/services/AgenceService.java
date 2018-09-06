package com.example.j2eeapp.services;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import com.example.j2eeapp.domain.AgenceEntity;

public interface AgenceService {

	/*
	 * we use the service layer for data treatment 
	 * (or just transefer from the dao to the view)
	 * 
	 *	in the interface we only declare 
	 * the methodes we're going to use.
	 */

	List<AgenceEntity> findAll();
	
	boolean createAgence(AgenceEntity agenceEntity);
	
	void updateAgence(AgenceEntity agenceEntity);
	
	void deleteAgence(AgenceEntity agenceEntity);

	boolean checkAvailable(AjaxBehaviorEvent event);

	void onRegionChange();

	List<AgenceEntity> GetListByRegion(String region);

}
