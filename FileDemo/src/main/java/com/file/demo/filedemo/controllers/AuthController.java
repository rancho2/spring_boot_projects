package com.file.demo.filedemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.demo.filedemo.entities.RefreshToken;
import com.file.demo.filedemo.entities.User;
import com.file.demo.filedemo.models.JwtRequest;
import com.file.demo.filedemo.models.JwtResponse;
import com.file.demo.filedemo.models.RefreshTokenRequest;
import com.file.demo.filedemo.security.JwtHelper;
import com.file.demo.filedemo.services.RefreshTokenService;
import com.file.demo.filedemo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserDetailsService userDetailsService;
	private Logger logger=LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
	{
		logger.info("Before authentication");
		this.doAuthenticate(request.getEmail(),request.getPassword());
		logger.info("Came here after authentication");
		logger.info("Email: {} ",request.getEmail());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		logger.info("Got user details");
		String token = this.jwtHelper.generateToken(userDetails);
		logger.info("token : {} ",token);
		logger.info("Username : {} ",userDetails.getUsername());
		RefreshToken refreshToken=this.refreshTokenService.createRefreshToken(request.getEmail());
		logger.info("refreshToken: {} ",refreshToken.getRefreshToken());
		JwtResponse jwtResponse=new JwtResponse(token,refreshToken.getRefreshToken(),userDetails.getUsername());
		return new ResponseEntity(jwtResponse,HttpStatus.OK);
	}
	private void doAuthenticate(String email,String password)
	{
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		}catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Invalid username or password !!");
		}
	}
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler()
	{
		return "Credentials Invalid !!";
	}
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user)
	{
		logger.info("password: {} ",user.getPassword());
		logger.info("email: {} ",user.getEmail());
		return userService.createUser(user);
	}
	@PostMapping("/refresh")
	public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request)
	{
		RefreshToken refreshToken=refreshTokenService.verifyRefreshToken(request.getRefreshToken());
		User user = refreshToken.getUser();
		String token = this.jwtHelper.generateToken(user);
		return new JwtResponse(token,refreshToken.getRefreshToken(),user.getEmail());
	}
}
