package com.practice.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.practice.demo.beans.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom {

	 @PersistenceContext
	 private EntityManager em;
	
	
	 public List<Task> findActiveTasks() {
		 return em.createQuery("SELECT t FROM Task t WHERE t.deleted = false", Task.class).getResultList();
	 }
	 
	 public Optional<Task> findActiveTaskById(Long id){
		  List<Task> result = em.createQuery(
				    "SELECT t FROM Task t WHERE t.id = :id AND t.deleted = false",
				  Task.class).setParameter("id", id).getResultList();
		  
		  return result.stream().findFirst();
	 }
	 
	 public Task save(Task task) {
		  em.persist(task);
		  return task;
	 }
}
