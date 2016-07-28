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
import com.ixigo.flights.exceptions.AirLineSearchException;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.utilities.FlightCSVReaderUtility;

@Service
public class AirlineService {

	private LoadingCache<String, List<AirLine>> airLineCache;

	@Autowired
	public AirlineService(@Value("${flight.airline.timeout}") Integer timeout) {
		airLineCache = CacheBuilder.newBuilder().refreshAfterWrite(timeout, TimeUnit.MINUTES).build(new CacheLoader<String, List<AirLine>>() {
			@Override
			public List<AirLine> load(String query) throws Exception {
				return FlightCSVReaderUtility.readAirLineCSV();
			}
		});
	}

	public List<AirLine> fetchAirLine() {
		try {
			return airLineCache.get(FlightConstants.AIRLINE_KEY);
		} catch (Exception exception) {
			throw new AirLineSearchException();
		}
	}
}
