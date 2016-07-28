package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Airport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String airportCode;

	private String cityName;

	private String airportName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public Airport() {
	}

	public Airport(String airportCode, String cityName, String airportName) {
		super();
		this.airportCode = airportCode;
		this.cityName = cityName;
		this.airportName = airportName;
	}

}
