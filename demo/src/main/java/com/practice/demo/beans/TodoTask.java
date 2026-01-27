package com.practice.demo.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TODO")
public class TodoTask extends Task{

	  private LocalDate dueDate;
	
	public TodoTask(Long id, String title, String description, boolean deleted, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDate dueDate) {
		super(id, title, description, deleted, createdAt, updatedAt);
	
	}
	
	public TodoTask() {
		super();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return super.toString() +"TodoTask [dueDate=" + dueDate + "]";
	}
}
