package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Air line related information
 * @author raghunandangupta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private String airlineCode;

	private String airlineName;

	private String imageUrl;

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public AirLine() {

	}

	public AirLine(String airlineCode, String airlineName) {
		super();
		this.airlineCode = airlineCode;
		this.airlineName = airlineName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airlineCode == null) ? 0 : airlineCode.hashCode());
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
		AirLine other = (AirLine) obj;
		if (airlineCode == null) {
			if (other.airlineCode != null)
				return false;
		} else if (!airlineCode.equals(other.airlineCode))
			return false;
		return true;
	}

}
