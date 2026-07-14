package com.Ashim.CommerceEngine.userService.models;


import com.Ashim.CommerceEngine.userService.services.UserServiceImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    private String name;
    private String password;
    private String email;

    @ManyToMany
    private List<Role> roles;

    /* To set list of roles as empty do this
     private List<Role> roles = new ArrayList<>();
    Now you can remove this line from UserServiceImpl:
            user.setRoles(new ArrayList<>());

     */

}
