package com.practice.demo.repository;

import java.util.List;
import java.util.Optional;

import com.practice.demo.beans.Task;

public interface TaskRepositoryCustom {

	List<Task> findActiveTasks();
	Optional<Task> findActiveTaskById(Long id);
	Task save(Task task);
}
