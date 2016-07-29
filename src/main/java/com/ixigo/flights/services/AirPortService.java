package com.ixigo.flights.services;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * This will have method related to Airport
 * 
 * @author raghunandangupta
 *
 */
@Service
public class AirPortService {

	private final static Logger logger = LoggerFactory.getLogger(AirPortService.class);

	private LoadingCache<String, List<Airport>> airPortCache;

	@Autowired
	private TextIndexAndSearchService textIndexAndSearchService;

	/**
	 * This will cache Airport Data.
	 * @param timeout
	 */
	@Autowired
	public AirPortService(@Value("${flight.airline.timeout}") Integer timeout) {
		airPortCache = CacheBuilder.newBuilder().refreshAfterWrite(timeout, TimeUnit.MINUTES).build(new CacheLoader<String, List<Airport>>() {
			@Override
			public List<Airport> load(String query) throws Exception {
				List<Airport> airPosrtList = FlightCSVReaderUtility.readAirPortCSV();
				textIndexAndSearchService.index(airPosrtList);
				return airPosrtList;
			}
		});
	}

	/**
	 * This method will search Airport for a Search Key
	 * @param searchKey
	 * @return
	 * @throws ExecutionException
	 */
	public List<Airport> searchAirPorts(String searchKey) throws ExecutionException {
		List<Airport> airportList = textIndexAndSearchService.search(searchKey);
		if (airportList == null || airportList.size() == 0) {
			airportList = airPortCache.get(FlightConstants.AIRPORT_KEY);
			airportList.subList(0, (airportList.size() > 10 ? 10 : airportList.size()));
		}
		return airportList;
	}

	/**
	 * This will return All Airports
	 * @return
	 */
	public List<Airport> allAirports() {
		try {
			List<Airport> airPosrtList = airPortCache.get(FlightConstants.AIRPORT_KEY);
			return airPosrtList;
		} catch (Exception exception) {
			logger.error("Exception occured while searching AirPorts {}", exception);
			throw new AirPortSearchException();
		}
	}
}
