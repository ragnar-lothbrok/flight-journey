package backend;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ixigo.flights.FlightApplicationStarter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = FlightApplicationStarter.class,
	loader = SpringApplicationContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightApplicationtest {

	
}
