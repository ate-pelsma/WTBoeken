package com.workingtalent.library.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.User;

@Component
public class JwtUtils implements Serializable{

    private static final String SECRET_KEY = "5468576D5A7134743777217A25432A462D4A404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970337336763979244226452948404D";
    private static final int EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    public String generateToken(UserDetails userDetails) {
    	Map<String, Object> claims = new HashMap<>();
    	return doGenerateToken(claims, userDetails.getUsername());
    }
    
    @SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String subject) {
    	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
    			.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
    
    public String getUsernameFromToken(String token) 
    {
    	return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
    	return getClaimFromToken(token, Claims::getIssuedAt);
    }
    
    public Date getExpirationDateFromToken(String token) {
    	return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    	final Claims claims = getAllClaimsFromToken(token);
    	return claimsResolver.apply(claims);
    }
    
	@SuppressWarnings("deprecation")
	private Claims getAllClaimsFromToken(String token) {
    	return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	private Boolean ignoreTokenExpiration(String token) {
		return false;
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}
	
	//check if error
	public Boolean validateToken(String token, User userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getEmail()) && !isTokenExpired(token);
	}

}