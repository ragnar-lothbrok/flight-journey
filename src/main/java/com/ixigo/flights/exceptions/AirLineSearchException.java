
package com.ixigo.flights.exceptions;

public class AirLineSearchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AirLineSearchException() {
		super("Problem while fetching airline information.");
	}
	
	public AirLineSearchException(String message) {
		super(message);
	}

}
