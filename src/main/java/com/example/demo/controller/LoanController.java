package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;

@RestController
@RequestMapping("loan/api/v1.0")
public class LoanController {
	
	@Autowired
	LoanService service;
	
	//consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE
	
	@PostMapping(value = "/create", consumes= MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Loan> loanApplication(@RequestBody Loan loan) {
		
		Loan newLoan = service.addLoan(loan);
		//return new ResponseEntity<Loan>(newLoan , HttpStatus.OK);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		
		//return new ResponseEntity<Loan>(newLoan, headers, HttpStatus.OK);
		return ResponseEntity.ok(newLoan);
	}
	
	@GetMapping("/check")
	public String method() {
		return "Jenkins";
	}
	
	@GetMapping("/fetch/{lid}")
	public ResponseEntity<Loan> getLoan(@PathVariable Long lid) {
		
		if(false) {
			return new ResponseEntity<>(service.getLoan(lid),HttpStatus.OK);
		}
		else {
			throw new IllegalArgumentException("Invalid loan id "+lid);
		}
		//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//return ResponseEntity.status(400).body(null);
		//return service.getLoan(lid);
	}
	
	@GetMapping("/all")
	public List<Loan> getLoans() {
		return service.getLoans();
	}
	
	@PutMapping("/modify")
	public Loan doUpdate(@RequestBody Loan loan) {
		return service.update(loan);
	}
	
	@PutMapping("/modify2")
	public Loan doUpdate2(@RequestBody Loan loan) {
		return service.update(loan);
	}
	
	@DeleteMapping("/remove/{lid}")
	public ResponseEntity delete(@PathVariable Long lid) {
		service.remove(lid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	@ExceptionHandler(value = {IllegalArgumentException.class})
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ResponseEntity<Map<String, String>> handleHandler(IllegalArgumentException ex){
//		Map<String, String> errMap = new HashMap<>();
//		errMap.put("Loanid", ex.getMessage());
//		return ResponseEntity.status(400).body(errMap);
//		
//	}
	
	
	

}
