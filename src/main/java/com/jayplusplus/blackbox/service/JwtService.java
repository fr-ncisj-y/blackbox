package com.jayplusplus.blackbox.service;

import com.jayplusplus.blackbox.model.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The type Jwt service.
 */
@Component
public class JwtService {

    private static final String SECRET = "F69B3CFCC1722D5DAA98628C9C315F69B3CFCC1722D5DAA98628C9C315";

    /**
     * Generate token login response.
     *
     * @param username the username
     * @return the login response
     */
    public LoginResponse generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        String authToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        Date expiration = extractExpiration(authToken);
        long ms = expiration.getTime();
        return new LoginResponse(00,"Successful","Bearer",authToken, ms);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extract user name string.
     *
     * @param token the token
     * @return the string
     */
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    /**
     * Extract expiration date.
     *
     * @param token the token
     * @return the date
     */
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim (String token, Function<Claims,T> claimResolver){
        final Claims claims = extraclAllClaims(token);
        return claimResolver.apply(claims);
    }
    private Claims extraclAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate token boolean.
     *
     * @param token       the token
     * @param userDetails the user details
     * @return the boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
