package com.ixigo.flights.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Flight Search response containing Flight related information
 * @author raghunandangupta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSearchResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Flight> departureFlightList = new ArrayList<Flight>();

	List<Flight> arrivalFlightList = new ArrayList<Flight>();

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
