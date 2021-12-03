package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.UserInfo;
import com.olx.entity.JsonDocument;
import com.olx.repository.JwtRepository;
import com.olx.security.JwtUtil;
import com.olx.service.UserInfoService;

@RestController
@RefreshScope
@RequestMapping("/olx/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	@Autowired
	JwtRepository jwtRepository;
	
	
	@Value("${spring.datasource.url}")
	String dbUrl;
	
	@GetMapping(value = "/db")
	public String getDbUrl() {
		return "DATABASE URL : " + dbUrl;
	}
	
	@Autowired
	UserInfoService userInfoService; 
	
	@PostMapping(value = "/",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> createNewUser(@RequestBody UserInfo userInfo){
		return new ResponseEntity<UserInfo>(this.userInfoService.createNewUser(userInfo),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<UserInfo> getUserDetails(@RequestHeader("Authorization") String token){
	
       String jwtToken = token.substring(7,token.length());
		String clientUsername = jwtUtil.extractUsername(jwtToken);
		String databaseUsername = userDetailsService.loadUserByUsername(clientUsername).getUsername();
		boolean result=jwtUtil.validateTokenByUsername(jwtToken, databaseUsername);
		UserInfo userInfo = this.userInfoService.findUsername(databaseUsername);
	   return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
		
	}
	
	
	
	
	@GetMapping(value = "/validate/token")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token){
		String jwtToken = token.substring(7,token.length());
		
		String clientUsername = jwtUtil.extractUsername(jwtToken);
		String databaseUsername = userDetailsService.loadUserByUsername(clientUsername).getUsername();	
		
		boolean result=jwtUtil.validateTokenByUsername(jwtToken, databaseUsername);
		if(result) {
			JsonDocument jsonDocument = jwtRepository.findByJwtTokenEquals(jwtToken);
			if(jsonDocument !=null) {
				return new ResponseEntity(false,HttpStatus.BAD_REQUEST);  //logged out token
			}
			else {
				return new ResponseEntity(true,HttpStatus.OK);
			}
			
		}
		else {
			return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@PostMapping(value ="/authenticate" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			
			authenticationManager.authenticate
			       (new UsernamePasswordAuthenticationToken(
			    		   authenticationRequest.getUsername(), 
			    		   authenticationRequest.getPassword()));
			
		}
		catch(BadCredentialsException ex) {
			return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
		String jwtToken = jwtUtil.generateTokenByUsername(authenticationRequest.getUsername());		
		return new ResponseEntity(jwtToken,HttpStatus.OK);
		
	}

	
	
	@DeleteMapping(value = "/logout")
	public ResponseEntity<String> logOut(@RequestHeader("Authorization") String token){
		String jwtToken = token.substring(7,token.length());
		JsonDocument jsonDocument = new JsonDocument();
		jsonDocument.setJwtToken(jwtToken);
		jwtRepository.save(jsonDocument);
		return new ResponseEntity<String>("Logged Out Succesfully!!",HttpStatus.OK);
	}
	
	

}
