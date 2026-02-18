package com.practice.demo.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.demo.dto.StatusCountDTO;
import com.practice.demo.dto.TaskResponse;
import com.practice.demo.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;
	
	public DashboardController(DashboardService dashboardService) {
		
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/summary/count")
	public long getCountOfAllActiveTasks() {
		
		return dashboardService.getCountOfAllActiveTasks();
	}
    
	@GetMapping("/summary/status")
	public List<StatusCountDTO> getStatusWiseTaskCount(){
		
		return dashboardService.getStatusWiseTaskCount();
	}
	
	@GetMapping("/tasks/my")
	public List<TaskResponse> getMyActiveTasks(){
		
		return dashboardService.getMyActiveTasks();
	}
}
