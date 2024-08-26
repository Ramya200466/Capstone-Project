package com.dto;

public class LoanDTO 
{
	private Integer id;

    private String type;
    
    private String name;

    private Long min_amount;

    private Long max_amount;
    
    private Double interest;

    private Integer tenure_months;

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

	public Long getMin_amount() 
	{
		return min_amount;
	}

	public void setMin_amount(Long min_amount) 
	{
		this.min_amount = min_amount;
	}

	public Long getMax_amount() 
	{
		return max_amount;
	}

	public void setMax_amount(Long max_amount) 
	{
		this.max_amount = max_amount;
	}

	public Double getInterest() 
	{
		return interest;
	}

	public void setInterest(Double interest)
	{
		this.interest = interest;
	}

	public Integer getTenure_months()
	{
		return tenure_months;
	}

	public void setTenure_months(Integer tenure_months) 
	{
		this.tenure_months = tenure_months;
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