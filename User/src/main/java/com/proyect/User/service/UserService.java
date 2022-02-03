package com.proyect.User.service;

import java.util.List;

import com.proyect.User.model.User;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.response.UserResponse;

public interface UserService {
	public User newUser(User user);

	public User findOneByMail(User mail);

	public ShoppingResponse findShopping(String name);

	public User existUser(String mail);

	public void deleteUserById(int id);

	public List<UserResponse> findById(int id);

	public List<UserResponse> findAll();
}
