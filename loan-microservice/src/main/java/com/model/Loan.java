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
public class Loan {
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
    @NotNull(message="name missing")
    private Long min_amount;

    @Column(name="max_amount")
    @NotNull(message="name missing")
    private Long max_amount;
    
    @Column(name="interest")
    @NotNull(message="name missing")
    private Double interest;

    @Column(name="tenure_months")
    @NotNull(message="name missing")
    private Integer tenure_months;

    @Column(name="description")
    @NotEmpty(message="name missing")
    private String description;

	public Loan(Integer id,String name,String type, Long min_amount, Long max_amount, Double interest, Integer tenure_months,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.min_amount = min_amount;
		this.max_amount = max_amount;
		this.interest = interest;
		this.tenure_months = tenure_months;
		this.description = description;
	}

	public Loan() 
	{
		super();
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