package com.ixigo.flights.exceptions;

public class FlightSearchRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FlightSearchRequestException() {
		super("Problem while fetching flight information.");
	}

	public FlightSearchRequestException(String message) {
		super(message);
	}

}
