package com.example.multimodule.core.domain.domain.user;

import com.example.multimodule.core.domain.support.error.HttpException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {
    private static final String BLOG_ISSUER = "BLOG";

    private final String secret;
    private final Long expirationDate;

    public JwtTokenManager(
            @Value("${app.jwt-secret}") String secret,
            @Value("${app.jwt-expiration-date}") Long expirationDate
    ) {
        this.secret = secret;
        this.expirationDate = expirationDate;
    }

    public Long expireTime(Date currentDate) {
        if (currentDate == null) currentDate = new Date();
        return currentDate.getTime() + expirationDate;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String create(String email) {
        Date currentDate = new Date();
        Date expireDate = new Date(expireTime(currentDate));

        return Jwts.builder()
                .claim("email", email)
                .setIssuer(BLOG_ISSUER)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .requireIssuer(BLOG_ISSUER)
                    .build()
                    .parse(token);

            return true;
        } catch (SignatureException e) {
            throw new HttpException("Invalid Jwt Signature");
        } catch (MalformedJwtException e) {
            throw new HttpException("Invalid Jwt Token");
        } catch (ExpiredJwtException e) {
            throw new HttpException("Expired Jwt Token");
        } catch (UnsupportedJwtException e) {
            throw new HttpException("Unsupported Jwt Token");
        } catch (IllegalArgumentException e) {
            throw new HttpException("Jwt claims String Is Empty");
        } catch (IncorrectClaimException e) {
            throw new HttpException("Invalid Jwt Claim");
        }
    }

    public String getClaimTarget(String token, String target){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(target)
                .toString();
    }
}
