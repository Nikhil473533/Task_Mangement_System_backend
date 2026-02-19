package com.practice.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.practice.demo.beans.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository{

@PersistenceContext
EntityManager em;

public Optional<User> getDefaultUser(String username) {
	
	return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
			             .setParameter("username", username)
			             .getResultList()
			             .stream()
			             .findFirst();
	
}

@Override
public Optional<User> findByUsername(String username) {
 
   return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles "
   		         + "WHERE u.username = :username", User.class)
                 .setParameter("username", username)
                 .getResultList()
                 .stream()
                 .findFirst();
}

}
