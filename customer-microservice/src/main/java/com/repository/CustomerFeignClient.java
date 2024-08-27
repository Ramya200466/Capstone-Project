package com.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.config.FeignConfig;
import com.dto.LoanDTO;
@FeignClient(name="LOANMICROSERVICE",configuration = FeignConfig.class)
public interface CustomerFeignClient 
{
	@GetMapping("/loans/id/{id}")
	public ResponseEntity<LoanDTO> getLoanById(@PathVariable Integer id);
	
	@GetMapping("/loans/type/{type}")
	public ResponseEntity<?> getLoanByType(@PathVariable String type);
}