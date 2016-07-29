package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This will contain Flght class details
 * @author raghunandangupta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private String flightClassCode;

	private String flightClassName;

	public String getFlightClassCode() {
		return flightClassCode;
	}

	public void setFlightClassCode(String flightClassCode) {
		this.flightClassCode = flightClassCode;
	}

	public String getFlightClassName() {
		return flightClassName;
	}

	public void setFlightClassName(String flightClassName) {
		this.flightClassName = flightClassName;
	}

	public FlightClass(String flightClassCode, String flightClassName) {
		super();
		this.flightClassCode = flightClassCode;
		this.flightClassName = flightClassName;
	}

	public FlightClass() {
		
	}
}
