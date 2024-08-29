package com.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="loan_application")
public class LoanApplication 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
    private Integer applicationId;

    @Column(name="customer_id")
    @NotNull(message="customer_id missing")
    private Integer customerId;

    @Column(name="loan_id")
    @NotNull(message="loan_id missing")
    private Integer loanId;
    
    @Column(name="application_date")
    @FutureOrPresent
    @NotNull(message="applicationDate missing")
    private LocalDate applicationDate;
    
    @Column(name="status")
    @NotEmpty(message="status missing")
    private String status;

    
	public LoanApplication(Integer applicationId, Integer customerId, Integer loanId, LocalDate applicationDate,
			String status) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanId = loanId;
		this.applicationDate = applicationDate;
		this.status = status;
	}

	public LoanApplication(Integer customerId, Integer loanId, LocalDate applicationDate, String status) 
	{
		super();
		this.customerId = customerId;
		this.loanId = loanId;
		this.applicationDate = applicationDate;
		this.status = status;
	}

	public LoanApplication() 
	{
		super();
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    

	
}