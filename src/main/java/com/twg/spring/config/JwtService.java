package com.twg.spring.config;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService
{
	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private Key getSignKey()
	{
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	 public <T> T extractClaim(String token, Function<Claims, T> resolver) 
	 {
	        return resolver.apply(extractAllClaims(token));
	  }
	
	public String extractEmail(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	
	public String generateToken(String email) 
	{
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String email) 
    {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) 
    {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
	
	private Claims extractAllClaims(String token) 
	{
	     return Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	 }
}

/* Another way
 package com.twg.library.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Get email (subject) from token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Create token with email as subject
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Check if token matches email and is not expired
    public boolean isTokenValid(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Common method to parse all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

 */
