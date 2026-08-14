package com.ashim.userservice.security.services;

import com.Ashim.CommerceEngine.userService.models.Role;
import org.springframework.security.core.GrantedAuthority;

// i create bean not spring
public class CustomGrantedAuthority implements GrantedAuthority {

    // i have to convert Role into Athority
    private Role role;

    public CustomGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getValue();
    }
}
