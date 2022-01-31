package com.proyect.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.repository.ShoppingRepository;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;

import com.proyect.User.response.UserResponse;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ShoppingRepository sry;

	@Autowired
	private UserRepository ury;
	
	@Override
	public User newUser(User user) {
		// TODO Auto-generated method stub
		return ury.save(user);
	}
	
	public Shopping newShopping(Shopping shopping) {
		return sry.save(shopping);
  }

	@Override
	public Shopping newShopping(Shopping shopping) {
		// TODO Auto-generated method stub
		return sr.save(shopping);
	}

	@Override
	public User findOneByMail(User mail) {
		// TODO Auto-generated method stub
		return ury.findOneByMail(mail.getMail());
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return ury.findByName(name);
	}

	@Override
	public User findByPassword(String password) {
		// TODO Auto-generated method stub
		return ury.findByName(password);
	}

	@Override
	public User existUser(String name, String password) {
		// TODO Auto-generated method stub
		return ury.findByNameAndPassword(name, password);
	}
	
}