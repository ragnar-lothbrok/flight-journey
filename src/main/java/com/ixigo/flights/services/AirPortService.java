package com.ixigo.flights.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ixigo.flights.models.Airport;
import com.ixigo.flights.utilities.FlightCSVReaderUtility;

@Service
public class AirPortService {

	public List<Airport> searchAirPorts(String airport){
		return FlightCSVReaderUtility.readAirPortCSV();
	}
}
