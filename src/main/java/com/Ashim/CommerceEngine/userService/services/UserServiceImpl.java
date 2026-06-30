package com.Ashim.CommerceEngine.userService.services;

import com.Ashim.CommerceEngine.userService.exceptions.UnauthorizedException;
import com.Ashim.CommerceEngine.userService.models.Token;
import com.Ashim.CommerceEngine.userService.models.User;
import com.Ashim.CommerceEngine.userService.repositories.TokenRepository;
import com.Ashim.CommerceEngine.userService.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    public UserServiceImpl(UserRepository userRepository,
                           TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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
    public Token login(String email, String password) throws UsernameNotFoundException, UnauthorizedException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            // oof throw exception, if FE gets it, redirct to signup
            throw new UsernameNotFoundException("Invalid email: " + email + " not found");
        }

        // if present create a token
        User user = optionalUser.get();

        if (user.getPassword().equals(password)) {
            //login successful, create/generate token.
            Token token = new Token();
            token.setUser(user);
            token.setValue("ahshshsshshshshs");
            // token.setExpiryDate(new Date(System.currentTimeMillis() + 3600000*24*30)); // 1 hour/30days from now using co pilot
            //deepak's implementation
            Date currentDate = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, 30);

            // get the updated time as a Date object
            Date dateAfter30Dats = calendar.getTime();
            token.setExpiryDate(dateAfter30Dats);

            return tokenRepository.save(token); // save the token object and then return
        }
        // when if comditio not true/ password not matching
        //login failed throw unauthorized user or return null or from excetion we can do it
        throw new UnauthorizedException("login failed, Invalid password");
    }

    @Override
    public User validateToken(String tokenValue) {
        // check token is present in DB, token i snot deleted and
        //token expiry time is greater than current time
        Optional<Token> optionalToken = tokenRepository.
                findByTokenValueAndDeletedAndExpiryDateGreaterThan(
                        tokenValue, false, new Date()
                );

        if (optionalToken.isEmpty()) {
            return null; //token invalid
        }
       return optionalToken.get().getUser();
    }

    @Override
    public void logout(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue);
        if (optionalToken.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }

        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }
}
