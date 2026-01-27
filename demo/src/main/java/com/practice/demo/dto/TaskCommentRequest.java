package com.practice.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskCommentRequest {

@NotBlank(message="Comment text is required.")
@Size(max = 1000)
public String commentText;
public Long taskId;
}
