package com.practice.demo.dto;

import java.time.LocalDateTime;

public record TaskAuditSnapshot(
		  Long id,
		  String title,
		  String description,
		  boolean deleted,
		  LocalDateTime createdAt,
		  LocalDateTime updatedAt
		  
		) {}
