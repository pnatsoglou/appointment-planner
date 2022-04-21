package de.hs_kl.staab.planner.customer;

import de.hs_kl.staab.planner.vehicles.Vehicles;

// MVP040
public class Customer {

	private static int ID_CUSTOMER = 1; // Kundennummer des Kunden
	private String customerName; // Name des Kunden
	private String billingDetails; // Rechnungsdaten des Kunden
	private String contactDetails; // Kontaktdaten des Kunden
	private final int numberCustomerID;
	private Vehicles vehicles;

	public Customer(String customerName, String billingDetails, String contactDetails, Vehicles vehicles) {
		this.numberCustomerID = ID_CUSTOMER++;
		this.customerName = customerName;
		this.billingDetails = billingDetails;
		this.contactDetails = contactDetails;
		this.vehicles = vehicles;
	}

	// Getters & Setters

	public String getCustomerName() {
		return this.customerName;
	}

	public String getBillingDetails() {
		return this.billingDetails;
	}

	public String getContactDetails() {
		return this.contactDetails;
	}

	public int getNumberCustomerID() {
		return this.numberCustomerID;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setBillingDetails(String billingDetails) {
		this.billingDetails = billingDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	@Override
	// Ausgeben
	public String toString() {
		return "(Kunde) numberCustomerID: " + this.numberCustomerID + ", " + "customerName: " + this.customerName + ", "
				+ "billingDetails " + this.billingDetails + ", " + "contactDetails: " + this.contactDetails + ", "
				+ this.vehicles;
	}
}
