package com.proyect.User.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyect.User.adapter.UserAdapter;
import com.proyect.User.model.User;
import com.proyect.User.proxy.UserProxy;
import com.proyect.User.repository.UserRepository;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.response.UserResponse;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProxy proxy;
	
	//@Autowired
	//private ShoppingRepository sry;

	@Autowired
	private UserRepository ury;
	
	@Autowired
	private UserAdapter ua;

	@Override
	public User newUser(User user) {
		// TODO Auto-generated method stub
		return ury.save(user);
	}


	@Override
	public User findOneByMail(User mail) {
		// TODO Auto-generated method stub
		return ury.findOneByMail(mail.getMail());
	}
	//lo conseguimos
	@Override
	public ShoppingResponse findShopping(String name) {
		
		//final List<Shopping> all = sry.findAll();
		System.out.println("---------- service findbyname");
		return proxy.findByName(name);
	}

	@Override
	public User existUser(String mail) {
	
	return ury.findOneByMail(mail);
	}
	
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		ury.deleteUserById(id);
	}

	@Override
	public List<UserResponse> findById(int id) {
		final List<User> all = ury.findById(id);
		return ua.of(all);
	}
	
	@Override
	public List<UserResponse> findAll() {
		final List<User> all = ury.findAll();
		return ua.of(all);
	}

}