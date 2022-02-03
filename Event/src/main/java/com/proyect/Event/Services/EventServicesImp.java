package com.proyect.Event.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Event.adapter.EventAdapter;
import com.proyect.Event.model.Event;
import com.proyect.Event.repository.EventRepository;
import com.proyect.Event.response.EventResponse;

@Service
public class EventServicesImp implements EventServices{
	
	@Autowired
	EventRepository er;
	
	@Autowired
	EventAdapter eventAdapter;
	
	@Override
	public Event save(Event event) {
		return er.save(event);
		
	}
	

	@Override
	public List<EventResponse> findAll() {
		final List<Event> all = er.findAll();
		return eventAdapter.of(all);
	}
	
	@Override
	public List<Event> findEveryThing() {
		final List<Event> all = er.findAll();
		return all;
	}

	

	@Override
	public void deleteEvent(String code) {
		// TODO Auto-generated method stub
		er.deleteEventByCode(code);
	}


	@Override
	public List<EventResponse> findByGenre(String genre) {
		final List<Event> all = er.findByGenre(genre);
		return eventAdapter.of(all);
	}


	@Override
	public EventResponse findByName(String name) {
		final Event all = er.findOneByName(name);
		return eventAdapter.of(all);
	}


	@Override
	public Event findOnebyName(Event name) {
		// TODO Auto-generated method stub
		return er.findOneByName(name.getName());
	}


	@Override
	public List<Event> findByVenue(String[] venue) {
		final List<Event> e = er.findByVenue(venue);
		return e;
	}
	
	
	
}
