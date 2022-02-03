package com.proyect.User.service;

import java.util.List;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.model.Shopping;

public interface ShoppingServices {
	public Shopping newShopping(Shopping shopping);
	public List<ShoppingResponse> findByUser(String user);

}
