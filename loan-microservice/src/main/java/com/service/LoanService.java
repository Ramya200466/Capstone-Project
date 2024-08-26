package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.CustomException;
import com.model.Loan;
import com.repository.LoanRepository;

@Service
public class LoanService 
{
	@Autowired
	LoanRepository loanRepos;
	public Optional<Loan> findLoanById(Integer id) throws CustomException
	{
		Optional<Loan> loan = loanRepos.findById(id);
		if(!loan.isPresent())
		{
			throw new CustomException("No loan found for id:"+id);
		}
	    return loan;
	}

	 public List<Loan> findLoanByName(String name) throws CustomException 
	 {
	    List<Loan> loan = loanRepos.findByName(name);
	    if(loan == null || loan.isEmpty())
	    {
	    	throw new CustomException("No loan found for loan name:"+name);
	    }
	    return loan;
	 }
}