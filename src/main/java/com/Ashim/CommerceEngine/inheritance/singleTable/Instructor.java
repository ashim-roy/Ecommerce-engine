package com.Ashim.CommerceEngine.inheritance.singleTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private String specialization;
    private  double avgRating;
}
