package com.practice.demo.beans;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
public abstract class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   private String title;
   private String description;
   
   private boolean deleted = false;
   
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="created_by_user_id", nullable=false)
   private User createdByUser;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="updated_by_user_id")
   private User updatedByUser;
   


   @PrePersist
   void onCreate() {
	     createdAt = LocalDateTime.now();
   }
   
   @PreUpdate
   void onUpdate() {
	   updatedAt = LocalDateTime.now();
   }

   public Task() {
	   
   }
   
   public Task(Long id, String title, String description, boolean deleted, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.deleted = deleted;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
   }

   public Long getId() {
	return id;
   }

   public void setId(Long id) {
	this.id = id;
   }

   public String getTitle() {
	return title;
   }

   public void setTitle(String title) {
	this.title = title;
   }

   public String getDescription() {
	return description;
   }

   public void setDescription(String description) {
	this.description = description;
   }

   public boolean isDeleted() {
	return deleted;
   }

   public void setDeleted(boolean deleted) {
	this.deleted = deleted;
   }

   public LocalDateTime getCreatedAt() {
	return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
   }

   public LocalDateTime getUpdatedAt() {
	return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
   }

   public User getCreatedByUser() {
	return createdByUser;
}

   public void setCreatedByUser(User createdByUser) {
	this.createdByUser = createdByUser;
   }

   public User getUpdatedByUser() {
	return updatedByUser;
   }

   public void setUpdatedByUser(User updatedByUser) {
	this.updatedByUser = updatedByUser;
   } 
   
   
}
