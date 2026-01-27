package com.practice.demo.repository;

import org.springframework.stereotype.Repository;

import com.practice.demo.beans.AuditLog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AuditRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(AuditLog log) {
		em.persist(log);
	}
	
}
