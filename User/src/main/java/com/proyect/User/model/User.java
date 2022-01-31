package com.proyect.User.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*@OneToMany
	private Shopping shopping;
	*/
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@NotEmpty(message = "Last Name cannot be empty")
	private String lastname;
	
	@NotEmpty(message = "EMail cannot be empty")
	private String mail;
	
	@NotEmpty(message = "Password cannot be empty")
	private String password;
	

	private String date;
	
	
}

