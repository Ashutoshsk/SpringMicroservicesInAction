package com.spma.licensingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisHash("organization")
public class Organization extends RepresentationModel<Organization> {
    @Id
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}