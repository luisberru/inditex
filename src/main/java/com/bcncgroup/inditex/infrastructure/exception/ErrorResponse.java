package com.bcncgroup.inditex.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    String message,
    int status,
    LocalDateTime timestamp,
    Map<String, String> errors) {
	

    public ErrorResponse(String message, int status) {
        this(message, status, LocalDateTime.now(), null);
    }

	public ErrorResponse(String message, int status, Map<String, String> errors) {
		 this(message, status, LocalDateTime.now(), errors);
	}

   
}
