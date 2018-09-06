package com.example.j2eeapp.commons.dao;

import java.io.Serializable;
import java.util.List;

/*
 * interface
 * 
 * those are the common methodes, that will be used for all the entities,
 * so instead of creating them to each entity,
 * we have them declared and coded here, and all oof those methodes will be 
 * inherated in every dao class (extends GenericDao<AgenceEntity, Long>, extends GenericJpaDao<AgenceEntity, Long>)
 */
public interface GenericDao<T, ID extends Serializable> {
	
	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T findById(ID id);
	List<T> findAll();
	void flush();
}
