package com.Ashim.CommerceEngine.userService.services;

import com.Ashim.CommerceEngine.inheritanceDemo.joinedTable.User;
import com.Ashim.CommerceEngine.userService.exceptions.UnauthorizedException;
import com.Ashim.CommerceEngine.userService.models.Token;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password) throws UsernameNotFoundException, UnauthorizedException;

    User validateToken(String tokenValue);

    void logout(String tokenValue);
}
