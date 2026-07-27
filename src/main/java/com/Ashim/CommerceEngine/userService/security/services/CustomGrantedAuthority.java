package com.Ashim.CommerceEngine.userService.security.services;

import com.Ashim.CommerceEngine.userService.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
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
