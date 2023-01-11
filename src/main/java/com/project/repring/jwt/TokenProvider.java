package com.project.repring.jwt;

import com.project.repring.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final UserDetailsService userDetailsService;
    private final HttpServletResponse response;
    public static final String BEARER_TYPE = "bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "RefreshToken";

    public TokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                         @Value("${security.jwt.token.access-token-expire-length}") final long accessTokenValidityInMilliseconds,
                         @Value("${security.jwt.token.refresh-token-expire-length}") final long refreshTokenValidityInMilliseconds,
                         final UserDetailsService userDetailsService,
                         final HttpServletResponse response) {

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.userDetailsService = userDetailsService;
        this.response = response;
    }

    public TokenDto generateToken(String userPK) {
        Claims claims = Jwts.claims().setSubject(userPK);
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + accessTokenValidityInMilliseconds);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        response.addHeader(AUTH_HEADER, accessToken);

        Date refreshTokenExpiresIn = new Date(now.getTime() + refreshTokenValidityInMilliseconds);
        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        response.addHeader(REFRESH_HEADER, refreshToken);

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshTokenExpiresIn(refreshTokenExpiresIn.getTime())
                .build();

    }

    // 토큰에서 회원 정보 추출
    public String getUserPK(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(jwtToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String jwtToken) {

        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(setTokenName(jwtToken));
            return claims.getBody().getExpiration().after(new Date());
        }
        catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Todo : 필요 여부 확인
    private String setTokenName(String bearerToken) {
        return bearerToken.substring(7);
    }
}
