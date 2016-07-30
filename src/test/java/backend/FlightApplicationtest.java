package backend;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ixigo.flights.FlightApplicationStarter;
import com.ixigo.flights.models.AirLine;
import com.ixigo.flights.models.Airport;
import com.ixigo.flights.services.AirPortService;
import com.ixigo.flights.services.AirlineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = FlightApplicationStarter.class,
	loader = SpringApplicationContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightApplicationtest {

	@Autowired
	private AirPortService airPortService;
	
	@Autowired
	private AirlineService airlineService;
	
	@Test
	public void testAirPort(){
		List<Airport> airPortList = airPortService.allAirports();
		assertNotNull(airPortList);
	}
	
	@Test
	public void testAirLine(){
		List<AirLine> airLineList = airlineService.fetchAirLine();
		assertNotNull(airLineList);
	}
}
