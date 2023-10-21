package com.yuri.projects.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yuri.projects.auth.details.UserDetailsData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private String secret;

    public JwtTokenService(@Value("${auth.secret}") final String secret) {
        this.secret = secret;
    }

    public String generateToken(final UserDetailsData userDetailsData) {
        final String token = JWT.create()
                .withSubject(userDetailsData.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(secret));

        final String finalToken = "Bearer " + token;

        return finalToken;
    }

    public String validateToken(final String token) {
        final Algorithm algotirithm = Algorithm.HMAC256(secret);
        final JWTVerifier verifier = JWT.require(algotirithm).build();
        final DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }

}
