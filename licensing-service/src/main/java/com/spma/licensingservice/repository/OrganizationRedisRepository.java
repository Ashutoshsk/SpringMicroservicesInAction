package com.spma.licensingservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spma.licensingservice.model.Organization;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization,String>  {
}