package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.LoanApplication;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication,Integer>
{
	List<LoanApplication> findByCustomerId(Integer id);
}