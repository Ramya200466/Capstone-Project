package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoanApplicationDTO;
import com.dto.LoanDTO;
import com.exception.CustomException;
import com.model.Customer;
import com.model.LoanApplication;
import com.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/customers")
public class CustomerController 
{
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/register")
    public ResponseEntity<?> addCustomer(@Valid@RequestBody Customer customer) 
    {
		logger.info("Registering a new customer");
        return customerService.addNewCustomer(customer);
    }
	
	@PostMapping("/login")
    public ResponseEntity<?> loginCustomer(String email,String password) 
    {
		logger.info("Customer login attempt with email: {}", email);
		String customer= customerService.loginCustomer(email,password);
		return ResponseEntity.ok(customer);
    }
	
	@GetMapping("/loans/id/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable @Positive Integer id) 
	{
		logger.info("Retrieving loan by ID: {}", id);
		ResponseEntity<LoanDTO> response = customerService.getLoanById(id);
        return ResponseEntity.ok(response.getBody());
	}

	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type)
	{
		logger.info("Retrieving loan by type: {}", type);
		ResponseEntity<?> response = customerService.getLoanByType(type);
        return ResponseEntity.ok(response.getBody());
	}
	
	@PostMapping("/apply")
    public LoanApplication addLoanApplication(@Valid@RequestBody LoanApplication loan) throws CustomException 
    {
		logger.info("Submitting loan application for loan ID: {}", loan.getLoanId());
        return customerService.applyNewLoan(loan);
    }
	
	@GetMapping("{customerId}/applications")
	public ResponseEntity<List<LoanApplicationDTO>> getApplicationsByCustomerId(@PathVariable @Positive Integer customerId) throws CustomException {
	    logger.info("Retrieving loan applications for Customer ID: {}", customerId);
	    
	    // Retrieve the list of LoanApplicationDTO from the service
	    ResponseEntity<List<LoanApplicationDTO>> applications = customerService.getLoanApplicationDetailsByCustomerId(customerId);
	    
	    // Return the list wrapped in a ResponseEntity with HTTP 200 OK status
	    return applications;
	}

	
	
}