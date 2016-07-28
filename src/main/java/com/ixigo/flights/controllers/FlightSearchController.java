package com.ixigo.flights.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/flights")
public class FlightSearchController {

	@RequestMapping(
		method = { RequestMethod.GET },
		produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void search(HttpServletRequest request, HttpServletResponse response) {
	}
}
