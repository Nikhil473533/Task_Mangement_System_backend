package com.practice.demo.dto;

import java.time.LocalDateTime;

public record TaskCommentResponse(
		                          Long id,
		                          Long taskId,
		                          String commentText,
		                          String createdBy,
		                          LocalDateTime createdAt,
		                          String updatedBy,
		                          LocalDateTime updatedAt
		                          ) {}
