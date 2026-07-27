package com.Ashim.CommerceEngine.userService.security.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement your logic to load user details from your data source (e.g., database)
        // For demonstration purposes, returning a new instance of CustomUserDetails
        return new CustomUserDetails();
    }
}
