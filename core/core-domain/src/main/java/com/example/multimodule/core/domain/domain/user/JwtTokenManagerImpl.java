package com.example.multimodule.core.domain.domain.user;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenManagerImpl implements JwtTokenManager {
    private static final String BLOG_ISSUER = "BLOG";

    private final String secret;
    private final Long expirationDate;

    @Override
    public Long expireTime(Date currentDate) {
        if (currentDate == null) currentDate = new Date();
        return currentDate.getTime() + expirationDate;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String makeToken(String email) {
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

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .requireIssuer(BLOG_ISSUER)
                    .build()
                    .parse(token);

            return true;
        } catch (SignatureException e) {
            throw new JwtTokenException("Invalid Jwt Signature");
        } catch (MalformedJwtException e) {
            throw new JwtTokenException("Invalid Jwt Token");
        } catch (ExpiredJwtException e) {
            throw new JwtTokenException("Expired Jwt Token");
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenException("Unsupported Jwt Token");
        } catch (IllegalArgumentException e) {
            throw new JwtTokenException("Jwt claims String Is Empty");
        } catch (IncorrectClaimException e) {
            throw new JwtTokenException("Invalid Jwt Claim");
        }
    }

    @Override
    public String getClaimTarget(String token, String target) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(target)
                .toString();
    }
}
