package com.practice.demo.repository;

import java.util.Optional;

import com.practice.demo.beans.User;

public interface UserRepository {
	public Optional<User> getDefaultUser(String username);
	public Optional<User> findByUsername(String username);
}
