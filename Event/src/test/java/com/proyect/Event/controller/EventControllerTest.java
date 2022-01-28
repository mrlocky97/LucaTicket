package com.proyect.Event.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

class EventControllerTest {

	@Test
	void shouldReturn200WhenWeCallTheRouteRight() {
		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().get("/event/events").then().statusCode(200);

	}

	@Test
	void shouldReturn405WhenPutARouteWrong() {
		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().get("/event/event").then().statusCode(405);

	}

	@Test
	void shouldReturn201WhenPostNewEvent() {

		JSONObject request = new JSONObject();

		request.put("code", "324235f");
		request.put("name", "Cupra");

		System.out.println(request.toJSONString());

		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().header("Content-Type", "aplication/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString()).when().post("/event/add/").then().statusCode(201)
				.log().all();

	}

	@Test
	void shouldReturn200WhenUpdeteAnEvent() {

		JSONObject request = new JSONObject();

		request.put("code", "324235f");
		request.put("name", "Cupra");

		System.out.println(request.toJSONString());

		JSONObject request1 = new JSONObject();

		request1.put("code", "324235f");
		request1.put("name", "Otro");

		System.out.println(request1.toJSONString());

		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().header("Content-Type", "aplication/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString()).when().post("/event/add/").then().statusCode(201)
				.log().all();
		RestAssured.given().header("Content-Type", "aplication/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request1.toJSONString()).when().put("/event/").then().statusCode(200)
				.log().all();
	}
	
	
	@Test
	void shouldRemoveAnExistingEvent() {
		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().log().all()
				.accept(ContentType.JSON)
				.pathParam("code", "324235f")
			.when()
				.delete("/event/deleteEvent/{code}")
			.then().log()
				.ifValidationFails().statusCode(200);
	}
	
	@Test
	void shoulAddNewEventEvenDoesntHaveAllFields() {

		JSONObject request = new JSONObject();

		request.put("albondiga", "324235f");
		request.put("name", "Cupra");

		System.out.println(request.toJSONString());

		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given().header("Content-Type", "aplication/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString()).when().post("/event/add/").then().statusCode(201)
				.log().all();

	}
	
	@Test
	void shouldReturn200WhenWeCallTheRouteFindByGenreRight() {
		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given()
		.accept(ContentType.JSON)
		.pathParam("genre", "pop")
		.get("/event/events/genre/{genre}")
		.then().statusCode(200);

	}

	@Test
	void shouldReturn404WhenPutARouteFindByGenreWrong() {
		RestAssured.baseURI = "http://localhost:8080";

		RestAssured.given()
		.accept(ContentType.JSON)
		.pathParam("genre", "pop")
		.get("/event/event/genre/{genre}")
		.then().statusCode(404);

	}

}
