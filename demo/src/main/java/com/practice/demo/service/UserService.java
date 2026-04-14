package com.practice.demo.service;

public interface UserService {

    public String createResetToken(String username);
    public String resetPassword(String token, String username);
}
