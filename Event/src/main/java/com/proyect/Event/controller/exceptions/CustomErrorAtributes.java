package com.proyect.Event.controller.exceptions;

import java.text.DateFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.proyect.Event.controller.EventController;

@Component
public class CustomErrorAtributes extends DefaultErrorAttributes {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		logger.info("------ getErrorAttributes(): " + options);
		
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
		logger.info("------ getErrorAttributes(): " + options);		

		
		Object timestamp = errorAttributes.get("timestamp");
		if (timestamp == null) {
			errorAttributes.put("timestamp", DateFormat.getTimeInstance() + "NO SUCCESS ----->NULL");
		} else {
			errorAttributes.put("timestamp", timestamp + "  NO SUCCESS ----->ERROR" );
		}
		  // Eliminamos la traza para simplificar la salida
			errorAttributes.remove("trace");

		// Insertamos nueva clave	
		errorAttributes.put("jdk", System.getProperty("java.version"));

		return errorAttributes;
	}

}
