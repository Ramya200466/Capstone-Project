package com.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomException;
import com.model.Loan;
import com.service.LoanService;

@RestController
@Validated
@RequestMapping("/loans")
public class LoanController 
{
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable Integer id) throws CustomException 
	{
		logger.info("Received request to get loan by ID: {}", id);
		Optional<Loan> loan = loanService.findLoanById(id);
		logger.info("Successfully retrieved loan for ID: {}", id);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
	@GetMapping("/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type) throws CustomException 
	{
		logger.info("Received request to get loan by type: {}", type);
		List<Loan> loan = loanService.findLoanByType(type);
		logger.info("Successfully retrieved loans for type: {}", type);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
}