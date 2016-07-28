package com.ixigo.flights.models;

import java.io.Serializable;
import java.util.List;

public class FlightSearchResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Flight> departureFlightList;
	
	List<Flight> arrivalFlightList;

	public List<Flight> getDepartureFlightList() {
		return departureFlightList;
	}

	public void setDepartureFlightList(List<Flight> departureFlightList) {
		this.departureFlightList = departureFlightList;
	}

	public List<Flight> getArrivalFlightList() {
		return arrivalFlightList;
	}

	public void setArrivalFlightList(List<Flight> arrivalFlightList) {
		this.arrivalFlightList = arrivalFlightList;
	}
	
	
}
