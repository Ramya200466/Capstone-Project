package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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

	public LoanApplication(Integer customerId, Integer loanId) 
	{
		super();
		this.customerId = customerId;
		this.loanId = loanId;
	}

	public Integer getApplicationId() 
	{
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) 
	{
		this.applicationId = applicationId;
	}

	public Integer getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getLoanId() 
	{
		return loanId;
	}

	public void setLoanId(Integer loanId) 
	{
		this.loanId = loanId;
	}

	public LoanApplication()
	{
		super();
	}

	public LoanApplication(Integer applicationId, Integer customerId, Integer loanId) 
	{
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanId = loanId;
	}
}