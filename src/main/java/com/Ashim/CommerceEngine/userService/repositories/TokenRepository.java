package com.Ashim.CommerceEngine.userService.repositories;

import com.Ashim.CommerceEngine.userService.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token saveToken(Token token);

    Optional<Token> findByValue(String value);
}
