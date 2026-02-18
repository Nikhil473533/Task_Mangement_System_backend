package com.practice.demo.repository;

import java.util.List;
import com.practice.demo.beans.Task;
import com.practice.demo.dto.StatusCountDTO;

public interface DashboardRepository {

	 public long getCountOfAllActiveTasks();
	 public List<StatusCountDTO> getStatusWiseTaskCount();
	 public List<Task> getMyActiveTasks(Long userId);
}
