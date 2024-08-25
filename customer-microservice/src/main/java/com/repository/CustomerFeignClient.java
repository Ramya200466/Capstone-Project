package com.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="LOANMICROSERVICE")
public interface CustomerFeignClient 
{
	@GetMapping("/loans/id/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable Integer id);
	
	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type);
}