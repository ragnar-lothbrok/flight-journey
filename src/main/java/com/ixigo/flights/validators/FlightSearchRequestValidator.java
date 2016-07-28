package com.ixigo.flights.validators;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.ixigo.flights.exceptions.FlightSearchRequestException;
import com.ixigo.flights.models.FlightSearchRequest;

/**
 * This will validate Flight Search Request.
 * @author raghunandangupta
 *
 */
@Service
public class FlightSearchRequestValidator implements IValidator<FlightSearchRequest> {

	public Boolean isValid(FlightSearchRequest obj) {
		boolean isFlightSearchValid = true;
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		if (obj == null) {
			isFlightSearchValid = false;
		} else {
			FlightSearchRequest flightSearchRequest = (FlightSearchRequest) obj;
			if (flightSearchRequest.getDepartureFlightBooking() == null) {
				isFlightSearchValid = false;
			} else {
				if ((flightSearchRequest.getDepartureFlightBooking().getJourneyDate() != null
						&& flightSearchRequest.getDepartureFlightBooking().getJourneyDate().longValue() > currentTime)
						|| (flightSearchRequest.getArrivalFlightBooking().getJourneyDate() != null
								&& flightSearchRequest.getDepartureFlightBooking().getJourneyDate().longValue() > flightSearchRequest
										.getArrivalFlightBooking().getJourneyDate().longValue()
								&& flightSearchRequest.getArrivalFlightBooking().getJourneyDate().longValue() > currentTime)) {
					isFlightSearchValid = false;
				}
			}
		}
		if (!isFlightSearchValid) {
			throw new FlightSearchRequestException();
		}
		return true;
	}

}
