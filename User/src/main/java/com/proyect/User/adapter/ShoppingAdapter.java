package com.proyect.User.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyect.User.model.Shopping;
//import com.proyect.User.model.Shopping;
//import com.proyect.User.model.ShoppingEvent;
//import com.proyect.User.response.EventResponse;
//import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.response.ShoppingResponse;

@Component
public class ShoppingAdapter {

	public static ShoppingResponse of(Shopping shopping) {
		ShoppingResponse sr = new ShoppingResponse();
		sr.setUser(shopping.getUser());
		sr.setCode(shopping.getCode());
		sr.setName(shopping.getName());
		sr.setPrice(shopping.getPrice());

		return sr;
	}

	public static List<ShoppingResponse> of(List<Shopping> shoppings) {

		return shoppings.stream().map(p -> of(p)).collect(Collectors.toList());
	}

}
