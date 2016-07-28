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
				airPortList.add(new Airport(airPortCode.trim(), cityName.trim(), airPortName.trim()));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return airPortList;
	}

	public static List<RawFlightData> readFlightDataCSV() {
		String flightsDataCsv = "flightsData.csv";
		List<RawFlightData> rawFlightDataList = new ArrayList<RawFlightData>();
		try {
			CsvReader csvReader = new CsvReader(flightsDataCsv);
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				rawFlightDataList.add(new RawFlightData(csvReader.get("originCode"), csvReader.get("destinationCode"), csvReader.get("takeoffTime"),
						csvReader.get("landingTime"), Double.parseDouble(csvReader.get("price")), csvReader.get("airlineCode"),
						csvReader.get("class"), csvReader.get("flightNumber")));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return rawFlightDataList;
	}

	/**
	 * Method is not in use currently
	 * @param time
	 * @param currentDate
	 * @param simpleDateFormat
	 * @return
	 */
	@SuppressWarnings("unused")
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
