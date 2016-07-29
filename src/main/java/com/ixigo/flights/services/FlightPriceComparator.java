package com.ixigo.flights.services;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.ixigo.flights.models.Flight;

/**
 * This will sort flight response on the basis of Price
 * @author raghunandangupta
 *
 */
@Service
public class FlightPriceComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		return o1.getPrice().compareTo(o2.getPrice());
	}

}