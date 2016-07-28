package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ixigo.flights.validators.IValidatable;
import com.ixigo.flights.validators.IValidator;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSearchRequest implements Serializable, IValidatable<FlightSearchRequest> {

	private static final long serialVersionUID = 1L;

	private FlightSearch departureFlightBooking;

	private FlightSearch arrivalFlightBooking;

	private FlightPassenger flightPassenger;

	private AirLine airLine;

	private String flightClass;

	public FlightSearch getDepartureFlightBooking() {
		return departureFlightBooking;
	}

	public void setDepartureFlightBooking(FlightSearch departureFlightBooking) {
		this.departureFlightBooking = departureFlightBooking;
	}

	public FlightSearch getArrivalFlightBooking() {
		return arrivalFlightBooking;
	}

	public void setArrivalFlightBooking(FlightSearch arrivalFlightBooking) {
		this.arrivalFlightBooking = arrivalFlightBooking;
	}

	public FlightPassenger getFlightPassenger() {
		return flightPassenger;
	}

	public void setFlightPassenger(FlightPassenger flightPassenger) {
		this.flightPassenger = flightPassenger;
	}

	public AirLine getAirLine() {
		return airLine;
	}

	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	@Override
	public Boolean validate(IValidator<FlightSearchRequest> validator) {
		return validator.isValid(this);
	}

}
