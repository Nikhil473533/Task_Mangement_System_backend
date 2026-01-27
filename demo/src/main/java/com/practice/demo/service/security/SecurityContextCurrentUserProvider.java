package com.practice.demo.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.practice.demo.beans.User;

@Component
public class SecurityContextCurrentUserProvider implements CurrentUserProvider {


	@Override
	public User getCurrentUser() {
		
		Authentication auth =
				 SecurityContextHolder.getContext().getAuthentication();
		
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		
		return principal.getUser();
	}

}
