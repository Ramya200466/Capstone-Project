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
import com.dto.LoanDTO;
import com.exception.CustomException;
import com.model.Customer;
import com.model.LoanApplication;
import com.repository.CustomerFeignClient;
import com.service.CustomerService;

import feign.FeignException;
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
	public ResponseEntity<LoanDTO> getLoanById(@PathVariable Integer id) throws CustomException
	{
		try 
		{
	        ResponseEntity<LoanDTO> response = customerFeignClient.getLoanById(id);
	        if (response.getStatusCode().is4xxClientError() || response.getBody() == null) 
	        {
	            throw new CustomException("No Loan found with ID: " + id);
	        }
	        return ResponseEntity.ok(response.getBody());
	    }
		catch (FeignException.NotFound e) 
		{
	        throw new CustomException("No Loan found with ID: " + id);
	    }
	}
	
	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type) throws CustomException
	{
		try 
		{
	        ResponseEntity<?> response = customerFeignClient.getLoanByType(type);
	        if (response.getStatusCode().is4xxClientError() || response.getBody() == null) 
	        {
	            throw new CustomException("No Loan found with name: " + type);
	        }
	        return ResponseEntity.ok(response.getBody());
	    }
		catch (FeignException.NotFound e) 
		{
	        throw new CustomException("No Loan found with name: " + type);
	    }
	}
	
	@PostMapping("/apply")
    public LoanApplication addLoanApplication(@Valid@RequestBody LoanApplication loan) throws CustomException 
    {
        return customerService.applyNewLoan(loan);
    }
	
	@GetMapping("/application/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable Integer id) throws CustomException //throws CustomException 
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