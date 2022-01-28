package com.proyect.Event.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class EvenFoundOK{

	public HttpStatus evenFoundiscreatedOK() {
		return HttpStatus.valueOf("ITEM HAS BEEN ADDED TO LIST");
	}
}
