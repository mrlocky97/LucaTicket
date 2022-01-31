package com.proyect.User.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.proyect.User.controller.exceptions.UserAlreadyExists;
import com.proyect.User.controller.exceptions.UserNotFound;
//import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.model.UserLogin;
import com.proyect.User.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Validated
@Tag(name = "User", description = "The user API")
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService us;
	
	@PutMapping
	public User save(User user) {
		return us.newUser(user);
	}

	@Operation(summary = "Add a new user", description = "returns a json with ResponseEntity created", tags = {
	"User" })
	@PostMapping("/add")
	public ResponseEntity<?> newUser(@Valid @RequestBody User user) {
		
		User check= us.findOneByMail(user);
		if(check!=null) {
			log.info("-----USER HAS BEEN FOUND AND IT ALREADY EXISTS");
			throw new UserAlreadyExists();
		}	
		log.info("------ adding new User (POST) ");
		User u = this.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		log.info("------ new user ADDED) ");
		return  ResponseEntity.ok("USER HAS BEEN CREATED \n" + user);
	}
	
	
	@Operation(summary = "Loggin", description = "log in a user", tags = {
	"User" })
	@PostMapping("/login")
	public UserLogin login(@RequestParam("mail") String mail,@RequestParam("password") String password) {
		String checkMail= us.findOneByMailString(mail);
		String checkPassword = us.checkPassword(password);
		
		if(checkMail==null  || checkPassword != password) {
			log.info("---------------USER OR PASSWORD INCORRECT-------- ");
			throw new UserNotFound(mail);
		}
		
		log.info("----------------------------- 	LOGIN 	 ----------------------------- ");
		String token = getJWTT(mail);
		//comprobar si los datos existen en la db.;
		UserLogin userl  = new UserLogin();
		userl.setMail(mail);
		userl.setPassword(password);
		userl.setToken(token);
		log.info("------ USUARIO PARA LOGIN: " + mail + " PASSWORD: " + password);
		return userl;
	}
	
	/*
	 * construye el token con informacion, expiracion y GrantedAuthority que sirve para autorizar peticiones http
	 */
	private String getJWTT(String userName) {
		// TODO Auto-generated method stub
		String secretKey = "mySecretkey";
		String controlWord = "Bearer";
		log.info("------------------- 	getJWTT METHOD 	-------------------");
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		/*
		 * JWT forma autocontenida para trasmitir informacion como JSON
		 * UUID genera valores aleatorios y sirve como identificador 
		 * CLAIM proporciona informacion del cuerpo del token
		 */
		String token = Jwts.builder()
						.setId(UUID.randomUUID().toString().replace("-", ""))
						.setSubject(userName)
						.claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
						// seteamos la fecha de creacion y la de expiracion
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
						// define al clave encriptada y el algoritmo
						.signWith(SignatureAlgorithm.HS384, secretKey.getBytes()).compact();
							
		return controlWord + " " + token;
	}

}
