package com.proyect.User.service;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.model.Shopping;

public interface UserService {
	public User newUser(User user);
	public Shopping newShopping(Shopping shopping);
	public User existUser(String name, String password);
}
