package de.hs_kl.staab.planner;

import java.time.LocalDateTime;

import de.hs_kl.staab.planner.customer.Customer;
import de.hs_kl.staab.planner.user.Consultant;

public class ConsultingAppointment extends Appointment {

	private final Customer customer;
	private final Consultant consultant;

	public ConsultingAppointment(LocalDateTime dateOfAppointment, String locationOfAppointment, Customer customer,
			Consultant consultant) {
		super(dateOfAppointment, locationOfAppointment);
		this.customer = customer;
		this.consultant = consultant;
		super.endOfAppointment = super.dateOfAppointment.plusMinutes(60); // Terminende auf 60 Minuten gestellt (Mit
																			// Puffer)
	}

	public Customer getCustomer() {
		return customer;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	@Override
	public String toString() {
		return "\n(Beratungstermin): " + super.toString() + this.consultant + this.customer;
	}

}
