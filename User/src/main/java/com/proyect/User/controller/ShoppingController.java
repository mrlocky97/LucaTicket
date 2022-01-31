package com.proyect.User.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyect.User.response.ShoppingResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyect.User.model.Shopping;
import com.proyect.User.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Shopping", description = "The shopping API")
@RequestMapping("/shopping")
public class ShoppingController {
	
	private static final Logger log = LoggerFactory.getLogger(ShoppingController.class);
	
	@Autowired
	private UserService us;
	
	@PutMapping
	public Shopping save(Shopping shopping) {
		return us.newShopping(shopping);
	}
	
	@Operation(summary = "List of all available shoppings", description = "returns a json with all shoppings in the database", tags = {
	"Shopping" })
	@GetMapping("/shoppings")
	public List<ShoppingResponse> findAllShoppings() {
		log.info("------ read Shopping (GET) ");
		return us.findAllShoppings();
	}
	
	@PostMapping("/buy")
	//En testing
	public ResponseEntity<?> buyEvent(@RequestBody Shopping shopping) {
		log.info("------ adding event bought (POST) ");
		Shopping s = this.save(shopping);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idshopping}").buildAndExpand(s.getIdshopping()).toUri();
		log.info("------ new event bought ADDED) ");
		return ResponseEntity.created(location).build();
	}
	
	public boolean validateCard(String numberCard, int month, int year) {
		Date dt = new Date();
        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = dt.toInstant().atZone(timeZone).toLocalDate();
        getLocalDate.getYear();
	
		int yeah = 0;
		
		if (numberCard.length() < 16 || numberCard.length() > 16) {
			return false;
		}
		
		if(month > 12 || month < 1) {
			return false;
		}
		
        yeah =  getLocalDate.getYear() + 2;
       
        //Tolera tarjeta que caducan dos años posterior, pero no tolera ninguna del año inferior a la actual
        if(year <  getLocalDate.getYear() || year > yeah ) {
        	return false;
        }
		
		return true;
		
	}
}
