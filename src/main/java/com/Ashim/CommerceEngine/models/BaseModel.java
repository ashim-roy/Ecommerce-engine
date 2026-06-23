package com.Ashim.CommerceEngine.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private long id;
    private long createdAt;
    private long lastUpdatedAt;

}
