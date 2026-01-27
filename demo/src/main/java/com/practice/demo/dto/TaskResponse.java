package com.practice.demo.dto;


import java.time.LocalDateTime;

public record TaskResponse(
                            Long id,
                            String title,
                            String description,
                            LocalDateTime createdAt,
                            boolean     deleted,
                            LocalDateTime updatedAt
		                  ) {}
