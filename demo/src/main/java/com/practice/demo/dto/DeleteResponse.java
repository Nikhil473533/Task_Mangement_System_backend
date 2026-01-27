package com.practice.demo.dto;

import java.time.LocalDateTime;

public record DeleteResponse(
	      Long id,
	      String message,
	      LocalDateTime deletedAt
		
		) {}
