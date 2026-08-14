package com.Ashim.CommerceEngine.userService.dtos;

import com.Ashim.CommerceEngine.userService.models.Role;
import com.Ashim.CommerceEngine.userService.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<String> roles;

    // Notice that from() is declared as static because it creates a new UserDto object from a given User. We don't need an existing UserDto instance to perform the conversion.

    public static UserDto from(User user) {
        if(user==null){
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
       // userDto.setRoles(user.getRoles());

        userDto.setRoles(new ArrayList<>());

        for (Role role : user.getRoles()) {
            userDto.getRoles().add(role.getValue());
        }

        return userDto;

    }
}
