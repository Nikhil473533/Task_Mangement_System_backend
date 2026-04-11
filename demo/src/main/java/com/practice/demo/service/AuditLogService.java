package com.practice.demo.service;

import java.util.List;
import java.util.Map;
import com.practice.demo.dto.FilterCriteria;

public interface AuditLogService {
	public Map<String, Object> getAllRecords(String selectColumns, String sortColumn,
            String sortDirection, List<FilterCriteria> filters, String searchTerm, String startDateSearchTerm,
            String endDateSearchTerm,int pageNumber, int pageSize);
}
