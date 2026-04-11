package com.practice.demo.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.dto.FilterCriteria;
import com.practice.demo.service.AuditLogService;

@RestController
@RequestMapping("/api/auditLog")
public class AuditLogController {

	private final AuditLogService auditLogService;
	private final ObjectMapper objectMapper;
	
	public AuditLogController(AuditLogService auditLogService, ObjectMapper objectMapper) {
		this.auditLogService = auditLogService;
		this.objectMapper = objectMapper;
	}
	
	 @GetMapping("/getAll")
	    public Map<String, Object> getAuditLogs(
	            @RequestParam(required = false) String selectColumns,
	            @RequestParam(required = false) String sortColumn,
	            @RequestParam(required = false) String sortOrder,
	            @RequestParam(required = false) String searchTerm,
	            @RequestParam(required = false) String startDateSearchTerm,
	            @RequestParam(required = false) String endDateSearchTerm,
	            @RequestParam(required = false) String userId,
	            @RequestParam(required = false) String filters,
	            @RequestParam(defaultValue = "1") int pageNumber,
	            @RequestParam(defaultValue = "10") int pageSize
	    ) {

	        List<FilterCriteria> filterList = null;

	        try {
	            if (filters != null && !filters.isEmpty()) {
	                filterList = objectMapper.readValue(
	                        filters,
	                        new TypeReference<List<FilterCriteria>>() {}
	                );
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Invalid filter format", e);
	        }

	        return auditLogService.getAllRecords(
	                selectColumns,
	                sortColumn,
	                sortOrder,
	                filterList,
	                searchTerm,
	                startDateSearchTerm,
	                endDateSearchTerm,
	                pageNumber,
	                pageSize
	        );
	    }

}
