package com.practice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.practice.demo.beans.User;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
	public void login(@RequestParam String username, HttpServletResponse response){
		
		User user = userRepository.findByUsername(username)
				    .orElseThrow();
		
		String token = jwtUtil.generateToken(user);

		Cookie cookie = new Cookie("token", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(24*60*60);
		
		response.addCookie(cookie);
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	}
	
	@GetMapping("/check")
	public void check() {}
}
