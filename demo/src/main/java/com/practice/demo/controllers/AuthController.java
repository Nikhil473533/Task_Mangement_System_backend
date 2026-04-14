package com.practice.demo.controllers;

import com.practice.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestParam String username,
                      @RequestParam String password,
                      HttpServletResponse response) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        String accessToken = jwtUtil.generateAccessToken(user);

        Cookie cookie = new Cookie("token", accessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true in production
        cookie.setPath("/");
        cookie.setMaxAge(15 * 60);

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

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String username)
    {
        return userService.createResetToken(username);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword)
    {
       return userService.resetPassword(token, newPassword);
    }

}
