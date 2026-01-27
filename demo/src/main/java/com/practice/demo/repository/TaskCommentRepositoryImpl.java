package com.practice.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.practice.demo.beans.Task;
import com.practice.demo.beans.TaskComment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class TaskCommentRepositoryImpl implements TaskCommentRepository{
 	
 @PersistenceContext	
 private EntityManager em;
 
 public TaskComment save(TaskComment taskComment) {
	    
	 em.persist(taskComment);
	 return taskComment;
 }

 public List<TaskComment> getAllTaskComments(){
	  
	return em.createQuery("SELECT tc FROM TaskComment tc", TaskComment.class).getResultList();
 }
 
 public List<TaskComment> getTaskCommentByTaskId(Long taskId) {
	return em.createQuery("SELECT tc FROM TaskComment tc "
			+ "WHERE tc.task.id = :taskId AND tc.deleted = false "
			+ "ORDER BY tc.createdAt ASC", TaskComment.class)
			  .setParameter("taskId", taskId).getResultList();
 }
 
 public Optional<TaskComment> findActiveCommentById(Long commentId) {
	 
	 return em.createQuery("SELECT tc FROM TaskComment tc "
	 		+"WHERE tc.id = :commentId AND tc.deleted = false", TaskComment.class)
			.setParameter("commentId", commentId)
			.getResultStream()
			.findFirst();
	 
 }
}
