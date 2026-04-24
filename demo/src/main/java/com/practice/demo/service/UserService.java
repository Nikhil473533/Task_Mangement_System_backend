package com.practice.demo.service;

import java.util.Map;

public interface UserService {

    public Map<String, String> createResetToken(String username);
    public Map<String, String> resetPassword(String token, String username);
}
