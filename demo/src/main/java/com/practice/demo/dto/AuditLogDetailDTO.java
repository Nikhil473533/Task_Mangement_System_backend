package com.practice.demo.dto;

import java.time.LocalDateTime;

public record AuditLogDetailDTO(
		             Long id,
		             Long entityId,
		             String entityName,
		             String action,
		             LocalDateTime eventTime,
		             String oldValue,
		             String newValue	           
		                 ) {}

