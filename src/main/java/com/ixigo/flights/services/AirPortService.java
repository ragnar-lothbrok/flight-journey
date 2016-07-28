package com.ixigo.flights.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ixigo.flights.constants.FlightConstants;
import com.ixigo.flights.exceptions.AirPortSearchException;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.utilities.FlightCSVReaderUtility;

@Service
public class AirPortService {

	private LoadingCache<String, List<Airport>> airPortCache;

	@Autowired
	public AirPortService(@Value("${flight.airline.timeout}") Integer timeout) {
		airPortCache = CacheBuilder.newBuilder().refreshAfterWrite(timeout, TimeUnit.MINUTES).build(new CacheLoader<String, List<Airport>>() {
			@Override
			public List<Airport> load(String query) throws Exception {
				return FlightCSVReaderUtility.readAirPortCSV();
			}
		});
	}

	public List<Airport> searchAirPorts(String airport) {
		return FlightCSVReaderUtility.readAirPortCSV();
	}

	public List<Airport> allAirports() {
		try {
			return airPortCache.get(FlightConstants.AIRPORT_KEY);
		} catch (Exception exception) {
			throw new AirPortSearchException();
		}
	}
}
