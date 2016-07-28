
package com.ixigo.flights.exceptions;

public class AirPortSearchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AirPortSearchException() {
		super("Problem while fetching airport information.");
	}

	public AirPortSearchException(String message) {
		super(message);
	}

}
