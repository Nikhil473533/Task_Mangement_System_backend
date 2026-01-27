package com.practice.demo.dto;

public record ApiError(
		              int status,
		              String error,
		              String message
		              ) {}
