package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class is a single flight search request.
 * @author raghunandangupta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	private Airport sourceAirport;

	private Airport destintionAirport;

	private Long journeyDate;

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestintionAirport() {
		return destintionAirport;
	}

	public void setDestintionAirport(Airport destintionAirport) {
		this.destintionAirport = destintionAirport;
	}

	public Long getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Long journeyDate) {
		this.journeyDate = journeyDate;
	}

	public FlightSearch(Airport sourceAirport, Airport destintionAirport, Long journeyDate) {
		super();
		this.sourceAirport = sourceAirport;
		this.destintionAirport = destintionAirport;
		this.journeyDate = journeyDate;
	}

	public FlightSearch() {

	}
}
