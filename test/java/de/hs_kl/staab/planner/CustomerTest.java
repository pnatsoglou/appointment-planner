package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.customer.Customer;
import de.hs_kl.staab.planner.vehicles.Vehicles;

public class CustomerTest {

	Vehicles vehicles1 = new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
			LocalDateTime.of(2004, 2, 16, 0, 0));

	Customer customer1 = new Customer("Panagiotis Natsoglou", "Schulstraße 14, 66849 Landstuhl",
			"pannat220299@gmail.com", vehicles1);

	@Test
	public void getAllCustomerTest() {
		assertEquals("Panagiotis Natsoglou", customer1.getCustomerName());
		assertEquals("Schulstraße 14, 66849 Landstuhl", customer1.getBillingDetails());
		assertEquals("pannat220299@gmail.com", customer1.getContactDetails());
	}

}
