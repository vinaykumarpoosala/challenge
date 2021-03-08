package com.example.project.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.project.Model.ApplicationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	String secret;
	
	@Value("${jwt.token.validity}")
	long jwtExpirationInMs;
	public String generateToken(ApplicationUser user) {
		Claims claims= Jwts.claims();
       
        claims.put("email", user.getUser_email());
        claims.put("user_name", user.getUser_name());
        claims.setSubject("Hospital");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs));
        
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        
        return token;
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("Inavalid JWT Signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Inavlid JWT Token");
		} catch (ExpiredJwtException ex) {
			System.out.println("JWT Token expired");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported Jwt Token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT Claims string empty");
		}
		return false;
		
		//validate aithundi
	}

	public String getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		String id = (String) claims.get("user_name");
		return id;
	}
	
}