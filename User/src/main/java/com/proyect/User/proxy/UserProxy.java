package com.proyect.User.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyect.User.model.Shopping;
import com.proyect.User.response.ShoppingResponse;

@FeignClient(name="event", url= "http://localhost:7777")
public interface UserProxy {

	@GetMapping("/event/events/name/{name}")
	public List<ShoppingResponse> findByName(@PathVariable String name);
}
