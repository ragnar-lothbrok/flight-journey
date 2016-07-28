package com.ixigo.flights.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixigo.flights.exceptions.AirPortSearchException;
import com.ixigo.flights.models.FlightSearchRequest;
import com.ixigo.flights.models.FlightSearchResponse;
import com.ixigo.flights.models.RawFlightData;
import com.ixigo.flights.utilities.FlightCSVReaderUtility;
import com.ixigo.flights.validators.FlightSearchRequestValidator;

@Service
public class FlightSearchService {

	private final static Logger logger = LoggerFactory.getLogger(FlightSearchService.class);

	@Autowired
	FlightSearchRequestValidator flightSearchRequestValidator;

	public FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest) {
		FlightSearchResponse flightSearchResponse = null;
		try {
			List<RawFlightData> rawFlightDataList = FlightCSVReaderUtility.readFlightDataCSV();
			System.out.println(rawFlightDataList);
			flightSearchRequestValidator.validate(flightSearchRequest);
		} catch (Exception exception) {
			logger.error("Exception occured while searching Flight Search {}", exception);
			throw new AirPortSearchException();
		}
		return flightSearchResponse;
	}

}
