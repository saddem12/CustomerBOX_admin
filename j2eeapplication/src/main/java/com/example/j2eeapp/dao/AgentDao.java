package com.example.j2eeapp.dao;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.AgentEntity;

public interface AgentDao extends GenericDao<AgentEntity, Long> {
    boolean checkAvailable(String value);
}
