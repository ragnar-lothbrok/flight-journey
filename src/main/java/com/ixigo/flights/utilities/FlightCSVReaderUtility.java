package com.ixigo.flights.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.models.AirportSearch;
import com.ixigo.flights.models.RawFlightData;

public class FlightCSVReaderUtility {

	private final static Logger logger = LoggerFactory.getLogger(FlightCSVReaderUtility.class);

	public static List<AirLine> readAirLineCSV() {
		String airLineCsv = "airlineMap.csv";

		List<AirLine> airLineList = new ArrayList<AirLine>();
		try {
			CsvReader csvReader = new CsvReader(airLineCsv);
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				String airlineCode = csvReader.get("airlineCode");
				String airlineName = csvReader.get("airlineName");
				airLineList.add(new AirLine(airlineCode.trim(), airlineName.trim()));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return airLineList;
	}

	public static List<Airport> readAirPortCSV() {
		String airPortCsv = "airportMap.csv";
		List<Airport> airPortList = new ArrayList<Airport>();
		try {
			CsvReader csvReader = new CsvReader(airPortCsv);
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				String airPortCode = csvReader.get("airportCode");
				String cityName = csvReader.get("cityName");
				String airPortName = csvReader.get("airportName");
				airPortList.add(new AirportSearch(airPortCode.trim(), cityName.trim(), airPortName.trim(), null, null));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return airPortList;
	}

	public static List<RawFlightData> readFlightDataCSV() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mma");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String currentDate = dateFormat.format(new Date());

		String flightsDataCsv = "flightsData.csv";
		List<RawFlightData> rawFlightDataList = new ArrayList<RawFlightData>();
		try {
			CsvReader csvReader = new CsvReader(flightsDataCsv);
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				rawFlightDataList.add(new RawFlightData(csvReader.get("originCode"), csvReader.get("destinationCode"),
						convertStringToDate(csvReader.get("takeoffTime"), currentDate, simpleDateFormat),
						convertStringToDate(csvReader.get("landingTime"), currentDate, simpleDateFormat), Double.parseDouble(csvReader.get("price")),
						csvReader.get("airlineCode"), csvReader.get("class"), csvReader.get("flightNumber")));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return rawFlightDataList;
	}

	private static Date convertStringToDate(String time, String currentDate, SimpleDateFormat simpleDateFormat) {
		time = time.trim().replace(" ", "");
		time = currentDate + " " + time;
		try {
			return simpleDateFormat.parse(time);
		} catch (ParseException exception) {
			logger.error("Exception occured while parsing date {}", exception);
		}
		return null;
	}
}
