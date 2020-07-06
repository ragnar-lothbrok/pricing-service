package com.xyz.assignment.jpa.repositories;

import com.xyz.assignment.jpa.entities.SubscriptionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionType, Long> {
    
}