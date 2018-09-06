package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.j2eeapp.dao.MeetingsDao;
import com.example.j2eeapp.domain.MeetingsEntity;
import com.example.j2eeapp.services.MeetingsService;

public class MeetingsServicImpl implements MeetingsService{

	private MeetingsDao meetingsDao;
	
	private List<MeetingsEntity> hList = new ArrayList<MeetingsEntity>();
	
	
	public MeetingsDao getMeetingsDao() {
		return meetingsDao;
	}

	public void setMeetingsDao(MeetingsDao meetingsDao) {
		this.meetingsDao = meetingsDao;
	}

	public List<MeetingsEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createMeeting(MeetingsEntity meetingsEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateMeeting(MeetingsEntity meetingsEntity) {
		// TODO Auto-generated method stub
		
	}

	public void deleteMeeting(MeetingsEntity meetingsEntity) {
		// TODO Auto-generated method stub
		
	}

	public void findByStatus() {
		hList.clear();
		hList= meetingsDao.findByStatus();
	}

	public void search(String text) {
		List<MeetingsEntity> h = new ArrayList<MeetingsEntity>();
		hList= meetingsDao.search(text);
		for (int i=0;i < hList.size();i++)
		{
			if(hList.get(i).getStatus().equals("done")){
				h.add(hList.get(i));
			}
		}
		hList.clear();
		hList=h;
	}

	public List<MeetingsEntity> gethList() {
		return hList;
	}

	public void sethList(List<MeetingsEntity> hList) {
		this.hList = hList;
	}



}
