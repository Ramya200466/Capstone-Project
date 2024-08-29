package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dto.LoanApplicationDTO;
import com.dto.LoanDTO;
import com.exception.CustomException;
import com.model.Customer;
import com.model.LoanApplication;
import com.repository.CustomerFeignClient;
import com.repository.CustomerRepository;
import com.repository.LoanApplicationRepository;

import jakarta.validation.constraints.Positive;


@Service
public class CustomerService 
{
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepository customerRepos;
	
	@Autowired
	LoanApplicationRepository loanRepos;
	
	@Autowired
    CustomerFeignClient customerFeignClient;
	
	public ResponseEntity<LoanDTO> getLoanById(Integer id)
	{
		logger.info("Retrieving loan details for ID: {}", id);
		return customerFeignClient.getLoanById(id);
	}
	
	public ResponseEntity<?> getLoanByType(@PathVariable String type)
	{
		logger.info("Retrieving loan details for type: {}", type);
		return customerFeignClient.getLoanByType(type);
	}
	public ResponseEntity<?> addNewCustomer(Customer customer) 
	{
		logger.info("Attempting to register new customer with email: {}", customer.getEmail());
		Optional<Customer> existingPatient = customerRepos.findByEmail(customer.getEmail());
        
        if (existingPatient.isPresent()) 
        {
            String message = "Customer already registered with email: " + customer.getEmail();
            logger.warn(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        Optional<Customer> patient = customerRepos.findByGovtId(customer.getGovtId());
        if (patient.isPresent()) 
        {
            String message = "Customer already registered with govtId: " + customer.getGovtId();
            logger.warn(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (customer.getDateOfBirth().isAfter(LocalDate.now())) 
        {
            String message = "Date of birth must be in the past.";
            logger.warn(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
		Customer customer_=customerRepos.save(customer);
		logger.info("Customer registered successfully with ID: {}", customer_.getId());
		return new ResponseEntity<>(customer_, HttpStatus.OK);
	}

	public String loginCustomer(String email, String password) 
	{
		logger.info("Attempting to login customer with email: {}", email);
		Optional<Customer> customer=customerRepos.findByEmailAndPassword(email, password);
		if (customer.isPresent()) 
        {
			logger.info("Customer login successful for email: {}", email);
            return "Valid Customer, you are successfully logged in";
        } 
        else 
        {
        	logger.warn("Invalid login attempt for email: {}", email);
            return "Invalid Customer, check whether email or password is incorrect.";
        }
	}

	public LoanApplication applyNewLoan(LoanApplication loan) throws CustomException
	{
		logger.info("Applying for a new loan with ID: {} for customer ID: {}", loan.getLoanId(), loan.getCustomerId());
		Optional<Customer> customer =customerRepos.findById(loan.getCustomerId());
        if (!customer.isPresent()) 
        {
        	String message = "No Customer found with ID: " + loan.getCustomerId();
            logger.error(message);
            throw new CustomException(message);
        }
        ResponseEntity<?> response = customerFeignClient.getLoanById(loan.getLoanId());
        if (response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT) || response.getBody() == null) 
        {
        	String message = "No loan found with ID: " + loan.getLoanId();
            logger.error(message);
            throw new CustomException(message);
        }
		LoanApplication loan_=loanRepos.save(loan);
		logger.info("Loan application submitted successfully with application ID: {}", loan_.getApplicationId());
		return loanRepos.save(loan_);
	}
	public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationDetailsByCustomerId(Integer id) throws CustomException {
	    logger.info("Retrieving loan application details for Customer ID: {}", id);

	    // Fetch loan applications by customer ID
	    List<LoanApplication> applications = loanRepos.findByCustomerId(id);
	    
	    // Check if no applications are found
	    if (applications.isEmpty()) {
	        String message = "No loan application found for customer id: " + id;
	        logger.error(message);
	        throw new CustomException(message);
	    }
	    
	    // Convert each LoanApplication to LoanApplicationDTO
	    List<LoanApplicationDTO> loanApplicationDTOs = applications.stream().map(application -> {
	        // Retrieve loan details
	        ResponseEntity<LoanDTO> response = customerFeignClient.getLoanById(application.getLoanId());
	        LoanDTO loan = response.getBody();
	        String loanName = loan.getName();
	        
	        // Retrieve customer details
	        Optional<Customer> customer = customerRepos.findNameById(application.getCustomerId());
	        String customerName = customer.map(Customer::getName).orElse("Unknown");

	        // Create and populate LoanApplicationDTO
	        LoanApplicationDTO dto = new LoanApplicationDTO();
	        dto.setApplicationId(application.getApplicationId());
	        dto.setLoanName(loanName);
	        dto.setCustomerName(customerName);
	        dto.setApplicationDate(application.getApplicationDate());
	        dto.setStatus(application.getStatus());

	        return dto;
	    }).collect(Collectors.toList());

	    logger.info("Loan application details retrieved successfully for customer ID: {}", id);

	    // Return the list of DTOs wrapped in a ResponseEntity
	    return ResponseEntity.ok(loanApplicationDTOs);
	}

	
}