package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class will contain single Flight Data
 * @author raghunandangupta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	private Airport sourceAirport;

	private Airport destinationAirport;

	private AirLine airLine;

	private String flightNumber;

	private Double price;

	private String airlineClass;

	private String takeOffTime;

	private String landingTime;

	private String duration;

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAirlineClass() {
		return airlineClass;
	}

	public void setAirlineClass(String airlineClass) {
		this.airlineClass = airlineClass;
	}

	public String getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(String takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public String getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(String landingTime) {
		this.landingTime = landingTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
