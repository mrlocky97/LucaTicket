package com.proyect.User.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyect.User.response.ShoppingResponse;

@FeignClient(name = "event", url = "http://localhost:7777")
public interface UserProxy {

	// lo conseguimos
	@GetMapping("/event/events/name/{name}")
	public ShoppingResponse findByName(@PathVariable String name);
}
