package com.practice.demo.beans;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name="task_comment")
public class TaskComment {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
			name = "task_id",
			nullable = false,
			foreignKey = @ForeignKey(name = "fk_task_comment_task")
			)
	private Task task;
	
	@Column(name="comment_text", nullable=false, length = 1000)
	private String commentText;
	
	@Column(name="created_by", nullable=false, length = 30)
	private String createdBy;
	
	@Column(name="created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name="updated_by", length = 50)
	private String updatedBy;
	
	@Column(name="deleted", nullable = false)
	private boolean deleted;
	
  public TaskComment() {
	  
  }

  public TaskComment(Long id, Task task, String commentText, String createdBy, LocalDateTime createdAt,
		LocalDateTime updatedAt, String updatedBy, boolean deleted) {
	super();
	this.id = id;
	this.task = task;
	this.commentText = commentText;
	this.createdBy = createdBy;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.updatedBy = updatedBy;
	this.deleted = deleted;
  }

  
  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public Task getTask() {
	return task;
  }

  public void setTask(Task task) {
	this.task = task;
  }

  public String getCommentText() {
	return commentText;
  }

  public void setCommentText(String commentText) {
	this.commentText = commentText;
  }

  public String getCreatedBy() {
	return createdBy;
  }

  public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
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

  public String getUpdatedBy() {
	return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
  }

  public boolean isDeleted() {
	return deleted;
  }

  public void setDeleted(boolean deleted) {
	this.deleted = deleted;
  }

  
}
