package com.proyect.User.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.Shopping;
import com.proyect.User.response.ShoppingResponse;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Integer>{
	
	List<Shopping> findByUser(String user);
}
