package com.proyect.Event.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyect.Event.Services.EventServices;
import com.proyect.Event.controller.exceptions.EventAlreadyExists;
import com.proyect.Event.controller.exceptions.EventNotFound;
import com.proyect.Event.model.Event;
import com.proyect.Event.repository.EventRepository;
import com.proyect.Event.response.EventResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/event")
@Validated
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
	
	public List<Event> findEverything() {
		log.info("------ readSEvents (GET) ");
		return eventServices.findEveryThing();
	}

	@Operation(summary = "Save a event", description = "inserts an event to the database", tags = { "Event" })
	@PutMapping
	public Event save(Event event) {
		return eventServices.save(event);
	}

	@Operation(summary = "Add a new eventt", description = "Add a new eventt", tags = { "Event" })
	@PostMapping("/add")
	public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
		log.info("------ addSEvent (POST) ");
		
			Event check= eventServices.findOnebyName(event);
			if(check!=null) {
				log.info("-----EVENT HAS BEEN FOUND AND IT ALREADY EXISTS");
				throw new EventAlreadyExists();
			}	
			
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
	@PutMapping("/updateEvent/{code}")
	public void updateGame(@RequestBody Event event, @PathVariable String code) {
		eventServices.deleteEvent(code);
		eventServices.save(event);
	}

	// Listar eventos por género
	@Operation(summary = "List events by genre", description = "returns a json with all events by genre in the database", tags = {
			"Event" })
	@GetMapping("/events/genre/{genre}")
	public List<EventResponse> findByGenre(@PathVariable String genre) {
		log.info("---------GetEventByGenre");
		List<EventResponse> e = eventServices.findByGenre(genre);
		
		if(e.isEmpty()) {
			throw new EventNotFound(genre);
		}
		return eventServices.findByGenre(genre);
	}

	// Listar eventos por nombre
	@Operation(summary = "List events by name", description = "returns a json with all events by name in the database", tags = {
			"Event" })
	@GetMapping("/events/name/{name}")

	public EventResponse findByName(@PathVariable String name){
		
		log.info("---------GetEventByName");
		EventResponse e = eventServices.findByName(name);
		
		if(e==null) {
			throw new EventNotFound(name);
		}
		return eventServices.findByName(name);
	}
	
	// Listar eventos por venue[1] -> city
	//@Operation(summary = "List events by venue", description = "returns a json with all events by venue", tags = {
	//		"Event" })
	//@GetMapping("/events/venue")
	public List<Event> findByVenue(@PathVariable String[] venue){
		
		log.info("---------GetEventByVenue");
		List<Event> e = eventServices.findByVenue(venue);
			
		return e;
	}
		
	// Listar eventos por venue[1] -> city
	@Operation(summary = "List events by city", description = "returns a json with all events by city", tags = {
			"Event" })
	@GetMapping("/events/city/{city}")

	public List<Event> findByCity(@PathVariable String city){
			String[] element;
			List<String[]> listVenues = new ArrayList<String[]>();
			List<List<Event>> ev = new ArrayList<List<Event>>();
			List<Event> list = new ArrayList<Event>();
			log.info("---------GetEventByCity");
			
			List<Event> e = this.findEverything();
			if(e.isEmpty()) {
				throw new EventNotFound(city);
			}
			
			
			for (int i = 0; i< e.size(); i++) {
				
		             try {           	
		            	 element = e.get(i).getVenue();
		            	 if(element.length >= 2) {
						     if(element[1].compareToIgnoreCase(city) == 0 ) {
						            listVenues.add(element);
						     }  
		            	 }
		             }catch(NullPointerException ex) {
		            	 
		             }
		     }
			

			for(int i = 0; i < listVenues.size(); i++) {
				try {	
					if(!ev.contains(this.findByVenue(listVenues.get(i))))
						ev.add( this.findByVenue(listVenues.get(i)));
				
				}catch(NullPointerException ex) {
					
				}
			}
			
			
			for(int i = 0; i < ev.size(); i++) {
					
				for(int j = 0; j < ev.get(i).size(); j++) {
					try {
					list.add(ev.get(i).get(j));
					
					}catch(NullPointerException ex)	{
						
					}
				}
			}
			
			
			
			if(ev.isEmpty()) {
				throw new EventNotFound(city);
			}
			
			return list;
		}	
		
}
