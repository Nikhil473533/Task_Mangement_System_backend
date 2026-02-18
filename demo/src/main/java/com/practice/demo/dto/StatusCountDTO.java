package com.practice.demo.dto;

public class StatusCountDTO {

	private String code;
	private String displayName;
	private Long taskCount;
	
	public StatusCountDTO() {
		super();
	}

	public StatusCountDTO(String code, String displayName, Long taskCount) {
		
		this.code = code;
		this.displayName = displayName;
		this.taskCount = taskCount;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}
	
	

}
