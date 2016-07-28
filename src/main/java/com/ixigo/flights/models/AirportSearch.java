package com.ixigo.flights.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirportSearch extends Airport {

	private static final long serialVersionUID = 1L;

	private Long popularity;

	private Date updationDate;

	public Long getPopularity() {
		return popularity;
	}

	public void setPopularity(Long popularity) {
		this.popularity = popularity;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public AirportSearch(String airportCode, String cityName, String airportName, Long popularity, Date updationDate) {
		super(airportCode, cityName, airportName);
		this.popularity = popularity;
		this.updationDate = updationDate;
	}

}
