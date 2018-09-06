package com.example.j2eeapp.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.j2eeapp.commons.dao.GenericJpaDao;
import com.example.j2eeapp.domain.AgenceEntity;

public class AgenceJpaDao extends GenericJpaDao<AgenceEntity, Long> implements AgenceDao {
	/*
	 * this class, is the implementation of the interface
	 * where we're going to code the declared methodes
	 */
	

	public AgenceJpaDao() {
		super(AgenceEntity.class);
	}

	public boolean checkAvailable(String value) {
		 
		/*
		 * we made query "select count(*) from agence u where u.name = :name"
		 * will return count of the rows that have to same "name" as the inputted value
		 * this medode return true if the count is less that 1(doesn't exist)
		 */
			 
		
		Assert.notNull(value);
		
		Query query = getEntityManager()
			.createQuery("select count(*) from " + getPersistentClass().getSimpleName() 
					+ " u where u.name = :name").setParameter("name", value);
		
		Long count = (Long) query.getSingleResult();
		
		return count < 1;
	}


	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<AgenceEntity> findByRegion(String region) {

		Query query =getEntityManager()
				.createQuery("select p from " + getPersistentClass().getSimpleName() + " p where p.region = :region)")
				.setParameter("region", region);

		List<AgenceEntity> p =query.getResultList();

		return p;
	}
	 
}
