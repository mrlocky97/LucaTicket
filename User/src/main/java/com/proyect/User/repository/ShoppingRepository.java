package com.proyect.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.Shopping;
import com.proyect.User.response.ShoppingResponse;

public interface ShoppingRepository extends JpaRepository<Shopping, Integer>{
}
