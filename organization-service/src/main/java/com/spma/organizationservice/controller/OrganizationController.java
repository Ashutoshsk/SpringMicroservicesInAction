package com.spma.organizationservice.controller;

import com.spma.organizationservice.model.Organization;
import com.spma.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService service;

    @PreAuthorize("hasAnyRole('ostock-admin','ostock-user','OADMIN', 'OUSER')")
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        return ResponseEntity.ok(service.findById(organizationId));
    }

    @PreAuthorize("hasAnyRole('ostock-admin','ostock-user','OADMIN', 'OUSER')")
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.update(organization);
    }

    @PreAuthorize("hasAnyRole('ostock-admin','ostock-user','OADMIN', 'OUSER')")
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(service.create(organization));
    }

    @PreAuthorize("hasAnyRole('ostock-admin','OADMIN')")
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("organizationId") String organizationId) {
        service.delete(organizationId);
    }

}