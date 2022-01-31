package com.proyect.User.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;

import com.proyect.User.repository.ShoppingRepository;
import com.proyect.User.repository.UserRepository;
import com.proyect.User.response.UserResponse;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserResponse ur;
	@Autowired
	private ShoppingRepository sr;

	
	@Autowired
	private UserRepository ury;

	
	@Override
	public User newUser(User user) {
		// TODO Auto-generated method stub
		return ury.save(user);
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


	

}