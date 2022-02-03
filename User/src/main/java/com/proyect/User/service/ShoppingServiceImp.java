package com.proyect.User.service;

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

}
