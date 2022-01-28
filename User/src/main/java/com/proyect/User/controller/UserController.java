package com.proyect.User.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.model.UserLogin;
import com.proyect.User.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
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
	
	@PutMapping
	public Shopping save(Shopping shopping) {
		return us.newShopping(shopping);
	}

	
	@Operation(summary = "Add a new user", description = "returns a json with ResponseEntity created", tags = {
	"User" })
	@PostMapping("/add")
	public ResponseEntity<?> newUser(@RequestBody User user) {
		log.info("------ adding new User (POST) ");
		User u = this.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		log.info("------ new user ADDED) ");
		return ResponseEntity.created(location).build();
	}
	
	@Operation(summary = "Loggin", description = "log in a user", tags = {
	"User" })
	@PostMapping("/login")
	public UserLogin login(@RequestParam("userName") String userName,@RequestParam("password") String password) {
		String token = getJWTT(userName);
		// creamos un usuario solo para hacer el login
		UserLogin userl  = new UserLogin();
		userl.setName(userName);
		userl.setPassword(password);
		userl.setToken(token);
		log.info("------ USUARIO PARA LOGIN: " + userl.getName() + " PASSWORD: " + userl.getPassword());
		return userl;
	}
	
	/*
	 * construye el token con informacion, expiracion y GrantedAuthority que sirve para autorizar peticiones http
	 */
	private String getJWTT(String userName) {
		// TODO Auto-generated method stub
		String secretKey = "MySecretkey";
		String controlWord = "Bearer";
		log.info("------ getJWTT METHOD ------");
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
	
	@PostMapping("/{id}/buy")
	public ResponseEntity<?> buyEvent(@RequestBody Shopping shopping) {
		log.info("------ adding event bought (POST) ");
		Shopping s = this.save(shopping);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idshopping}").buildAndExpand(s.getIdshopping()).toUri();
		log.info("------ new event bought ADDED) ");
		return ResponseEntity.created(location).build();
	}
	
}
