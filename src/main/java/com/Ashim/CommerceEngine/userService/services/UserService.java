package com.Ashim.CommerceEngine.userService.services;

import com.Ashim.CommerceEngine.inheritanceDemo.joinedTable.User;
import com.Ashim.CommerceEngine.userService.models.Token;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password);

    User validateToken(String tokenValue);

    void logout(String tokenValue);
}
