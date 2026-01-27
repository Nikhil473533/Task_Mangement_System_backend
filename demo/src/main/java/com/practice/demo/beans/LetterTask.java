package com.practice.demo.beans;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LETTER")
public class LetterTask extends Task{ 
	
	private String address;

	public LetterTask(Long id, String title, String description, boolean deleted, LocalDateTime createdAt,
			LocalDateTime updatedAt, String address) {
		super(id, title, description, deleted, createdAt, updatedAt);
	
	}
	
	public LetterTask() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return super.toString() +"LetterTask [address=" + address + "]";
	}
 
	
}
