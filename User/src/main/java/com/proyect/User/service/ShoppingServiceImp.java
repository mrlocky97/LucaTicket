package com.proyect.User.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.User.adapter.ShoppingAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyect.User.model.Shopping;
import com.proyect.User.repository.ShoppingRepository;
import com.proyect.User.response.ShoppingResponse;

@Transactional
@Service
public class ShoppingServiceImp implements ShoppingServices {

	@Autowired
	private ShoppingRepository sr;

	@Override
	public Shopping newShopping(Shopping shopping) {
		// TODO Auto-generated method stub
		return sr.save(shopping);
	}

	@Autowired
	private ShoppingRepository sry;
	
	@Autowired
	private ShoppingAdapter sa;
	
	@Override
	public List<ShoppingResponse> findByUser(String user) {
		final List<Shopping> all = sry.findByUser(user);
		return sa.of(all);
	}
}
