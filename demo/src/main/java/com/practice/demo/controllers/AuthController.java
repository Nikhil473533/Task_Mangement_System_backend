package com.practice.demo.controllers;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.practice.demo.beans.User;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	
	public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public Map<String,String> login(@RequestParam String username){
		
		User user = userRepository.findByUsername(username)
				    .orElseThrow();
		
		String token = jwtUtil.generateToken(user);
		return Map.of("token", token);
	}
	
}
