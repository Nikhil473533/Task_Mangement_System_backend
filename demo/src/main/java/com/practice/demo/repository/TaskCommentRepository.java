package com.practice.demo.repository;

import java.util.List;
import java.util.Optional;

import com.practice.demo.beans.TaskComment;

public interface TaskCommentRepository {

	public TaskComment save(TaskComment taskComment);
	public List<TaskComment> getTaskCommentByTaskId(Long taskId);
	Optional<TaskComment> findActiveCommentById(Long commentId);
}
