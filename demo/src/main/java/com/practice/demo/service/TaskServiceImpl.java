package com.practice.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.practice.demo.service.security.CurrentUserProvider;
import com.practice.demo.beans.Task;
import com.practice.demo.beans.TaskStatus;
import com.practice.demo.beans.User;
import com.practice.demo.dto.TaskAuditSnapshot;
import com.practice.demo.dto.TaskRequest;
import com.practice.demo.dto.TaskResponse;
import com.practice.demo.exception.BusinessRuleViolationException;
import com.practice.demo.repository.TaskRepositoryImpl;
import com.practice.demo.repository.TaskStatusRepositoryImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	private final TaskRepositoryImpl repository;
	private final AuditService auditService;
	private final CurrentUserProvider currentUserProvider;
    private final TaskStatusRepositoryImpl taskStatusRepositoryImpl;

	
	public TaskServiceImpl(TaskRepositoryImpl repository, AuditService auditService, CurrentUserProvider currentUserProvider, TaskStatusRepositoryImpl taskStatusRepositoryImpl) {
		this.repository = repository;
		this.auditService = auditService;
        this.currentUserProvider = currentUserProvider;
        this.taskStatusRepositoryImpl = taskStatusRepositoryImpl;
	}
	
	private User getCurrentUser() {
	  return currentUserProvider.getCurrentUser();
	}
	
	@Override
	public Task create(Task task) {
		task.setCreatedByUser(getCurrentUser());
		task.setCreatedAt(LocalDateTime.now());
		
		TaskStatus openStatus = taskStatusRepositoryImpl.findStatusByCode("OPEN").orElseThrow(() -> new BusinessRuleViolationException("OPEN status not configured"));
		
		task.setTaskStatus(openStatus);
		
	    Task saved = repository.save(task);
	   auditService.log(
			   "TASK",
			   saved.getId(),
			   "CREATE",
			    null,
			    saved,
			    "V2"
			   );
		return saved;
	}

	@Override
	public TaskResponse update(Long id, TaskRequest req) {
		Task existingObj = repository.findActiveTaskById(id).orElseThrow(() -> new EntityNotFoundException());
		
		TaskAuditSnapshot before = snapshot(existingObj);
		
		existingObj.setTitle(req.title);
		existingObj.setDescription(req.description);
		existingObj.setUpdatedByUser(getCurrentUser());
        existingObj.setUpdatedAt(LocalDateTime.now());		
		
		Task saved = repository.save(existingObj);
		
		TaskAuditSnapshot after = snapshot(existingObj);
		
		auditService.log(
				  "TASK",
				  saved.getId(),
				  "UPDATE",
				   before,
				   after,
				   "V2"
				);
		
		return toResponse(saved);
	}

	@Override
	public void softDelete(Long id) {
		Task task = repository.findActiveTaskById(id).orElseThrow();
		
		TaskAuditSnapshot before = snapshot(task);
		
		task.setDeleted(true);
		task.setUpdatedByUser(getCurrentUser());
		task.setUpdatedAt(LocalDateTime.now());
		repository.save(task);
		
		TaskAuditSnapshot after = snapshot(task);
		
		auditService.log(
				"TASK",
				task.getId(),
				"SOFT_DELETE",
				 before,
				 after,
			     "V2"
				);
 	}

	@Override
	public List<TaskResponse> read() {
		
		return repository.findActiveTasks() 
				          .stream()
				          .map(this::toResponse)
				          .toList();
	}
	
	@Override
	public TaskResponse changeTaskStatus(Long id, String status) {
		    Task taskObj = repository.findActiveTaskById(id).orElseThrow();
		    TaskAuditSnapshot before = snapshot(taskObj);
            TaskStatus tsObj = taskStatusRepositoryImpl.findStatusByCode(status).orElseThrow();
            taskObj.setTaskStatus(tsObj);
            taskObj.setUpdatedAt(LocalDateTime.now());
            taskObj.setUpdatedByUser(getCurrentUser());
		    Task saved = repository.save(taskObj);
		    TaskAuditSnapshot after = snapshot(saved);
		    auditService.log(
		    		       "TASK",
		    		       taskObj.getId(),
		    		       "UPDATE(change_Task_status)",
		    		         before,
		    		         after,
		    		         "V2"
		    		        );
		    return toResponse(saved);
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
	
   @Override
   public Optional<TaskResponse> findById(Long id) {
       return repository.findActiveTaskById(id)
                        .map(this::toResponse);
   }
	
 private TaskAuditSnapshot snapshot(Task task) {
	 
	 return new TaskAuditSnapshot(
			  task.getId(),
			  task.getTitle(),
			  task.getDescription(),
			  task.isDeleted(),
			  task.getCreatedAt(),
			  task.getUpdatedAt()
			 );			 
 }	

}
