package com.ixigo.flights.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ixigo.flights.exceptions.AirLineSearchException;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.services.AirlineService;

@Controller
@RequestMapping("/airline")
public class AirlineController {

	private final static Logger logger = LoggerFactory.getLogger(AirlineController.class);

	@Autowired
	AirlineService airlineService;

	@RequestMapping(
		method = { RequestMethod.GET },
		produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<AirLine> fetchAirLines(HttpServletRequest request, HttpServletResponse response) {
		try {
			return airlineService.fetchAirLine();
		} catch (Exception exception) {
			logger.error("Exception occured while searching Flights {}", exception);
			throw new AirLineSearchException();
		}
	}

}
