package com.ixigo.flights.validators;

import org.springframework.stereotype.Service;

import com.ixigo.flights.exceptions.FlightSearchRequestException;
import com.ixigo.flights.models.FlightSearchRequest;

@Service
public class FlightSearchRequestValidator implements Validator {

	@Override
	public void validate(Object obj) throws FlightSearchRequestException {
		boolean isFlightSearchValid = true;

		if (obj == null) {
			isFlightSearchValid = false;
		} else {
			FlightSearchRequest flightSearchRequest = (FlightSearchRequest) obj;
			if (flightSearchRequest.getDepartureFlightBooking() == null) {
				isFlightSearchValid = false;
			} else {
				// TODO
			}
		}
		if (!isFlightSearchValid) {
			throw new FlightSearchRequestException();
		}
	}

}
