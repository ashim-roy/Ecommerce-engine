package com.Ashim.CommerceEngine.userService.dtos;

import com.Ashim.CommerceEngine.userService.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TokenDto {

    private String tokenValue;
    private Date expiryDate;

    public static TokenDto from(Token token) {
        TokenDto dto = new TokenDto();
        dto.setTokenValue(token.getTokenValue());
        dto.setExpiryDate(token.getExpiryDate());
        return dto;
    }
}