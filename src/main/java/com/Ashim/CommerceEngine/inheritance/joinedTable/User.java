package com.Ashim.CommerceEngine.inheritance.joinedTable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

}

/*
Annotation needed?

	1. @Inheritance(strategy = InheritanceType.JOINED)
	2. @Entity(name = "xxxxx") on all model including parent class
    3. @PrimaryKeyJoinColumn(name = "user_id") on every child class

 */
