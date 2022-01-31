package com.proyect.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findOneByMail(String mail);
	//String findOneByMailString(String mail);
	User findByNameAndPassword(String name, String password);
}
