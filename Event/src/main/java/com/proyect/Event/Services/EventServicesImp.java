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
	public void deleteEvent(String code) {
		// TODO Auto-generated method stub
		er.deleteEventByCode(code);
	}


	@Override
	public List<Event> findByGenre(String genre) {
		
		return er.findByGenre(genre);
	}
	
}
