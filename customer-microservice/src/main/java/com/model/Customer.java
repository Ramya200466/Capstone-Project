package com.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
    private Integer id;

    @Column(name="name")
    @NotEmpty(message="name missing")
    private String name;

    @Column(name="date_of_birth")
    @NotNull(message="date_of_birth missing")
    private LocalDate date_of_birth;

    @Column(name="gender")
    @NotEmpty(message="gender missing")
    private String gender;
    
    @Column(name="contact")
    @NotEmpty(message="contact missing")
    private String contact;	
    
    @Column(name="govt_id")
    @NotEmpty(message="govt_id missing")
    private String govt_id;	
    
    @Column(name="email")
    @NotEmpty(message="email missing")
     private String email;	
    
    @Column(name="password")
    @NotEmpty(message="password missing")
    private String password;

	public Customer(String name, LocalDate date_of_birth, String gender, String contact, String govt_id, String email,
			String password) 
	{
		super();
		this.name = name;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.contact = contact;
		this.govt_id = govt_id;
		this.email = email;
		this.password = password;
	}

	public Customer(Integer id, String name, LocalDate date_of_birth, String gender, String contact, String govt_id,
			String email, String password) 
	{
		super();
		this.id = id;
		this.name = name;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.contact = contact;
		this.govt_id = govt_id;
		this.email = email;
		this.password = password;
	}

	public Customer() 
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

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public LocalDate getDate_of_birth() 
	{
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) 
	{
		this.date_of_birth = date_of_birth;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getContact() 
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public String getGovt_id() 
	{
		return govt_id;
	}

	public void setGovt_id(String govt_id)
	{
		this.govt_id = govt_id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}	
    
}