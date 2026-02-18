package com.practice.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.practice.demo.beans.Task;
import com.practice.demo.beans.User;
import com.practice.demo.dto.StatusCountDTO;
import com.practice.demo.dto.TaskResponse;
import com.practice.demo.repository.DashboardRepository;
import com.practice.demo.service.security.CurrentUserProvider;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService{

	private final DashboardRepository dashboardRepository;
	private final CurrentUserProvider currentUserProvider;
	
	public DashboardServiceImpl(DashboardRepository dashboardRepository, CurrentUserProvider currentUserProvider) {
		this.dashboardRepository = dashboardRepository;
		this.currentUserProvider = currentUserProvider;
	}
    
	@Override
	public long getCountOfAllActiveTasks() {
	 return  dashboardRepository.getCountOfAllActiveTasks();
	
	 
	}
	
	@Override
	public List<StatusCountDTO> getStatusWiseTaskCount() {
		
		return dashboardRepository.getStatusWiseTaskCount();
	}
	
	@Override
	public List<TaskResponse> getMyActiveTasks(){
		
		return dashboardRepository.getMyActiveTasks(getCurrentUser().getId())
		                              .stream()
		                              .map(this::toResponse)
		                              .toList();
	}
	
	private TaskResponse toResponse(Task task) {
		
		return new TaskResponse(
				              task.getId(),
				              task.getTitle(),
				              task.getDescription(),
				              task.getCreatedAt(),
				              task.isDeleted(),
				              task.getTaskStatus().getDisplayName(),
				              task.getUpdatedAt()
				               );
	}
	
	private User getCurrentUser() {
		  return currentUserProvider.getCurrentUser();
		}	
}
