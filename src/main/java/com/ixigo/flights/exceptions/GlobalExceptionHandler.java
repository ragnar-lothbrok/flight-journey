package com.ixigo.flights.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ixigo.flights.models.ErrorResource;

/**
 * This will haddle global exceptions.
 * @author raghunandangupta
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ AirLineSearchException.class })
	protected ResponseEntity<Object> handleAirLineRequestException(RuntimeException e, WebRequest request) {
		ErrorResource error = new ErrorResource("Airline not found.", "Problem while fetching airline information.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ AirPortSearchException.class })
	protected ResponseEntity<Object> handleAirPortRequestException(RuntimeException e, WebRequest request) {
		ErrorResource error = new ErrorResource("Airport not found.", "Problem while fetching airport information.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ FlightSearchRequestException.class })
	protected ResponseEntity<Object> handleFlightBookingRequestException(RuntimeException e, WebRequest request) {
		ErrorResource error = new ErrorResource("Flight search request failed.", "Problem while fetching flight information.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
	}

}
