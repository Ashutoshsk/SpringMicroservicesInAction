package com.spma.organizationservice.service;

import com.spma.organizationservice.events.source.SimpleSourceBean;
import com.spma.organizationservice.model.Organization;
import com.spma.organizationservice.repository.OrganizationRepository;
import com.spma.organizationservice.utils.ActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    @Autowired
    SimpleSourceBean simpleSourceBean;
    @Autowired
    private OrganizationRepository repository;

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
      //  simpleSourceBean.publishOrganizationChange("GET", organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organization = repository.save(organization);
      //  simpleSourceBean.publishOrganizationChange(
       //         String.valueOf(ActionEnum.CREATED),
        //        organization.getId());
        return organization;
    }

    public void update(Organization organization) {
        repository.save(organization);
        simpleSourceBean.publishOrganizationChange("UPDATE", organization.getId());
    }

    public void delete(String organizationId) {
        repository.deleteById(organizationId);
        simpleSourceBean.publishOrganizationChange("DELETE", organizationId);
    }

    @SuppressWarnings("unused")
    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}