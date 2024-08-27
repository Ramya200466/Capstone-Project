package com.controller;


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
import org.springframework.web.server.ResponseStatusException;

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
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/register")
    public ResponseEntity<?> addCustomer(@Valid@RequestBody Customer customer) 
    {
        return customerService.addNewCustomer(customer);
    }
	
	@PostMapping("/login")
    public ResponseEntity<?> loginCustomer(String email,String password) 
    {
		String customer= customerService.loginCustomer(email,password);
		return ResponseEntity.ok(customer);
    }
	
	@GetMapping("/loans/id/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable @Positive Integer id) 
	{
		ResponseEntity<LoanDTO> response = customerService.getLoanById(id);
        return ResponseEntity.ok(response.getBody());
	}

	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type)
	{
		ResponseEntity<?> response = customerService.getLoanByType(type);
        return ResponseEntity.ok(response.getBody());
	}
	
	@PostMapping("/apply")
    public LoanApplication addLoanApplication(@Valid@RequestBody LoanApplication loan) throws CustomException 
    {
        return customerService.applyNewLoan(loan);
    }
	
	@GetMapping("/application/{id}")
	public ResponseEntity<?> getApplicationById(@PathVariable @Positive Integer id) throws CustomException 
	{
		LoanApplicationDTO application = customerService.getLoanApplicationDetailsById(id);
		return new ResponseEntity<>(application, HttpStatus.OK);
	}
	
	
}