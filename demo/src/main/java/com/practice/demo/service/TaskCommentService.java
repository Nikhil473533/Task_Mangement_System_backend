package com.practice.demo.service;

import com.practice.demo.beans.TaskComment;
import com.practice.demo.dto.TaskCommentResponse;

public interface TaskCommentService {
  
	public TaskCommentResponse updateSingleTaskComment(Long commentId, String updatedComment);
	public String softDelete(Long commentId);
	public TaskComment save(TaskComment taskComment);
	
}
