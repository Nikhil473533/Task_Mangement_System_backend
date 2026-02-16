package com.practice.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.practice.demo.beans.TaskStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TaskStatusRepositoryImpl implements TaskStatusRepository{
    
	@PersistenceContext
	private EntityManager em;
	
	public TaskStatusRepositoryImpl() {
		
	}
	
	public Optional<TaskStatus> findStatusByCode(String code)
	{
		return em.createQuery("SELECT ts FROM TaskStatus ts WHERE ts.code = :code",TaskStatus.class)
				 .setParameter("code", code)
				 .getResultList()
				 .stream()
				 .findFirst();
	}
	
}
