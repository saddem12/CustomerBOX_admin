package com.example.j2eeapp.dao;

import java.util.List;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.MeetingsEntity;

public interface MeetingsDao extends GenericDao<MeetingsEntity, Long> {
	
	public List<MeetingsEntity> findByStatus();

	public List<MeetingsEntity> search(String text); 
}
