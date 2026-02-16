package com.practice.demo.repository;

import java.util.Optional;

import com.practice.demo.beans.TaskStatus;

public interface TaskStatusRepository {
	public Optional<TaskStatus> findStatusByCode(String code);
}
