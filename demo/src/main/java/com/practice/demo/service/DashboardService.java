package com.practice.demo.service;

import java.util.List;
import com.practice.demo.dto.StatusCountDTO;
import com.practice.demo.dto.TaskResponse;

public interface DashboardService {

	public long getCountOfAllActiveTasks();
	public List<StatusCountDTO> getStatusWiseTaskCount();
	public List<TaskResponse> getMyActiveTasks();
}
