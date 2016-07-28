package com.ixigo.flights.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightPassenger implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer adultcount;

	private Integer infantCount;

	private Integer childCount;

	public Integer getAdultcount() {
		return adultcount;
	}

	public void setAdultcount(Integer adultcount) {
		this.adultcount = adultcount;
	}

	public Integer getInfantCount() {
		return infantCount;
	}

	public void setInfantCount(Integer infantCount) {
		this.infantCount = infantCount;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public FlightPassenger() {

	}

	public FlightPassenger(Integer adultcount, Integer infantCount, Integer childCount) {
		super();
		this.adultcount = adultcount;
		this.infantCount = infantCount;
		this.childCount = childCount;
	}

}
