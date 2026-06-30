package com.Ashim.CommerceEngine.userService.repositories;

import com.Ashim.CommerceEngine.userService.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValue(String tokenValue);

    Optional<Token> findByTokenValueAndDeletedAndExpiryDateGreaterThan(
            String tokenValue,
            boolean deleted,
            Date currentTime
            );
}

/*
SELECT *
FROM token
WHERE token_value = ?
AND deleted = false
AND expiry_date > ?
 */