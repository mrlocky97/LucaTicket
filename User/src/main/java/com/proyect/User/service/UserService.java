package com.proyect.User.service;

import java.util.List;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.model.Shopping;

public interface UserService {
	public User newUser(User user);
	public Shopping newShopping(Shopping shopping);

	public User findOneByMail(User mail);
	public User findByName(String name);
	public String findOneByMailString(String mail);

	public String checkPassword(String password);
	public List<ShoppingResponse> findAllShoppings();
	public User existUser(String mail);
}
