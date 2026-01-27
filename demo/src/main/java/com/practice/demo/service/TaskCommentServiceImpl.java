package com.practice.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.practice.demo.beans.Task;
import com.practice.demo.beans.TaskComment;
import com.practice.demo.beans.User;
import com.practice.demo.dto.TaskCommentAuditSnapshot;
import com.practice.demo.dto.TaskCommentRequest;
import com.practice.demo.dto.TaskCommentResponse;
import com.practice.demo.repository.TaskCommentRepositoryImpl;
import com.practice.demo.repository.TaskRepositoryImpl;
import com.practice.demo.service.security.CurrentUserProvider;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskCommentServiceImpl implements TaskCommentService{

    private final TaskCommentRepositoryImpl taskCommentRepositoryImpl;
    private final AuditService auditService;
    private final TaskRepositoryImpl taskRepositoryImpl;
    private final CurrentUserProvider currentUserProvider;
    
    public TaskCommentServiceImpl(TaskCommentRepositoryImpl taskCommentRepositoryImpl, AuditService auditService, TaskRepositoryImpl taskRepositoryImpl, CurrentUserProvider currentUserProvider) {
    	
    	this.taskCommentRepositoryImpl = taskCommentRepositoryImpl;
    	this.auditService = auditService;
    	this.taskRepositoryImpl = taskRepositoryImpl;
    	this.currentUserProvider = currentUserProvider;
    }
    
	
    private String getDisplayName() {
        return currentUserProvider.getCurrentUser().getDisplayName();
    }

    
	@Override
    public TaskComment save(TaskComment taskComment) {
		
         taskComment.setCreatedBy(getDisplayName());
         taskComment.setCreatedAt(LocalDateTime.now());
    	TaskComment saved = taskCommentRepositoryImpl.save(taskComment);
    	
    	auditService.log(
    			         "TaskComment",
    			          saved.getId(),
    			          "INSERT",
    			           null,
    			           saved,
    			           "V2");
    	return saved;
    }
    
    
    @Override
    public TaskCommentResponse updateSingleTaskComment(Long commentId, String updatedComment) {
    	 
     TaskComment taskCommentObj = taskCommentRepositoryImpl.findActiveCommentById(commentId)
    		                      .orElseThrow(() -> new EntityNotFoundException("TaskComment Class not found with id: " + commentId));
     	
    TaskCommentAuditSnapshot before = snapshot(taskCommentObj);
     
     taskCommentObj.setCommentText(updatedComment);
     
     taskCommentObj.setUpdatedAt(LocalDateTime.now());
     
     taskCommentObj.setUpdatedBy(getDisplayName());
     
     TaskCommentAuditSnapshot after = snapshot(taskCommentObj);
     
     auditService.log(
    	               "TaskComment",
    	                taskCommentObj.getId(),
    	                "UPDATE",
    	                 before,
    	                 after,
    	                 "V2");
     
     return toResponse(taskCommentObj);
     
    }
    
 @Override   
 public String softDelete(Long commentId) {
	 
	 TaskComment taskCommentObj = taskCommentRepositoryImpl.findActiveCommentById(commentId)
			                      .orElseThrow(() -> new EntityNotFoundException("Entity TaskComment not found with id:" + commentId)); 
	 
   TaskCommentAuditSnapshot before = snapshot(taskCommentObj);
   
   taskCommentObj.setDeleted(true);
   taskCommentObj.setUpdatedBy(getDisplayName());
   taskCommentObj.setUpdatedAt(LocalDateTime.now());
   
   TaskCommentAuditSnapshot after = snapshot(taskCommentObj);
 
   auditService.log(
		            "TaskComment",
		            taskCommentObj.getId(),
		            "SOFT_DELETE",
		            before,
		            after,
		            "V2");
   
   return "Task Comment with Id: "+ commentId +" was successfully deleted";
 }
    
 public List<TaskCommentResponse> read(){
	 return taskCommentRepositoryImpl.getAllTaskComments()
			 .stream()
			 .map(this::toResponse)
			 .toList();
 }
 
 public TaskCommentResponse createComment(TaskCommentRequest req) {
	 Task task = taskRepositoryImpl.findActiveTaskById(req.taskId)
		        .orElseThrow(() ->
	            new EntityNotFoundException("Task not found with id: " + req.taskId)
	        );
	 TaskComment taskComment = new TaskComment();
	 taskComment.setTask(task);
	 taskComment.setCommentText(req.commentText);
	 taskComment.setCreatedBy(getDisplayName());
	 taskComment.setDeleted(false);
	 taskComment.setCreatedAt(LocalDateTime.now());
	 
	 taskCommentRepositoryImpl.save(taskComment);
	 
	 TaskCommentAuditSnapshot after = snapshot(taskComment);
	 
 	auditService.log(
	         "TaskComment",
	          taskComment.getId(),
	          "INSERT",
	           null,
	           after,
	           "V2");
	 
	 return toResponse(taskComment); 
 }
 
 private TaskCommentResponse toResponse(TaskComment tc) {
	 return new TaskCommentResponse(
			  tc.getId(),
			  tc.getTask().getId(),
			  tc.getCommentText(),
			  tc.getCreatedBy(),
			  tc.getCreatedAt(),
			  tc.getUpdatedBy(),
			  tc.getUpdatedAt()
			 );
 }
 
 private TaskCommentAuditSnapshot snapshot(TaskComment tc) {
	    return new TaskCommentAuditSnapshot(
	        tc.getId(),
	        tc.getTask().getId(),   
	        tc.getCommentText(),
	        tc.getCreatedBy(),
	        tc.getCreatedAt(),
	        tc.getUpdatedBy(),
	        tc.getUpdatedAt(),
	        tc.isDeleted()
	    );
	}

    
}
