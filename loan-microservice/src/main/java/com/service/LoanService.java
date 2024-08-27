package com.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.CustomException;
import com.model.Loan;
import com.repository.LoanRepository;

@Service
public class LoanService 
{
	private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
	@Autowired
	LoanRepository loanRepos;
	
	public Optional<Loan> findLoanById(Integer id) throws CustomException
	{
		logger.info("Attempting to find loan by ID: {}", id);
		Optional<Loan> loan = loanRepos.findById(id);
		if(!loan.isPresent())
		{
			logger.error("No loan found for ID: {}", id);
			throw new CustomException("No loan found for id:"+id);
		}
		logger.info("Loan found for ID: {}", id);
	    return loan;
	}

	 public List<Loan> findLoanByType(String type) throws CustomException 
	 {
		logger.info("Attempting to find loan by type: {}", type);
	    List<Loan> loan = loanRepos.findByType(type);
	    if(loan == null || loan.isEmpty())
	    {
	    	logger.error("No loan found for type: {}", type);
	    	throw new CustomException("No loan found for loan type:"+type);
	    }
	    logger.info("Loans found for type: {}", type);
	    return loan;
	 }
}