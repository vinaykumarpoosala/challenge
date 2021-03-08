package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController("/")
public class ApplicationUserController {

	@Autowired
	private ApplicationUserService service;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody ApplicationUser applicationUser) {

		return service.save(applicationUser);
	}
	@PostMapping("signin")
	public ResponseEntity<?> login(@RequestBody ApplicationUser applicationUser){
		
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(applicationUser.getUser_name(),applicationUser.getPassword())
				);
		}catch(Exception e) {
			Map<String,String> map = new HashMap<>();
			map.put("message", "Username or Password is incorrect");
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		}
		
		return service.login(applicationUser.getUser_name());
		
	}

	@GetMapping("viewprofile/{userid}")
	public ResponseEntity<?> getUserByUserid(@PathVariable String userId){
		return service.getUser(userId);
	}
}
