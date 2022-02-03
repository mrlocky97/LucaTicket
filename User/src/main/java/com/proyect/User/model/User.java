package com.proyect.User.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
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

@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@NotEmpty(message = "Last Name cannot be empty")
	private String lastname;

	@NotEmpty(message = "EMail cannot be empty")
	private String mail;

	@NotEmpty(message = "Password cannot be empty")
	private String password;

	@Setter(AccessLevel.NONE)
	private String date;

	private void setDate() {
		String dateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		dateString = date;
	}

}