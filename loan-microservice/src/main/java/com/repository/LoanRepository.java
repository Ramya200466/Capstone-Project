package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer>
{	
	Optional<Loan> findById(Integer id);
	List<Loan> findByName(String name);
}