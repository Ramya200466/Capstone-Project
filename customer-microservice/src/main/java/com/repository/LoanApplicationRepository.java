package com.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.model.LoanApplication;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication,Integer>
{
	Optional<LoanApplication> findById(Integer id);
}