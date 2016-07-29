package com.ixigo.flights.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.models.RawFlightData;

/**
 * Supporting method for Flight related information.
 * Can be Database or Third Party APIs
 * @author raghunandangupta
 *
 */
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("dd MMM yyyy kk:mm");
		Calendar currDate = Calendar.getInstance();
		String currentDate = simpleDateFormat.format(currDate.getTime());
		currDate.add(Calendar.DATE, 1);
		String nextDate = simpleDateFormat.format(currDate.getTime());

		String flightsDataCsv = "flightsData.csv";
		List<RawFlightData> rawFlightDataList = new ArrayList<RawFlightData>();
		try {
			CsvReader csvReader = new CsvReader(flightsDataCsv);
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				rawFlightDataList.add(new RawFlightData(csvReader.get("originCode"), csvReader.get("destinationCode"), csvReader.get("takeoffTime"),
						csvReader.get("landingTime"), Double.parseDouble(csvReader.get("price")), csvReader.get("airlineCode"),
						csvReader.get("class"), csvReader.get("flightNumber"), convertStringToDate(csvReader.get("takeoffTime"),
								csvReader.get("landingTime"), currentDate, currDate.getTime(), nextDate, simpleDateTimeFormat)));
			}
		} catch (Exception exception) {
			logger.error("Excepiton occred  {}", exception);
		}
		return rawFlightDataList;
	}

	/**
	 * 
	 * @param time
	 * @param currentDate
	 * @param simpleDateTimeFormat
	 * @return
	 */
	private static String convertStringToDate(String takeOffTime, String landingTime, String currentDate, Date nextD, String nextDate,
			SimpleDateFormat simpleDateTimeFormat) {
		Date takeOffDate = null;
		Date landingDate = null;
		Long diffMs = null;
		try {
			takeOffTime = takeOffTime.trim().replace("  ", " ").replace("AM", "").replace("PM", "");
			takeOffTime = currentDate + " " + takeOffTime;
			takeOffDate = simpleDateTimeFormat.parse(takeOffTime);

			landingTime = landingTime.trim().replace("  ", " ").replace("AM", "").replace("PM", "");

			if (!landingTime.startsWith("00")) {
				landingTime = currentDate + " " + landingTime;
				landingDate = simpleDateTimeFormat.parse(landingTime);
				diffMs = landingDate.getTime() - takeOffDate.getTime();
			} else {
				landingTime = nextDate + " " + landingTime;
				landingDate = simpleDateTimeFormat.parse(landingTime);
				diffMs = landingDate.getTime() - takeOffDate.getTime();
			}

			long diffSec = diffMs / 1000;
			long min = diffSec / 60;
			long hour = min / 60;
			min = min % 60;
			return hour + "h " + min + " m";
		} catch (ParseException exception) {
			logger.error("Exception occured while parsing date {}", exception);
		}
		return null;
	}
}
