package com.controller;

import java.util.Optional;

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
import com.model.Customer;
import com.model.LoanApplication;
import com.repository.CustomerFeignClient;
import com.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/customers")
public class CustomerController 
{
	@Autowired
	CustomerService customerService;
	
	@Autowired
    private CustomerFeignClient customerFeignClient;
	
	@PostMapping("/register")
    public Customer addCustomer(@Valid@RequestBody Customer customer) 
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
	public ResponseEntity<?> getLoanById(@PathVariable Integer id)
	{
		return customerFeignClient.getLoanById(id);
	}
	
	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type)
	{
		return customerFeignClient.getLoanByType(type);
	}
	
	@PostMapping("/apply")
    public LoanApplication addLoanApplication(@Valid@RequestBody LoanApplication loan) 
    {
        return customerService.applyNewLoan(loan);
    }
	
	@GetMapping("/application/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable Integer id) //throws CustomException 
	{
		LoanApplicationDTO application = customerService.getLoanApplicationDetailsById(id);
		return new ResponseEntity<>(application, HttpStatus.OK);
	}
	
	@GetMapping("/applications/{id}")
	public ResponseEntity<?> getLoanApplicationById1(@PathVariable Integer id)
	{
		Optional<LoanApplication> application = customerService.getLoanApplicationDetailsById1(id);
		return new ResponseEntity<>(application, HttpStatus.OK);
	}
	
}