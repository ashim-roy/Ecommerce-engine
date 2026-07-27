package com.Ashim.CommerceEngine.userService.security.services;

import com.Ashim.CommerceEngine.userService.models.Role;
import com.Ashim.CommerceEngine.userService.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails( User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.accountNonExpired = true; // Set based on your logic
        this.accountNonLocked = true; // Set based on your logic
        this.credentialsNonExpired = true; // Set based on your logic
        this.enabled = true; // Set based on your logic

        this.authorities = new ArrayList<>(); //
        for (Role role : user.getRoles()) {    // Fetch all roles assigned to the user using user.getRoles().
            authorities.add(new CustomGrantedAuthority(role));
        }
// For every role, create a CustomGrantedAuthority object (Adapter Pattern), which converts our Role into Spring Security's GrantedAuthority.
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
