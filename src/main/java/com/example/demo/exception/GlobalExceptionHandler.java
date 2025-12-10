package com.example.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, MyErrorResponse>> handleHandler(IllegalArgumentException ex){
		
		MyErrorResponse res = new MyErrorResponse();
		res.setErrorCode("400");
		res.setMessage(ex.getMessage());
		res.setTime(new Date(2025,11,28,15,05));
		
		Map<String, MyErrorResponse> errMap = new HashMap<>();
		errMap.put("error",res);
		return ResponseEntity.status(400).body(errMap);
		
	}

}
