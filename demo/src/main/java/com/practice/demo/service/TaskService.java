package com.practice.demo.service;

import java.util.List;
import java.util.Optional;

import com.practice.demo.beans.Task;
import com.practice.demo.dto.TaskRequest;
import com.practice.demo.dto.TaskResponse;

public interface TaskService {

	public Task create(Task task);
	public TaskResponse update(Long id, TaskRequest req);
	public void softDelete(Long id);
	public List<TaskResponse> read();
	public Optional<TaskResponse>findById(Long id);
}
