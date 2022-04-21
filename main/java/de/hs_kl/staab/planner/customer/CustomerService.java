package de.hs_kl.staab.planner.customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet die Kunden mit ihren Fahrzeugen
 */
public class CustomerService implements CustomerCRUD {

	private List<Customer> customerList = new ArrayList<>();

	@Override
	public void createNewCustomer(Customer customer) {
		customerList.add(customer);
	}

	@Override
	public void updateCustomer(int ID, String customerName, String billingDetails, String contactDetails) {

		// Updated den Kunden über Streams
		customerList.stream().filter(customer -> customer.getNumberCustomerID() == ID).forEach(customer -> {
			customer.setCustomerName(customerName);
			customer.setBillingDetails(billingDetails);
			customer.setContactDetails(contactDetails);
		});

	}

	@Override
	public Customer getCustomerByID(int customerID) {
		if (customerID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Kunden-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (customerID > customerList.size()) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Kunden-ID mit dieser Nummer. Es gibt insgesamt " + customerList.size() + " Kunden.");
		}
		return customerList.get(customerID - 1);
	}

	@Override
	public List<Customer> getCustomers() {
		if (customerList.isEmpty()) {
			System.err.println("Die Liste für Customer ist leer.");
		}
		return customerList;
	}

}
