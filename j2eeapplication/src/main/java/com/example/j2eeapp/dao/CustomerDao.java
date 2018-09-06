package com.example.j2eeapp.dao;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.CustomerEntity;

public interface CustomerDao extends GenericDao <CustomerEntity, Long>  {

    boolean checkAvailable(String value);
}
