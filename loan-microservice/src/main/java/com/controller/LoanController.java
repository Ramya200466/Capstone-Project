package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Loan;
import com.service.LoanService;

@RestController
@Validated
@RequestMapping("/loans")
public class LoanController 
{
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable Integer id) 
	{
		Optional<Loan> loan = loanService.findLoanById(id);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
	@GetMapping("/type/{name}")
	public ResponseEntity<?> getLoanByName(@PathVariable String name) 
	{
		List<Loan> loan = loanService.findLoanByName(name);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
}