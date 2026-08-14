package com.Ashim.CommerceEngine.userService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedAt;
   //private Boolean deleted;   // Deleted for if token is active or not, token is active or not.
   private Boolean deleted = false;
}


/*
4 ways we can represnet inheritance…

MappedSuperClass,
JoinedTable,
TablePerClass,
SingleTable


 */
