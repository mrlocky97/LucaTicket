package com.proyect.Event.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.proyect.Event.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String>{

	Long deleteEventByCode(String code);
	
	List<Event> findByGenre(String genre);

	List<Event> findByName(String name);
	
	Event findOneByName(String name);
	
	List<Event> findByVenue(String[] venue);

}
