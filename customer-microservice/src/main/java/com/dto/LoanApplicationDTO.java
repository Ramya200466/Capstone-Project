package com.dto;

import java.time.LocalDate;

public class LoanApplicationDTO 
{
    private Integer applicationId;

    private String customerName;

    private String loanName;
    
    private LocalDate applicationDate;
    
    private String status;

	public LoanApplicationDTO(Integer applicationId, String customerName, String loanName, LocalDate applicationDate,
			String status) 
	{
		super();
		this.applicationId = applicationId;
		this.customerName = customerName;
		this.loanName = loanName;
		this.applicationDate = applicationDate;
		this.status = status;
	}

	public LoanApplicationDTO() 
	{
		super();
	}
    
	public Integer getApplicationId() 
	{
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) 
	{
		this.applicationId = applicationId;
	}

	public String getCustomerName() 
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getLoanName() 
	{
		return loanName;
	}

	public void setLoanName(String loanName) 
	{
		this.loanName = loanName;
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