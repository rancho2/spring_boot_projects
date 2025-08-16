package com.file.demo.filedemo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtHelper {
	public static final long JWT_TOKEN_VALIDITY=60;
	private String secret="afafafafasafafafsAFAFAFAFAFAFabababababAFAFAFAFAFAFACSPQGHAFAFAFAFAFAFAFAFAFAFAFAFAFAFAFAFAFAFAFAF";
	//get username from token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getSubject);
	}
	public <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver)
	{
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	public Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	//check if the token has expired
	private boolean isTokenExpired(String token)
	{
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	//validate token
	public boolean validateToken(String token,UserDetails userDetails)
	{
		String userName=getUsernameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	//generate token for user
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims=new HashMap<String, Object>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	//while creating the token -
	//1.Define claims of the token, like Issuer, Expiration, Subject and the ID
	//2.Sign the jwt using the HS512 algorithm and secret key
	//3.According to jws compact serialization
	//compaction of jwt to url-safe string
	private String doGenerateToken(Map<String, Object> claims,String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).
				setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).
				signWith(SignatureAlgorithm.HS512,secret).compact();
	}
}
