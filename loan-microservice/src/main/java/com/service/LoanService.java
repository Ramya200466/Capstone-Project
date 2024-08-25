package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Loan;
import com.repository.LoanRepository;

@Service
public class LoanService 
{
	@Autowired
	LoanRepository loanRepos;
	public Optional<Loan> findLoanById(Integer id)
	{
		Optional<Loan> loan = loanRepos.findById(id);
	    return loan;
	}

	 public List<Loan> findLoanByName(String name) 
	 {
	    List<Loan> loan = loanRepos.findByName(name);
	    return loan;
	 }
}