package com.Ashim.CommerceEngine.inheritance.mappedSperClass;

import com.Ashim.CommerceEngine.models.Product;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_mentor")
public class Mentor extends User{
    private String company;

}
