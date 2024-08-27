package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="loan")
public class Loan 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Integer id;

    @Column(name="type")
    @NotEmpty(message="type missing")
    private String type;
    
    @Column(name="name")
    @NotEmpty(message="name missing")
    private String name;

    @Column(name="min_amount")
    @NotNull(message="minAmount missing")
    private Long minAmount;

    @Column(name="max_amount")
    @NotNull(message="maxAmount missing")
    private Long maxAmount;
    
    @Column(name="interest")
    @NotNull(message="interest missing")
    private Double interest;

    @Column(name="tenure_months")
    @NotNull(message="tenureMonths missing")
    private Integer tenureMonths;

    @Column(name="description")
    @NotEmpty(message="description missing")
    private String description;

	public Loan() 
	{
		super();
	}

	public Loan(Integer id, String type, String name, Long minAmount, Long maxAmount, Double interest,
			Integer tenureMonths, String description) 
	{
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.interest = interest;
		this.tenureMonths = tenureMonths;
		this.description = description;
	}

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