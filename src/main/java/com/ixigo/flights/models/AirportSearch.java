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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCode == null) ? 0 : airportCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		return true;
	}

	public AirportSearch() {

	}

}
