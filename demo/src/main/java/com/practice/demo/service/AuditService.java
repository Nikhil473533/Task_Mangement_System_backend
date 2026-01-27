package com.practice.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.beans.AuditLog;
import com.practice.demo.repository.AuditRepository;

@Service
public class AuditService {

	private final AuditRepository auditRepository;
	private final ObjectMapper objectMapper;
	
	public AuditService(AuditRepository auditRepository, ObjectMapper objectMapper) {
		
		this.auditRepository = auditRepository;
		this.objectMapper = objectMapper;
	}
	
	public void log(
			   String entityName,
			   Long entityId,
			   String action,
			   Object before,
			   Object after,
			   String version
			) {
		try {
		AuditLog auditLog = new AuditLog();
		
		auditLog.setEntityName(entityName);
		auditLog.setAction(action);
		auditLog.setEntityId(entityId);
	    auditLog.setEventTime(LocalDateTime.now());
		auditLog.setBeforeState(before == null ? null : objectMapper.writeValueAsString(before));
		auditLog.setAfterState(after == null ? null : objectMapper.writeValueAsString(after));
		auditLog.setAuditVersion(version);
		
		auditRepository.save(auditLog);
		} catch(JsonProcessingException e)
		{
			throw new RuntimeException("Failed to write audit log",e);
		}
	}
}
