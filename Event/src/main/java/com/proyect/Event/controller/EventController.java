package com.proyect.Event.controller;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpStatus;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.proyect.Event.Services.EventServices;
import com.proyect.Event.controller.exceptions.EventNotFound;
import com.proyect.Event.model.Event;
import com.proyect.Event.response.EventResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController

@RequestMapping("/event")
@Tag(name = "Event", description = "The event API")
public class EventController {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventServices eventServices;


	
	@Operation(summary = "List of all available events", description = "returns a json with all events in the database", tags = {
			"Event" })
	@GetMapping("/events")
	public List<EventResponse> findAll() {
		log.info("------ readSEvents (GET) ");
		return eventServices.findAll();
	}

	
	@Operation(summary = "Save a event", description = "inserts an event to the database", tags = { "Event" })
	@PutMapping
	public Event save(Event event) {
		return eventServices.save(event);
	}
	

	@Operation(summary = "Add a new eventt", description = "Add a new eventt", tags = { "Event" })
	@PostMapping("/add")
	public ResponseEntity<?> addEvent(@RequestBody Event event) {
		log.info("------ addSEvent (POST) ");
		Event result = this.save(event);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(result.getCode())
				.toUri();
		log.info("------ new event has been ADDED ");
		return ResponseEntity.ok("EVENT HAS BEEN CREATED \n" + event);
	}

	
	
	@Operation(summary = "Delete a event", description = "Deletes an event by its code", tags = { "Event" })
	@DeleteMapping("/deleteEvent/{code}")
	public ResponseEntity<?> deleteUser(@PathVariable("code") String code) {
		log.info("------ deleting event) " + code);
		
			eventServices.deleteEvent(code);
			log.info("------ DELETED ");
			return ResponseEntity.ok("DELETED");

		
		
	}

	// Actualizar juego
	@Operation(summary = "Update Event", description = "Update an event by its code", tags = { "Event" })
	@PutMapping("/{code}")
	public void updateGame(@RequestBody Event event, @PathVariable String code) {
		eventServices.deleteEvent(code);
		eventServices.save(event);
	}
	
	//Listar eventos por género
	@Operation(summary = "List events by genre", description = "returns a json with all events by genre in the database", tags = { "Event" })
	@GetMapping("/events/genre/{genre}")
	public List<Event> findByGenre(@PathVariable String genre) {
		return eventServices.findByGenre(genre);
	}
	
	//Listar eventos por nombre
	@Operation(summary = "List events by name", description = "returns a json with all events by name in the database", tags = { "Event" })
	@GetMapping("/events/name/{name}")
	public List<Event> findByName(@PathVariable String name){
		return eventServices.findByName(name);
	}
}
