package com.ashim.userservice.services;

import com.ashim.userservice.exceptions.UnauthorizedException;
import com.ashim.userservice.exceptions.UnauthorizedException;
import com.ashim.userservice.models.Token;
import com.ashim.userservice.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password) throws UsernameNotFoundException, UnauthorizedException;

    User validateToken(String tokenValue);

    void logout(String tokenValue);
}
