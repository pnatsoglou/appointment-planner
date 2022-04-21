package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.vehicles.Vehicles;
import de.hs_kl.staab.planner.vehicles.VehiclesCRUD;
import de.hs_kl.staab.planner.vehicles.VehiclesService;

public class VehiclesServiceTest {

	VehiclesCRUD vehicles1 = new VehiclesService();

	@Test
	public void getTestByVehiclesService() {
		vehicles1.createNewVehicles(new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
				LocalDateTime.of(2004, 2, 16, 0, 0)));
		assertEquals("KL-PN22", vehicles1.getVehiclesByID(1).getLicensePlate());
	}

}
