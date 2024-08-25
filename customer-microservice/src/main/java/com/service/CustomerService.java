package com.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.LoanApplicationDTO;
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
	
	public Customer addNewCustomer(Customer customer) 
	{
		Customer customer_=customerRepos.save(customer);
		return customerRepos.save(customer_);
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

	public LoanApplication applyNewLoan(LoanApplication loan)
	{
		LoanApplication loan_=loanRepos.save(loan);
		return loanRepos.save(loan_);
	}
	public Optional<LoanApplication> getLoanApplicationDetailsById1(int id) //throws CustomException 
	{
		Optional<LoanApplication> application =  loanRepos.findById(id);
		
		return application;
	}
	public LoanApplicationDTO getLoanApplicationDetailsById(Integer id) //throws CustomException 
	{
		Optional<LoanApplication> applicationOptional =loanRepos.findById(id);
		 
	    LoanApplication application = applicationOptional.get();
	    
	    ResponseEntity<?> response =customerFeignClient.getLoanById(application.getLoan_id());
	    
	    @SuppressWarnings("unchecked")
		Map<String, Object> loanMap = (Map<String, Object>) response.getBody();
	    String loanName = (String) loanMap.get("name");
	    Optional<Customer> customer = customerRepos.findNameById(application.getCustomer_id());
	    String customerName = customer.get().getName();
	    
	    LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
	    loanApplicationDTO.setApplicationId(application.getApplication_id());;
	    loanApplicationDTO.setLoanName(loanName);
	    loanApplicationDTO.setCustomerName(customerName);
	    
	    return loanApplicationDTO;
	}
	
}