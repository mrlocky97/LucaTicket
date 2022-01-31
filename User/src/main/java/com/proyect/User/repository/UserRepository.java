package com.proyect.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByPassword(String password);
	User findOneByMail(String mail);
	User findByNameAndPassword(String name, String password);
}
