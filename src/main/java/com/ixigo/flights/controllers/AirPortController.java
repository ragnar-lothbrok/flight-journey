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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ixigo.flights.exceptions.AirPortSearchException;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.services.AirPortService;

@Controller
@RequestMapping("/airport")
public class AirPortController {
	
	private final static Logger logger = LoggerFactory.getLogger(AirPortController.class);

	@Autowired
	private AirPortService airPortService;

	@RequestMapping(
		method = { RequestMethod.GET },
		produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Airport> search(@RequestParam("airPort") String airPort, HttpServletRequest request, HttpServletResponse response) {
		try {
			return airPortService.searchAirPorts(airPort);
		} catch (Exception exception) {
			logger.error("Exception occured while searching AirPorts {}",exception);
			throw new AirPortSearchException();
		}
	}
}
