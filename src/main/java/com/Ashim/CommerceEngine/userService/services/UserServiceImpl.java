package com.Ashim.CommerceEngine.userService.services;

import com.Ashim.CommerceEngine.inheritanceDemo.joinedTable.User;
import com.Ashim.CommerceEngine.userService.models.Token;
import com.Ashim.CommerceEngine.userService.repositories.UserRepository;

import java.util.Optional;
import java.util.OptionalInt;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            //redirect to login
            User user = optionalUser.get();
        }
        // if user not present we create a user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        // TODO: store  in bcrypt

        return userRepository.save(user);

    }

    @Override
    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // redircet to signup
            return null;
            // oof throw exception, if FE gets it, redirct to signup
        }

        return null;
    }

    @Override
    public User validateToken(String tokenValue) {
        return null;
    }

    @Override
    public void logout(String tokenValue) {

    }
}
