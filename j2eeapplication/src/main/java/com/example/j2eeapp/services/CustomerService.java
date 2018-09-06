package com.example.j2eeapp.services;

import com.example.j2eeapp.domain.CustomerEntity;

import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

public interface CustomerService {
    List<CustomerEntity> findAll();

    boolean createCustomer(CustomerEntity customerEntity);

    void updateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(CustomerEntity customerEntity);

    boolean checkAvailable(AjaxBehaviorEvent event);
}