package com.spma.licensingservice.repository;

import com.spma.licensingservice.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization,String>  {
}