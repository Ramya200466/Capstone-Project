package com.dto;

public class LoanApplicationDTO 
{
    private Integer applicationId;

    private String customerName;

    private String loanName;

	public LoanApplicationDTO(Integer applicationId, String customerName, String loanName) 
	{
		super();
		this.applicationId = applicationId;
		this.customerName = customerName;
		this.loanName = loanName;
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

}