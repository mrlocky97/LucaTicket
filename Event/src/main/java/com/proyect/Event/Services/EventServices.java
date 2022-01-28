package com.proyect.Event.Services;

import java.util.List;


import com.proyect.Event.model.Event;
import com.proyect.Event.response.EventResponse;

public interface EventServices {

	public Event save(Event event);

	public List<EventResponse> findAll();

	public void deleteEvent(String code);

	public List<EventResponse> findByGenre(String genre);
	
	public List<EventResponse> findByName(String name);
}