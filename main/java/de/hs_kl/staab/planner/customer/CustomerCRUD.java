package de.hs_kl.staab.planner.customer;

import java.util.List;

public interface CustomerCRUD {

	void createNewCustomer(Customer customer);

	void updateCustomer(int ID, String customerName, String billingDetails, String contactDetails);

	Customer getCustomerByID(int customerID);

	List<Customer> getCustomers();
}
