package com.example.j2eeapp.dao;

import java.util.List;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.AgenceEntity;

public interface AgenceDao extends GenericDao<AgenceEntity, Long>  {

	boolean checkAvailable(String value);

	List<AgenceEntity> findByRegion(String region);

	/*
	 * now we're going to create the DAO layer
	 * we start by creating the interface, in the interface we only declare 
	 * the methods we're going to use.
	 * 
	 * we use the Dao layer to create methods that connect to the database, 
	 * queries ....
	 */
	
	
}
