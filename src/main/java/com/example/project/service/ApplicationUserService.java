package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;

@Service
public class ApplicationUserService {

	@Autowired
	ApplicationUserRepository repo;

	@Autowired
	JwtUtil jwtutils;

	public ResponseEntity<?> save(ApplicationUser user) {
		Map<String, String> map = new HashMap<>();
		try {
			repo.save(user);
		} catch (Exception e) {

			map.put("message", "Password or username policy failed");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		}
		map.put("message", "Registration successful");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	public ResponseEntity<?> login(String user_name) {
		ApplicationUser user = repo.findById(user_name).get();
		String token = jwtutils.generateToken(user);
		token = "Bearer " + token;
		System.out.println("token: " + token);
		Map<String, String> map = new HashMap<>();
		map.put("message", "Registration successful");
		map.put("token", token);
		map.put("id", user.getUser_name());
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	public ResponseEntity<?> getUser(String userId) {

		Optional<ApplicationUser> obj = repo.findById(userId);
		if (obj.isPresent() && obj != null) {

			return new ResponseEntity<ApplicationUser>(obj.get(), HttpStatus.OK);
		}

		Map<String, String> map = new HashMap<>();
		map.put("message", "User with id " + userId + " not present");

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> update(ApplicationUser user, String userId) {
		Optional<ApplicationUser> obj = repo.findById(userId);
		if (obj.isPresent() && obj != null) {
			ApplicationUser userD = obj.get();
			userD.setLocation(user.getLocation());
			userD.setUser_email(user.getUser_email());
			userD.setUser_mobile(user.getUser_mobile());
			repo.save(userD);
			return new ResponseEntity<ApplicationUser>(userD, HttpStatus.OK);
		}

		Map<String, String> map = new HashMap<>();
		map.put("message", "User with id " + userId + " not present");

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

}
