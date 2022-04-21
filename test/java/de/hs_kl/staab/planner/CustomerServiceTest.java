package de.hs_kl.staab.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.hs_kl.staab.planner.customer.Customer;
import de.hs_kl.staab.planner.customer.CustomerCRUD;
import de.hs_kl.staab.planner.customer.CustomerService;
import de.hs_kl.staab.planner.vehicles.Vehicles;

public class CustomerServiceTest {

	CustomerCRUD customerService = new CustomerService();

	Vehicles vehicles1 = new Vehicles("KL-PN22", "BMW", "318i", LocalDateTime.of(2002, 3, 14, 0, 0),
			LocalDateTime.of(2004, 2, 16, 0, 0));

	Customer customer1 = new Customer("Panagiotis Natsoglou", "Schulstraße 14, 66849 Landstuhl",
			"pannat220299@gmail.com", vehicles1);

	@Test
	public void getTestOfUpdateMethodByCustomerService() {
		// Überprüfen, ob die Update-Methode funktioniert
		customerService.createNewCustomer(customer1);
		assertEquals("Panagiotis Natsoglou", customerService.getCustomerByID(1).getCustomerName());
		assertEquals("Schulstraße 14, 66849 Landstuhl", customerService.getCustomerByID(1).getBillingDetails());
		assertEquals("pannat220299@gmail.com", customerService.getCustomerByID(1).getContactDetails());
		assertEquals(1, customerService.getCustomerByID(1).getNumberCustomerID());
		customerService.updateCustomer(1, "Ömer Ay", "Wischstraße 9, 67655 Kaiserslautern", "ömeray@gmail.com");
		assertEquals("Ömer Ay", customerService.getCustomerByID(1).getCustomerName());
		assertEquals("Wischstraße 9, 67655 Kaiserslautern", customerService.getCustomerByID(1).getBillingDetails());
		assertEquals("ömeray@gmail.com", customerService.getCustomerByID(1).getContactDetails());
		assertEquals(1, customerService.getCustomerByID(1).getNumberCustomerID());

	}

}
