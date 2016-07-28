package com.ixigo.flights.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixigo.flights.exceptions.AirPortSearchException;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.models.Flight;
import com.ixigo.flights.models.FlightSearch;
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

	@Autowired
	private AirPortService airPortService;

	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private FlightPriceComparator flightPriceComparator;

	public FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest) {
		FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
		try {
			List<RawFlightData> rawFlightDataList = FlightCSVReaderUtility.readFlightDataCSV();
			flightSearchRequest.validate(flightSearchRequestValidator);

			Boolean isArrival = false;
			if (flightSearchRequest.getArrivalFlightBooking() != null) {
				isArrival = true;
			}

			Flight flight = null;
			if (rawFlightDataList != null) {
				for (RawFlightData rawFlightData : rawFlightDataList) {
					if ((flightSearchRequest.getAirLine() == null || flightSearchRequest.getAirLine().getAirlineCode() == null)
							&& flightSearchRequest.getFlightClass() == null) {
						flight = fetchFlight(rawFlightData, flightSearchRequest.getDepartureFlightBooking());
						if (flight != null) {
							flightSearchResponse.getDepartureFlightList().add(flight);
						} else if (isArrival) {
							flight = fetchFlight(rawFlightData, flightSearchRequest.getArrivalFlightBooking());
							if (flight != null) {
								flightSearchResponse.getArrivalFlightList().add(flight);
							}
						}
					} else if (flightSearchRequest.getFlightClass() == null
							&& (flightSearchRequest.getAirLine() != null && flightSearchRequest.getAirLine().getAirlineCode() != null
									&& flightSearchRequest.getAirLine().getAirlineCode().equalsIgnoreCase(rawFlightData.getAirlineCode()))) {
						flight = fetchFlight(rawFlightData, flightSearchRequest.getDepartureFlightBooking());
						if (flight != null) {
							flightSearchResponse.getDepartureFlightList().add(flight);
						} else if (isArrival) {
							flight = fetchFlight(rawFlightData, flightSearchRequest.getArrivalFlightBooking());
							if (flight != null) {
								flightSearchResponse.getArrivalFlightList().add(flight);
							}
						}
					} else if (flightSearchRequest.getAirLine() == null
							&& flightSearchRequest.getFlightClass().equalsIgnoreCase(rawFlightData.getKlass())) {
						flight = fetchFlight(rawFlightData, flightSearchRequest.getDepartureFlightBooking());
						if (flight != null) {
							flightSearchResponse.getDepartureFlightList().add(flight);
						} else if (isArrival) {
							flight = fetchFlight(rawFlightData, flightSearchRequest.getArrivalFlightBooking());
							if (flight != null) {
								flightSearchResponse.getArrivalFlightList().add(flight);
							}
						}
					} else if (flightSearchRequest.getFlightClass().equalsIgnoreCase(rawFlightData.getKlass())
							&& flightSearchRequest.getAirLine().getAirlineCode().equalsIgnoreCase(rawFlightData.getAirlineCode())) {
						flight = fetchFlight(rawFlightData, flightSearchRequest.getDepartureFlightBooking());
						if (flight != null) {
							flightSearchResponse.getDepartureFlightList().add(flight);
						} else if (isArrival) {
							flight = fetchFlight(rawFlightData, flightSearchRequest.getArrivalFlightBooking());
							if (flight != null) {
								flightSearchResponse.getArrivalFlightList().add(flight);
							}
						}
					}
				}
			}
			if(flightSearchResponse.getArrivalFlightList() != null){
				flightSearchResponse.getArrivalFlightList().sort(flightPriceComparator);
			}
			if(flightSearchResponse.getDepartureFlightList() != null){
				flightSearchResponse.getDepartureFlightList().sort(flightPriceComparator);
			}
		} catch (Exception exception) {
			logger.error("Exception occured while searching Flight Search {}", exception);
			throw new AirPortSearchException();
		}
		return flightSearchResponse;
	}

	/**
	 * This method will match search requests with the Flights data from Web
	 * services.
	 * 
	 * @param rawFlightData
	 * @param flightBooking
	 * @return
	 */
	private Flight fetchFlight(RawFlightData rawFlightData, FlightSearch flightBooking) {
		Flight flight = null;
		if (flightBooking.getSourceAirport().getAirportCode().equalsIgnoreCase(rawFlightData.getOriginCode())
				&& flightBooking.getDestintionAirport().getAirportCode().equalsIgnoreCase(rawFlightData.getDestinationCode())) {
			flight = new Flight();
			flight.setLandingTime(rawFlightData.getLandingTime());
			flight.setTakeOffTime(rawFlightData.getTakeoffTime());
			flight.setPrice(rawFlightData.getPrice());

			List<Airport> airPortList = airPortService.allAirports();
			Airport airport = new Airport();
			airport.setAirportCode(rawFlightData.getOriginCode());

			// Set Source AirPort
			int index = airPortList.indexOf(airport);
			if (index != -1) {
				flight.setSourceAirport(airPortList.get(index));
			}

			// Set Destination AirPort
			airport.setAirportCode(rawFlightData.getDestinationCode());
			index = airPortList.indexOf(airport);
			if (index != -1) {
				flight.setDestinationAirport(airPortList.get(index));
			}

			// Set Air Line
			List<AirLine> airLineList = airlineService.fetchAirLine();
			AirLine airLine = new AirLine();
			airLine.setAirlineCode(rawFlightData.getAirlineCode());
			index = airLineList.indexOf(airLine);
			if (index != -1) {
				flight.setAirLine(airLineList.get(index));
			}

			flight.setFlightNumber(rawFlightData.getFlightNumber());

			flight.setAirlineClass(rawFlightData.getKlass());

		}
		return flight;
	}

}
