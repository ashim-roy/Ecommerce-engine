package com.Ashim.CommerceEngine.inheritance.joinedTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private String specialization;
    private  double avgRating;
}
