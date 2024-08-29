package com.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
    private Integer id;

    @Column(name="name")
    @NotEmpty(message="Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String name;

    @Column(name="date_of_birth")
    @NotNull(message="Date of Birth is required")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    @Column(name="gender")
    @NotEmpty(message="Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;
    
    @Column(name="contact")
    @NotEmpty(message="Invalid contact")
    @Pattern(regexp = "^\\d{10}$", message = "Contact must be a 10-digit number")
    private String contact;	
    
    @Column(name="govt_id")
    @NotEmpty(message = "Government ID is missing")
    @Size(min = 1, max = 20, message = "Government ID must be between 1 and 20 characters")
    private String govtId;	
    
    @Column(name="email")
    @Email(message = "Invalid email format")
    @NotEmpty(message="email missing")
     private String email;	
    
    @Column(name="password")
    @NotEmpty(message = "Password is missing")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

	public Customer(String name, LocalDate dateOfBirth, String gender, String contact, String govtId, String email,
			String password) 
	{
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contact = contact;
		this.govtId = govtId;
		this.email = email;
		this.password = password;
	}

	public Customer(Integer id, String name, LocalDate dateOfBirth, String gender, String contact, String govtId,
			String email, String password) 
	{
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contact = contact;
		this.govtId = govtId;
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

	public LocalDate getDateOfBirth() 
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
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

	public String getGovtId() 
	{
		return govtId;
	}

	public void setGovtId(String govtId)
	{
		this.govtId = govtId;
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