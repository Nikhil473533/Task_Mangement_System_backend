package com.practice.demo.beans;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="audit_log")
public class AuditLog {
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String entityName;
	private Long entityId;
	private String action;
	
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String beforeState;
	
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String afterState;
	
	private LocalDateTime eventTime;
	
	private String auditVersion; 
	
	public AuditLog() {
		
	}
	
	public AuditLog(long id, String entityName, Long entityId, String action, String beforeState, String afterState,
			LocalDateTime eventTime, String auditVersion) {
		super();
		this.id = id;
		this.entityName = entityName;
		this.entityId = entityId;
		this.action = action;
		this.beforeState = beforeState;
		this.afterState = afterState;
		this.eventTime = eventTime;
		this.auditVersion = auditVersion;
	}


	public String getAuditVersion() {
		return auditVersion;
	}

	public void setAuditVersion(String auditVersion) {
		this.auditVersion = auditVersion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBeforeState() {
		return beforeState;
	}

	public void setBeforeState(String beforeState) {
		this.beforeState = beforeState;
	}

	public String getAfterState() {
		return afterState;
	}

	public void setAfterState(String afterState) {
		this.afterState = afterState;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}
}
