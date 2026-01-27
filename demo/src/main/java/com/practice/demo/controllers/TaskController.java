package com.practice.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.demo.dto.CallTaskRequest;
import com.practice.demo.dto.DeleteResponse;
import com.practice.demo.dto.LetterTaskRequest;
import com.practice.demo.dto.TaskRequest;
import com.practice.demo.dto.TaskResponse;
import com.practice.demo.dto.TodoTaskRequest;
import com.practice.demo.beans.CallTask;
import com.practice.demo.beans.LetterTask;
import com.practice.demo.beans.Task;
import com.practice.demo.beans.TodoTask;
import com.practice.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
      
	private final TaskService service;
	
	public TaskController(TaskService service) {
		this.service = service;
	}
	
	// Create Call Task Record
	@PostMapping("/callTask")
	public ResponseEntity<Task> createCallTask(
			@RequestBody CallTaskRequest req) {
		 CallTask task = new CallTask();
		 task.setTitle(req.title);
		 task.setDescription(req.description);
		 task.setPhoneNumber(req.phoneNumber);
		 task.setDurationMinutes(req.durationMinutes);
		 
		 return ResponseEntity.ok(service.create(task));
	}
	
	// Create Letter Task Record
	@PostMapping("/letterTask")
	public ResponseEntity<Task> createLetterTask(
			@RequestBody LetterTaskRequest req){
		LetterTask task = new LetterTask();
		
		task.setTitle(req.title);
		task.setDescription(req.description);
		task.setAddress(req.address);
		
		return ResponseEntity.ok(service.create(task));
	}
	
	// Create To-do Task Record
	@PostMapping("/todoTask")
	public ResponseEntity<Task> createTodoTask(
			@RequestBody TodoTaskRequest req){
		TodoTask task = new TodoTask();
		
		task.setTitle(req.title);
		task.setDescription(req.description);
		task.setDueDate(req.dueDate);
		
		return ResponseEntity.ok(service.create(task));
	}
	
	//Get the list of all the task records
	@GetMapping("/getAllTasks")
	public List<TaskResponse> getAllTaskRecords() {
		return service.read();
	}
	
	//Find any task record by Id
	@GetMapping("/getTaskRecord/{id}")
	public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
		
		return service.findById(id)
				      .map(ResponseEntity::ok)
				      .orElse(ResponseEntity.notFound().build());
	}
	
	//Update a task record
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<TaskResponse> updateTaskById(
			@PathVariable Long id,
			@RequestBody TaskRequest req){
		
		
		return ResponseEntity.ok(service.update(id, req));
	}
	
	//Soft Delete
	@DeleteMapping("/deleteRecord/{id}")
	public ResponseEntity<DeleteResponse> softDelete(@PathVariable Long id){
		service.softDelete(id);
		return ResponseEntity.ok(
				new DeleteResponse(
				   id,
				   "Task Deleted successfully",
				   LocalDateTime.now()
					)
				);
	}
}
