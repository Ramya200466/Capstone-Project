package com.service;

import java.time.LocalDate;
import java.util.Optional;

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


@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepos;
	
	@Autowired
	LoanApplicationRepository loanRepos;
	
	@Autowired
    CustomerFeignClient customerFeignClient;
	
	public ResponseEntity<LoanDTO> getLoanById(Integer id)
	{
		return customerFeignClient.getLoanById(id);
	}
	
	public ResponseEntity<?> getLoanByType(@PathVariable String type)
	{
		return customerFeignClient.getLoanByType(type);
	}
	public ResponseEntity<?> addNewCustomer(Customer customer) 
	{
		Optional<Customer> existingPatient = customerRepos.findByEmail(customer.getEmail());
        
        if (existingPatient.isPresent()) 
        {
            String message = "Customer already registered with email: " + customer.getEmail();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        Optional<Customer> patient = customerRepos.findByGovtId(customer.getGovtId());
        if (patient.isPresent()) 
        {
            String message = "Customer already registered with govtId: " + customer.getGovtId();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (customer.getDateOfBirth().isAfter(LocalDate.now())) 
        {
            String message = "Date of birth must be in the past.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
		Customer customer_=customerRepos.save(customer);
		return new ResponseEntity<>(customer_, HttpStatus.OK);
	}

	public String loginCustomer(String email, String password) 
	{
		Optional<Customer> customer=customerRepos.findByEmailAndPassword(email, password);
		if (customer.isPresent()) 
        {
            return "Valid Customer, you are successfully logged in";
        } 
        else 
        {
            return "Invalid Customer, check whether email or password is incorrect.";
        }
	}

	public LoanApplication applyNewLoan(LoanApplication loan) throws CustomException
	{
		Optional<Customer> customer =customerRepos.findById(loan.getCustomerId());
        if (!customer.isPresent()) 
        {
            throw new CustomException("No Customer found with ID: " +loan.getCustomerId());
        }
        ResponseEntity<?> response = customerFeignClient.getLoanById(loan.getLoanId());
        if (response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT) || response.getBody() == null) 
        {
            throw new CustomException("No loan found with ID: " + loan.getLoanId());
        }
		LoanApplication loan_=loanRepos.save(loan);
		return loanRepos.save(loan_);
	}
	public LoanApplicationDTO getLoanApplicationDetailsById(Integer id) throws CustomException //throws CustomException 
	{
		Optional<LoanApplication> applicationOptional =loanRepos.findById(id);
		if (!applicationOptional.isPresent()) 
	    {
	        throw new CustomException("No loan found for application id: " + id);
	    }
	    LoanApplication application = applicationOptional.get();
	    
	    ResponseEntity<LoanDTO> response =customerFeignClient.getLoanById(application.getLoanId());
	    LoanDTO loan = response.getBody();
	    String loanName = loan.getName();
	    Optional<Customer> customer = customerRepos.findNameById(application.getCustomerId());
	    String customerName = customer.get().getName();
	    
	    LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
	    loanApplicationDTO.setApplicationId(application.getApplicationId());;
	    loanApplicationDTO.setLoanName(loanName);
	    loanApplicationDTO.setCustomerName(customerName);
	    
	    return loanApplicationDTO;
	}
	
}