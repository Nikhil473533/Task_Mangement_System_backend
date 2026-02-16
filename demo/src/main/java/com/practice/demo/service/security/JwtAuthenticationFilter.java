package com.practice.demo.service.security;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.practice.demo.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailService;
	
	
public JwtAuthenticationFilter(
		JwtUtil jwtutil,
		CustomUserDetailsService userDetailService
		) {
	        this.jwtUtil = jwtutil;
	        this.userDetailService = userDetailService;
}


@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	
	String authHeader = request.getHeader("Authorization");
	
	if(authHeader != null && authHeader.startsWith("Bearer ")) {
		String token = authHeader.substring(7);
		String username = jwtUtil.extractUsername(token);
		
		UserDetails userDetails = 
				userDetailService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken auth =
			new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	filterChain.doFilter(request, response);
}

}
