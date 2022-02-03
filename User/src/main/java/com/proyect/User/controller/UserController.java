package com.proyect.User.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.proyect.User.model.UserData;
import com.proyect.User.model.UserLogin;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.response.UserResponse;
import com.proyect.User.service.ShoppingServices;
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
	@Autowired
	private ShoppingServices ss;

	@PutMapping
	public User save(User user) {
		return us.newUser(user);
	}

	@Operation(summary = "Add a new user", description = "returns a json with ResponseEntity created", tags = {
			"User" })
	@PostMapping("/add")
	public ResponseEntity<?> newUser(@Valid @RequestBody User user) {

		User check = us.findOneByMail(user);
		if (check != null) {
			log.info("-----USER HAS BEEN FOUND AND IT ALREADY EXISTS");
			throw new UserAlreadyExists();
		}
		log.info("------ adding new User (POST) ");
		User u = this.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		log.info("------ new user ADDED) ");
		return ResponseEntity.ok("USER HAS BEEN CREATED \n" + user);
	}

	@GetMapping("/{mail}")
	private User existUser(String mail) {
		return us.existUser(mail);
	}

	@Operation(summary = "Loggin", description = "log in a user", tags = { "User" })
	@PostMapping("/login")
	public UserLogin login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
		log.info("----------------------------- LOGIN ----------------------------- ");
		String token = getJWTT(mail);
		// comprobar si los datos existen en la db.
		User u = this.existUser(mail);
		if (u != null && u.getPassword().equals(password)) {
			log.info("*****************    " + u.toString());
			UserLogin userl = new UserLogin();
			userl.setMail(u.getMail());
			userl.setPassword(u.getPassword());
			userl.setToken(token);
			log.info("------ USUARIO PARA LOGIN: " + userl.getMail() + " PASSWORD: " + userl.getPassword());
			return userl;
		} else {
			throw new UserNotFound();
		}
	}

	/*
	 * construye el token con informacion, expiracion y GrantedAuthority que sirve
	 * para autorizar peticiones http
	 */
	private String getJWTT(String mail) {
		// TODO Auto-generated method stub
		String secretKey = "mySecretkey";
		String controlWord = "Bearer";
		log.info("------------------- 	getJWTT METHOD 	-------------------");
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		/*
		 * JWT forma autocontenida para trasmitir informacion como JSON UUID genera
		 * valores aleatorios y sirve como identificador CLAIM proporciona informacion
		 * del cuerpo del token
		 */
		String token = Jwts.builder().setId(UUID.randomUUID().toString().replace("-", "")).setSubject(mail)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				// seteamos la fecha de creacion y la de expiracion
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
				// define al clave encriptada y el algoritmo
				.signWith(SignatureAlgorithm.HS384, secretKey.getBytes()).compact();

		return controlWord + " " + token;
	}
	
	@Operation(summary = "Delete a user", description = "Deletes an user by its code", tags = { "User" })
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		log.info("------ deleting user) " + id);
		us.deleteUserById(id);
		log.info("------ DELETED ");
		return ResponseEntity.ok("DELETED");

	}

	// Actualizar User
	@Operation(summary = "Update User", description = "Update an user by its id", tags = { "User" })
	@PutMapping("/update/{id}")
	public void updateUser(@RequestBody User user, @PathVariable int id) {
		us.deleteUserById(id);
		this.newUser(user);
	}
	
	// Listar Users por id
		@Operation(summary = "List users by id", description = "return a json with the user by its id", tags = {
				"Event" })
		@GetMapping("/events/user/{id}")

		public List<UserResponse> findById(@PathVariable int id){
			
			log.info("---------GetUserById");
			List<UserResponse> e = us.findById(id);
			
			if(e.isEmpty()) {
				//throw new UserNotFound(id);
			}
			return us.findById(id);
		}
		
		@Operation(summary = "List of all available users", description = "returns a json with all users in the database", tags = {
		"User" })
		@GetMapping("/users")
		public List<UserResponse> findAll() {
			log.info("------ readSEvents (GET) ");
			return us.findAll();
		}

		//Listar datos del usuario
		@Operation(summary = "List the data of a user", description = "returns a json with the data of a user", tags = {
		"User" })
		@GetMapping("/datauser/{mail}")
		public UserData dataUser (@PathVariable String mail) {
			User user = us.existUser(mail);
			List <ShoppingResponse> all = ss.findByUser(user.getName());
			UserData ud = new UserData();
			ud.setUser(user);
			ud.setAll(all);
			return ud;
		}
		
}
