package com.ashim.userservice.services;

import com.ashim.CommerceEngine.userService.exceptions.UnauthorizedException;
import com.Ashim.CommerceEngine.userService.models.Token;
import com.Ashim.CommerceEngine.userService.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password) throws UsernameNotFoundException, UnauthorizedException;

    User validateToken(String tokenValue);

    void logout(String tokenValue);
}
