package com.proyect.User.response;

import java.io.Serializable;

import com.proyect.User.model.User;

import lombok.Data;

@Data
public class ShoppingResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String user;
	private String code;
	private String price;
	private String name;

	
}
