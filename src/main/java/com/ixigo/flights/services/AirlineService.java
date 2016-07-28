package com.ixigo.flights.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ixigo.flights.exceptions.AirLineSearchException;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.utilities.FlightCSVReaderUtility;

@Service
public class AirlineService {

	public List<AirLine> fetchAirLine() {
		try {
			return FlightCSVReaderUtility.readAirLineCSV();
		} catch (Exception exception) {
			throw new AirLineSearchException();
		}
	}
}
