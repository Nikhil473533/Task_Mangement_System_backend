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
import com.practice.demo.beans.TaskComment;
import com.practice.demo.dto.DeleteResponse;
import com.practice.demo.dto.TaskCommentRequest;
import com.practice.demo.dto.TaskCommentResponse;
import com.practice.demo.service.TaskCommentServiceImpl;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/taskComments")
public class TaskCommentController {

	private final TaskCommentServiceImpl taskCommentServiceImpl;

	
	public TaskCommentController(TaskCommentServiceImpl taskCommentServiceImpl) {
		this.taskCommentServiceImpl = taskCommentServiceImpl;
	}

 @PostMapping("/createComment")
 public ResponseEntity<TaskCommentResponse> createComment(@RequestBody TaskCommentRequest req){
	  
	  return ResponseEntity.ok(taskCommentServiceImpl.createComment(req));
 }
	
@GetMapping("/getAllComments")
public List<TaskCommentResponse> getAllComments(){
	
	return taskCommentServiceImpl.read();
}
 
@PutMapping("/updateComment/{id}")
public ResponseEntity<TaskCommentResponse> updateComment(@PathVariable Long id, @Valid @RequestBody TaskCommentRequest req){
	
	TaskCommentResponse updatedTaskComment = taskCommentServiceImpl.updateSingleTaskComment(id, req.commentText);
	
	return ResponseEntity.ok(updatedTaskComment);
	
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<DeleteResponse> softDeleteComment(@PathVariable Long id) {
	
	String text = taskCommentServiceImpl.softDelete(id);
	 return ResponseEntity.ok(
			 new DeleteResponse(
				   id,
			       text,
			      LocalDateTime.now()
			    )
			 );
	
}
}
