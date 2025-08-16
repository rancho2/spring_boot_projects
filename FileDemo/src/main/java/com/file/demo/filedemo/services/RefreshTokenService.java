package com.file.demo.filedemo.services;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.demo.filedemo.entities.RefreshToken;
import com.file.demo.filedemo.entities.User;
import com.file.demo.filedemo.repositories.RefreshTokenRepository;
import com.file.demo.filedemo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {
	private Logger logger=LoggerFactory.getLogger(RefreshTokenService.class);
	private long refreshTokenValidity=2*60*1000;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	public RefreshToken createRefreshToken(String userName)
	{
		User user=userRepository.findByEmail(userName).get();
		RefreshToken refreshToken = user.getRefreshToken();
		if(refreshToken==null)
		{
			refreshToken=new RefreshToken(UUID.randomUUID().toString(),UUID.randomUUID().toString(),Instant.now().plusMillis(refreshTokenValidity),user);
		    refreshTokenRepository.save(refreshToken);
		}
		else
		{
			refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
		user.setRefreshToken(refreshToken);
		logger.info("Before saving to db");
		refreshTokenRepository.save(refreshToken);
		logger.info("After saving to db");
		return refreshToken;
	}
	@Transactional
	public RefreshToken verifyRefreshToken(String refreshToken)
	{
		RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()->new RuntimeException("Given token does not exist in db"));
	    if(refreshTokenOb.getExpiry().compareTo(Instant.now())<0)
	    {
	    	refreshTokenRepository.delete(refreshTokenOb);
	    	throw new RuntimeException("Refresh token expired");
	    }
	    return refreshTokenOb;
	}
}
