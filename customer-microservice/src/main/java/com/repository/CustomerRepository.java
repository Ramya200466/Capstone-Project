package com.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>
{
	Optional<Customer> findByEmailAndPassword(String email,String pasword);
	
	Optional<Customer> findNameById(Integer id);
	
	Optional<Customer> findByEmail(String email);
	
	Optional<Customer> findByGovtId(String govtId);
}