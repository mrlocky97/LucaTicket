package com.proyect.User.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.User.controller.exceptions.UserNotFound;
import com.proyect.User.model.Card;
import com.proyect.User.model.Shopping;
import com.proyect.User.model.User;
import com.proyect.User.proxy.UserProxy;
import com.proyect.User.response.ShoppingResponse;
import com.proyect.User.service.ShoppingServices;
import com.proyect.User.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Shopping", description = "The shopping API")
@RequestMapping("/shopping")
@Validated
public class ShoppingController {

	private static final Logger log = LoggerFactory.getLogger(ShoppingController.class);

	@Autowired
	private UserService us;
	@Autowired
	private UserProxy proxy;

	@Autowired
	private ShoppingServices ss;

	@GetMapping("/")
	public ResponseEntity entryShopping() {
		log.info("------ Start my shopping");
		return ResponseEntity.ok("Welcome to LucaTicket! Add a event to the cart");
	}

	// Comprar evento por usuario

	@Operation(summary = "Shopping we are about to buy", description = "returns a json with our shopping", tags = {
			"Shopping" })
	@PostMapping("/buyevent/{userMail}/{eventName}")
	public String buyShopping(@PathVariable String eventName, @PathVariable String userMail,
			@Valid @RequestBody Card card) {
		ShoppingResponse r = proxy.findByName(eventName);
		User user = us.existUser(userMail);
		if (user == null) {
			throw new UserNotFound();
		} else {

			Shopping shopping = new Shopping();
			shopping.setCode(r.getCode());
			shopping.setName(r.getName());
			shopping.setPrice(r.getPrice());
			shopping.setIdshopping(r.getId());
			shopping.setUser(user.getName());
			ss.newShopping(shopping);

			return "You're being redirected to our payment gateway to pay for ---> Name of event: " + r.getName()
					+ "  price to pay ???" + r.getPrice() + "User: " + user.getName() + card.toString();
		}

	}

	// lo conseguimos
	@Operation(summary = "Find Event by name", description = "returns a json with a ShoppingResponse", tags = {
			"Shopping" })
	@GetMapping("/{name}")
	public ShoppingResponse findByName(@PathVariable String name) {
		System.out.println("------------------ controller findbyname");
		ShoppingResponse user = us.findShopping(name);
		return user;
	}

}
