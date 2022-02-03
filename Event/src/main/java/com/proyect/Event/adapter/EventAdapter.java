package com.proyect.Event.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyect.Event.model.Event;
import com.proyect.Event.response.EventResponse;

@Component
public class EventAdapter {
	
	public EventResponse of(Event event) {
		EventResponse eventResponse = new EventResponse();
		eventResponse.setCode(event.getCode());
		eventResponse.setName(event.getName());
		eventResponse.setPrice(event.getPrice());
		eventResponse.setShort_description(event.getShort_description());
		eventResponse.setLong_description(event.getLong_description());
		eventResponse.setDate(event.getDate());
		eventResponse.setTime(event.getTime());
		eventResponse.setGenre(event.getGenre());
		eventResponse.setPolicy(event.getPolicy());
		eventResponse.setVenue(event.getVenue());
		
		return eventResponse;
		
	}
	
	
	public List<EventResponse> of(List<Event> events) {
        return events
        		.stream()
                .map(p -> of(p))
                .collect(Collectors.toList());
    }
	
	
}
