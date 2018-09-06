package com.example.j2eeapp.services;

import java.util.List;

import com.example.j2eeapp.domain.MeetingsEntity;

public interface MeetingsService {
	
	
	List<MeetingsEntity> findAll();
	
	boolean createMeeting(MeetingsEntity meetingsEntity);
	
	void updateMeeting(MeetingsEntity meetingsEntity);
	
	void deleteMeeting(MeetingsEntity meetingsEntity);

	public void findByStatus();

}
