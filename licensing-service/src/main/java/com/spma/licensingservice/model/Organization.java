package com.spma.licensingservice.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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