package com.proyect.User.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.User.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findOneByMail(String mail);
	User findByNameAndPassword(String name, String password);
	Long deleteUserById(int id);
	List<User> findById(int id);
}
