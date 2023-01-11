package com.project.repring.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    private String username;
    private String password;

    public LoginDto(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

}
