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

        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipal)) {
            throw new IllegalStateException("No authenticated user found");
        }

        return ((UserPrincipal) auth.getPrincipal()).getUser();
    }
}
