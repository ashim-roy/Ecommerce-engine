package com.Ashim.CommerceEngine.inheritance.mappedSperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "msc_ta")
public class TA extends User {
    private int numberOfSessions;
}
