package com.practice.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.practice.demo.dto.FilterCriteria;
import com.practice.demo.repository.AuditLogForDisplay;


@Service
@Transactional
public class AuditLogServiceImpl implements AuditLogService {

	private final AuditLogForDisplay auditLogForDisplay;
	
	public AuditLogServiceImpl(AuditLogForDisplay auditLogForDisplay) {
		 this.auditLogForDisplay = auditLogForDisplay;
	}
   
	public Map<String, Object> getAllRecords(String selectColumns, String sortColumn,
            String sortDirection, List<FilterCriteria> filters, String searchTerm, String startDateSearchTerm,
            String endDateSearchTerm,int pageNumber, int pageSize) {
		return auditLogForDisplay.getAuditLogs(selectColumns, sortColumn,
                sortDirection, filters, searchTerm, startDateSearchTerm,endDateSearchTerm, pageNumber, pageSize);

	}	
}
