package com.proyect.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByPassword(String password);
	
	User findByNameAndPassword(String name, String password);
	/*
	@Query("select name, password from usuarios  where name =?1 and password=?2 ")
	User findByNamePassword(String name, String password);
	*/
}
