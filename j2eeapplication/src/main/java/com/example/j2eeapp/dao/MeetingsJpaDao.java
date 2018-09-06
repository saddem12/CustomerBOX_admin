package com.example.j2eeapp.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.example.j2eeapp.commons.dao.GenericJpaDao;
import com.example.j2eeapp.domain.MeetingsEntity;


public class MeetingsJpaDao extends GenericJpaDao<MeetingsEntity, Long> implements MeetingsDao {

	public MeetingsJpaDao() {
		super(MeetingsEntity.class);
	}

	


	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MeetingsEntity> findByStatus() {

		Query query =getEntityManager()
				.createQuery("select p from " + getPersistentClass().getSimpleName() + " p where p.status = 'done'");

		List<MeetingsEntity> p =query.getResultList();

		return p;
	}





	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MeetingsEntity> search(String text) {

		Query query =getEntityManager()
				.createQuery("select p from " + getPersistentClass().getSimpleName() + " p WHERE time like '%"+text+"%' or date like '%"+text+"%' or duration like '%"+text+"%' ");

		List<MeetingsEntity> p =query.getResultList();

		return p;
	}
}
