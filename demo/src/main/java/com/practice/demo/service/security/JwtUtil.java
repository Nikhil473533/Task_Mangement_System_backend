package com.practice.demo.service.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.practice.demo.beans.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
	private final long expirationMs = 86400000;
	
  public JwtUtil(@Value("${jwt.secret}") String secret) {	
	  this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }
	
 public String generateToken(User user) {
	 return Jwts.builder()
			.setSubject(user.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
			.signWith(secretKey)
			.compact();
 }

 public String extractUsername(String token) {
	 return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
 }

}
