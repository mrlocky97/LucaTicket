package com.proyect.User.controller.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



import feign.FeignException;

@ControllerAdvice
public class CustomErrorExceptionHandlerShopping  extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FeignException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException {
		logger.info("------ UserNotFoundException() ");
		
		response.sendError(HttpStatus.NOT_FOUND.value(), "CANT ADD EVENT TO CART");
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.info("------ handleMethodArgumentNotValid()");
		
		CustomErrorJSON customError = new CustomErrorJSON();

		customError.setTimestamp(new Date());
		customError.setStatus(status.value());
		customError.setError(status.name());
		
		//Aqui INDICAMOS EL CAMPO EN EL QUE FALLA NUESTRO DOC
		List<String> messages = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			messages.add(error.getField() + ": " + error.getDefaultMessage());
		}
		customError.setMessage(messages);
		
		//Mostramos datos originales
		request.getDescription(false);
		String uri = request.getDescription(false);
		uri = uri.substring(uri.lastIndexOf("=")+1);
		customError.setPath(uri);

		return new ResponseEntity<>(customError, headers, status);

	}

}
