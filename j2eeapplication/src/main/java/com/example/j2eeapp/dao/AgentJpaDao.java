package com.example.j2eeapp.dao;

import com.example.j2eeapp.commons.dao.GenericJpaDao;
import com.example.j2eeapp.domain.AgentEntity;
import org.springframework.util.Assert;

import javax.persistence.Query;

public class AgentJpaDao  extends GenericJpaDao<AgentEntity, Long> implements AgentDao  {

    public AgentJpaDao() {
        super(AgentEntity.class);
    }

    public boolean checkAvailable(String value) {
        Assert.notNull(value);

        Query query = getEntityManager()
                .createQuery("select count(*) from " + getPersistentClass().getSimpleName()
                        + " u where u.userName = :userName").setParameter("userName", value);

        Long count = (Long) query.getSingleResult();

        return count < 1;
    }
}
