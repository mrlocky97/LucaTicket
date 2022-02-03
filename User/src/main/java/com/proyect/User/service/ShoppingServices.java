package com.proyect.User.service;

import java.util.List;

import com.proyect.User.response.ShoppingResponse;

public interface ShoppingServices {

	
	
	public List<ShoppingResponse> findByUser(String user);
}
