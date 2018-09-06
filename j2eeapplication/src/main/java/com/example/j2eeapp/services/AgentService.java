package com.example.j2eeapp.services;

import com.example.j2eeapp.domain.AgentEntity;

import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

public interface AgentService {

    List<AgentEntity> findAll();

    boolean createAgent(AgentEntity AgentEntity);

    void updateAgent(AgentEntity AgentEntity);

    void deleteAgent(AgentEntity AgentEntity);

    boolean checkAvailable(AjaxBehaviorEvent event);
}
