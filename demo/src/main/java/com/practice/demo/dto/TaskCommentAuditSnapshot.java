package com.practice.demo.dto;

import java.time.LocalDateTime;

public record TaskCommentAuditSnapshot(
		    Long id,
		    Long taskId,
		    String commentText,
		    String createdBy,
		    LocalDateTime createdAt,
		    String updatedBy,
		    LocalDateTime updatedAt,
		    boolean deleted
		) {}
