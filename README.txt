http://localhost:8888/fms/airport?airPort=mu
[{"airportCode":"BOM","cityName":"Mumbai","airportName":"DEL Chatarpati Shivaji Airport"}]

http://localhost:8888/fms/airline

http://localhost:8888/fms/flights

{
	"departureFlightBooking": {
		"sourceAirport": {
			"airportCode": "DEL"
		},
		"destintionAirport": {
			"airportCode": "BOM"
		},
		"journeyDate": 1469719382848
	},
	"arrivalFlightBooking": {
		"sourceAirport": {
			"airportCode": "DEL"
		},
		"destintionAirport": {
			"airportCode": "BOM"
		},
		"journeyDate": 1469719382848
	},
	"flightPassenger": {
		"adultcount": 5
	},
	"airLine": {
		"airlineCode": "G8",
		"airlineName": ""
	},
	"flightClass": "Economy"
}


=========================================================

{
	"departureFlightBooking": {
		"sourceAirport": {
			"airportCode": "DEL"
		},
		"destintionAirport": {
			"airportCode": "BOM"
		},
		"journeyDate": 1469719382848
	},
	"arrivalFlightBooking": {
		"sourceAirport": {
			"airportCode": "BOM"
		},
		"destintionAirport": {
			"airportCode": "DEL"
		},
		"journeyDate": 1469719388848
	},
	"flightPassenger": {
		"adultcount": 5
	},
	"flightClass": "Economy"
}