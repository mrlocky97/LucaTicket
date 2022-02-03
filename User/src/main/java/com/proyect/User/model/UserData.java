package com.proyect.User.model;

import java.util.List;

import com.proyect.User.response.ShoppingResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	private User user;
	
	List <ShoppingResponse> all;
	
	

}
