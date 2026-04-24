package com.practice.demo.service;

import com.practice.demo.beans.PasswordResetToken;
import com.practice.demo.beans.User;
import com.practice.demo.repository.PasswordResetTokenRepository;
import com.practice.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordResetTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map<String, String> createResetToken(String username) {

        User user = userRepository.findByUsername(username).orElseThrow();

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUserId(user.getId());
        resetToken.setExpiry(LocalDateTime.now().plusMinutes(15));

        tokenRepository.save(resetToken);

        return Map.of("token", token);
    }

    @Override
    public Map<String, String> resetPassword(String token, String newPassword) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElseThrow();

        if(resetToken.getExpiry().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token Expired");
        }

        User user = userRepository.findByUserId(resetToken.getUserId()).orElseThrow();

        user.setPassword(passwordEncoder.encode(newPassword));

       userRepository.save(user);

       return Map.of("message", "Password has been reset successfully");
    }
}
