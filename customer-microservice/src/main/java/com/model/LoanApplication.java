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
    private Integer application_id;

    @Column(name="customer_id")
    @NotNull(message="customer_id missing")
    private Integer customer_id;

    @Column(name="loan_id")
    @NotNull(message="loan_id missing")
    private Integer loan_id;

	public LoanApplication(Integer customer_id, Integer loan_id) 
	{
		super();
		this.customer_id = customer_id;
		this.loan_id = loan_id;
	}

	public Integer getApplication_id() 
	{
		return application_id;
	}

	public void setApplication_id(Integer application_id) 
	{
		this.application_id = application_id;
	}

	public Integer getCustomer_id() 
	{
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) 
	{
		this.customer_id = customer_id;
	}

	public Integer getLoan_id() 
	{
		return loan_id;
	}

	public void setLoan_id(Integer loan_id) 
	{
		this.loan_id = loan_id;
	}

	public LoanApplication()
	{
		super();
	}

	public LoanApplication(Integer application_id, Integer customer_id, Integer loan_id) 
	{
		super();
		this.application_id = application_id;
		this.customer_id = customer_id;
		this.loan_id = loan_id;
	}
}