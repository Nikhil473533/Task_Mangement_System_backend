package com.practice.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.practice.demo.beans.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuditLogger {

	private static final Path FILE =
			Paths.get("task-audit-log.json");
	
	private final ObjectMapper mapper; 
	
	public AuditLogger(ObjectMapper mapper)
	{
		this.mapper = mapper;
	}
	public synchronized void log(String action, Task task) {
		try {
			  Map<String, Object> entry = new HashMap<>();
			  entry.put("action", action);
			  entry.put("taskId", task.getId());
			  entry.put("taskType", task.getClass().getSimpleName());
			  entry.put("timestamp", LocalDateTime.now());
			  
			  String json = mapper.writeValueAsString(entry);
			  
			  Files.writeString(
					  FILE,
					  json + System.lineSeparator(),
					  StandardOpenOption.CREATE,
					  StandardOpenOption.APPEND
					  );
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
