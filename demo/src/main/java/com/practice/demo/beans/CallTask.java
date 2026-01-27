package com.practice.demo.beans;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CALL")
public class CallTask extends Task{
  
	public CallTask(Long id, String title, String description, boolean deleted, LocalDateTime createdAt,
			LocalDateTime updatedAt, String phoneNumber, String durationMinutes) {
		super(id, title, description, deleted, createdAt, updatedAt);
		
	} 
	
	public CallTask() {
		super();
	}
	
	private String phoneNumber;
	private Integer durationMinutes;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	@Override
	public String toString() {
		return super.toString() +", CallTask [phoneNumber=" + phoneNumber + ", durationMinutes=" + durationMinutes + "]";
	}
		
}
