package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.vehicles.Vehicles;

public class VehiclesTest {

	Vehicles vehicles1 = new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
			LocalDateTime.of(2004, 2, 16, 0, 0));

	@Test
	public void getAllVehiclesTest() {
		assertEquals("KL-PN22", vehicles1.getLicensePlate());
		assertEquals("BMW", vehicles1.getBrandName());
		assertEquals("318i", vehicles1.getModelName());
		assertEquals(LocalDateTime.of(2002, 3, 14, 0, 0), vehicles1.getConstructionYear());
		assertEquals(LocalDateTime.of(2004, 2, 16, 0, 0), vehicles1.getDateOfApproval());

	}

}
