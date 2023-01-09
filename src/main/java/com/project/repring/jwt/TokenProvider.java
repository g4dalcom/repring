package com.project.repring.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public TokenProvider(@Value("${security.jwt.token.secret-key}")
                         final String secretKey,
                         @Value("${security.jwt.token.access-token-expire-length}")
                         final long accessTokenExpireTime,
                         @Value("${security.jwt.token.refresh-token-expire-length}")
                         final long refreshTokenExpireTime) {

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }




}
