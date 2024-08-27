package com.dto;

public class LoanDTO 
{
	private Integer id;

    private String type;
    
    private String name;

    private Long minAmount;

    private Long maxAmount;
    
    private Double interest;

    private Integer tenureMonths;

    private String description;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Long getMinAmount() 
	{
		return minAmount;
	}

	public void setMinAmount(Long minAmount) 
	{
		this.minAmount = minAmount;
	}

	public Long getMaxAmount() 
	{
		return maxAmount;
	}

	public void setMaxAmount(Long maxAmount) 
	{
		this.maxAmount = maxAmount;
	}

	public Double getInterest() 
	{
		return interest;
	}

	public void setInterest(Double interest) 
	{
		this.interest = interest;
	}

	public Integer getTenureMonths()
	{
		return tenureMonths;
	}

	public void setTenureMonths(Integer tenureMonths) 
	{
		this.tenureMonths = tenureMonths;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
}