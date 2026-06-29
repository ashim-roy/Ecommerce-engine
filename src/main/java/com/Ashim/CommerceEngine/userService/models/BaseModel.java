package com.Ashim.CommerceEngine.userService.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
    private Boolean deleted;   // Deleted for if token is active or not, token is active or not.
}


/*
4 ways we can represnet inheritance…

MappedSuperClass,
JoinedTable,
TablePerClass,
SingleTable


 */
