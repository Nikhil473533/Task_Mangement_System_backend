package com.practice.demo.repository;

import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import com.practice.demo.dto.FilterCriteria;



@Repository
public class AuditLogForDisplayImpl implements AuditLogForDisplay{

private final JdbcTemplate jdbcTemplate;

private SimpleJdbcCall simpleJdbcCall;
	
	public AuditLogForDisplayImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Map<String, Object> getAuditLogs(String selectColumns, String sortColumn,
            String sortDirection,List<FilterCriteria> filters, String searchTerm, String startDateSearchTerm, String endDateSearchTerm ,int pageNumber, int pageSize) {
		 selectColumns = (selectColumns == null || selectColumns.trim().isEmpty()) ? "*" : selectColumns;
	        //searchColumn = (searchColumn == null || searchColumn.trim().isEmpty()) ? null : searchColumn;
	        sortColumn = (sortColumn == null || sortColumn.trim().isEmpty()) ? "ReceivedDate" : sortColumn;
	        sortDirection = (sortDirection == null || !(sortDirection.equalsIgnoreCase("ASC") || sortDirection.equalsIgnoreCase("DESC")))
	                ? "DESC"
	                : sortDirection;
	        searchTerm = (searchTerm == null) ? "" : searchTerm;
	        startDateSearchTerm = (startDateSearchTerm == null) ? "" : startDateSearchTerm;
	        endDateSearchTerm = (endDateSearchTerm == null) ? "" : endDateSearchTerm;
	        pageNumber = (pageNumber <= 0) ? 1 : pageNumber;
	        pageSize = (pageSize <= 0) ? 20 : pageSize;
		
	     // Convert filters to SQL condition string, skipping empty values
	        String filterCondition = buildFilterCondition(filters);
	        
	        //Prepare JDBC Call
	        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
	        		.withSchemaName("dbo")
	        		.withProcedureName("up_audit_log_details")
	                .declareParameters(
	                new SqlOutParameter("TotalCount", Types.INTEGER), 
	                new SqlOutParameter("AbsoluteCount", Types.INTEGER)
	                );
 
	      //Prepare input Parameters
	      Map<String, Object> inParams = new HashMap<>();
	      
	        inParams.put("SelectColumns", selectColumns);
	        inParams.put("SearchTerm", searchTerm);
	        inParams.put("SortColumn", sortColumn);
	        inParams.put("SortOrder", sortDirection);
	        inParams.put("Filters", filterCondition);
	        inParams.put("PageNumber", pageNumber);
	        inParams.put("PageSize", pageSize);
	        inParams.put("StartDateSearchTerm", startDateSearchTerm);
	        inParams.put("EndDateSearchTerm", endDateSearchTerm);
	        
	        //Execute stored procedure
	        SqlParameterSource in = new MapSqlParameterSource(inParams);
	        Map<String, Object> result = simpleJdbcCall.execute(in);
	        
		return result;
	}
    
    private String buildFilterCondition(List<FilterCriteria> filters) {
        if (filters == null || filters.isEmpty()) {
            return "";  // No filter condition
        }

        StringBuilder filterBuilder = new StringBuilder();
        boolean firstValidFilter = true;

        for (FilterCriteria filter : filters) {
            String value = filter.getValue() != null ? filter.getValue() : "";
            if (value.trim().isEmpty()) {
                continue;
            }
            if (!firstValidFilter) {
                filterBuilder.append(" ").append(filter.getOperator().toUpperCase()).append(" ");
            }
            firstValidFilter = false;

            String field = filter.getField();

                filterBuilder.append("[").append(field).append("] ");
                appendMatchMode(filterBuilder, filter.getMatchMode(), value);
        }

        return filterBuilder.toString();
    }
    
    // Helper method to append match mode conditions
    private void appendMatchMode(StringBuilder builder, String matchMode, String value) {
        // Escape single quotes to prevent SQL injection
        value = value.replace("'", "''");

        if ("dateis".equalsIgnoreCase(matchMode) || "dateisnot".equalsIgnoreCase(matchMode) ||
            "datebefore".equalsIgnoreCase(matchMode) || "dateafter".equalsIgnoreCase(matchMode)) {
            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate date = LocalDate.parse(value, inputFormatter);
                value = date.format(outputFormatter);
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format for value: " + value);
            }
        }

        switch (matchMode != null ? matchMode.toLowerCase() : "contains") {
            case "startswith" -> builder.append("LIKE '").append(value).append("%'");
            case "endswith" -> builder.append("LIKE '%").append(value).append("'");
            case "contains" -> builder.append("LIKE '%").append(value).append("%'");
            case "equals" -> builder.append("= '").append(value).append("'");
            case "notcontains" -> builder.append("NOT LIKE '%").append(value).append("%'");
            case "notequals" -> builder.append("!= '").append(value).append("'");
            case "dateis" -> builder.append("= '").append(value).append("'");
            case "dateisnot" -> builder.append("!= '").append(value).append("'");
            case "datebefore" -> builder.append("< '").append(value).append("'");
            case "dateafter" -> builder.append("> '").append(value).append("'");
            default -> builder.append("LIKE '%").append(value).append("%'");
        }
    }


	
}
