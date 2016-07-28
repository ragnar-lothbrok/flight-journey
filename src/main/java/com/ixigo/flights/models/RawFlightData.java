package com.ixigo.flights.models;

import java.io.Serializable;
import java.util.Date;

public class RawFlightData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String originCode;
	private String destinationCode;
	private Date takeoffTime;
	private Date landingTime;
	private Double price;
	private String airlineCode;
	private String klass;
	private String flightNumber;

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public Date getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(Date takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	public Date getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(Date landingTime) {
		this.landingTime = landingTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public RawFlightData() {

	}

	public RawFlightData(String originCode, String destinationCode, Date takeoffTime, Date landingTime, Double price, String airlineCode,
			String klass, String flightNumber) {
		super();
		this.originCode = originCode;
		this.destinationCode = destinationCode;
		this.takeoffTime = takeoffTime;
		this.landingTime = landingTime;
		this.price = price;
		this.airlineCode = airlineCode;
		this.klass = klass;
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "RawFlightData [originCode=" + originCode + ", destinationCode=" + destinationCode + ", takeoffTime=" + takeoffTime + ", landingTime="
				+ landingTime + ", price=" + price + ", airlineCode=" + airlineCode + ", klass=" + klass + ", flightNumber=" + flightNumber + "]";
	}

}
