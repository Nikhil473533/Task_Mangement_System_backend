package com.practice.demo.repository;

import java.util.List;
import java.util.Map;

import com.practice.demo.dto.FilterCriteria;



public interface AuditLogForDisplay {
	
    public Map<String, Object> getAuditLogs(String selectColumns, String sortColumn,
            String sortDirection,List<FilterCriteria> filters, String searchTerm, String startDateSearchTerm, String endDateSearchTerm ,int pageNumber, int pageSize);
}
