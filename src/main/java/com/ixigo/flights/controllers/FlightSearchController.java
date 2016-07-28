package com.ixigo.flights.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ixigo.flights.exceptions.FlightSearchRequestException;
import com.ixigo.flights.models.FlightSearchRequest;
import com.ixigo.flights.models.FlightSearchResponse;
import com.ixigo.flights.services.FlightSearchService;

@Controller
@RequestMapping("/flights")
public class FlightSearchController {
	
	private final static Logger logger = LoggerFactory.getLogger(FlightSearchController.class);
	
	@Autowired
	private FlightSearchService flightSearchService;

	@RequestMapping(
		method = { RequestMethod.POST },
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public FlightSearchResponse search(@RequestBody FlightSearchRequest flightBookRequest, HttpServletRequest request, HttpServletResponse response) {
		try{
			return flightSearchService.searchFlights(flightBookRequest);
		}catch(Exception exception){
			logger.error("Exception occured while searching Flights {}",exception);
			throw new FlightSearchRequestException();
		}
	}
}
