package com.backend.la.backendloveafrica.utils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.la.backendloveafrica.Exception.JwtTokenExpiredException;

@Component
public class JwtUtils {

  @Value("${security.jwt.private.key}")
  private String privateKey;

  @Value("${security.jwt.user.generator}")
  private String userGenerator;

  public String createToken(Authentication authentication) {

    Algorithm algorithm = Algorithm.HMAC256(privateKey);

    String username = authentication.getPrincipal().toString();

    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    String jwtToken = JWT.create()
        .withIssuer(this.userGenerator)
        .withSubject(username)
        .withClaim("authorities", authorities)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + (30 * 60000)))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis()))
        .sign(algorithm);

    return jwtToken;
  }

  public DecodedJWT validateToken(String token) {

    try {
      Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

      JWTVerifier jwtVerifier = JWT.require(algorithm)
          .withIssuer(this.userGenerator)
          .build();

      DecodedJWT decodedJWT = jwtVerifier.verify(token);

      return decodedJWT;

    } catch (TokenExpiredException e) {
      throw new JwtTokenExpiredException("expired_token");
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException("invalid_token");
    }
  }

  public String extractUsername(DecodedJWT decodedJWT) {
    return decodedJWT.getSubject().toString();
  }

  public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
    return decodedJWT.getClaim(claimName);
  }

  public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT) {
    return decodedJWT.getClaims();
  }

}
