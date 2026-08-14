package com.ashim.userservice.security.services;

import com.Ashim.CommerceEngine.userService.models.User;
import com.Ashim.CommerceEngine.userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty()) {
                throw new UsernameNotFoundException("User not found with email: " + username);
        }


        // Implement your logic to load user details from your data source (e.g., database)
        // For demonstration purposes, returning a new instance of CustomUserDetails

        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}

// Login Request → CustomUserDetailsService → UserRepository → User (DB) → CustomUserDetails → Spring Security