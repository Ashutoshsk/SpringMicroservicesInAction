package com.spma.organizationservice.events.source;

import com.spma.organizationservice.events.model.OrganizationChangeModel;
import com.spma.organizationservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
    private static final String SUPPLIER_BINDING_NAME = "publishOrganizationChange-out-0";
    private final StreamBridge stream;

    @Autowired
    public SimpleSourceBean(StreamBridge stream) {
        this.stream = stream;
    }

    public void publishOrganizationChange(String action, String organizationId) {
        logger.debug("Sending Kafka message {} for Organization Id: {}", action, organizationId);
        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                organizationId,
                UserContext.getCorrelationId());

        stream.send(SUPPLIER_BINDING_NAME, change);
    }
}