package de.hs_kl.staab.planner;

import java.time.LocalDateTime;

import de.hs_kl.staab.planner.user.CarMechanic;
import de.hs_kl.staab.planner.vehicles.Vehicles;
import de.hs_kl.staab.planner.work.Work;
import de.hs_kl.staab.planner.workstation.WorkStation;

public class WorkAppointment extends Appointment implements Collidable {

	private final Work work;
	private final WorkStation workStation;
	private final Vehicles vehicles;
	private final CarMechanic carMechanic;
	private boolean markAsCompleted;

	public WorkAppointment(LocalDateTime dateOfAppointment, String locationOfAppointment, Work work,
			WorkStation workStation, Vehicles vehicles, CarMechanic carMechanic) {
		super(dateOfAppointment, locationOfAppointment);
		this.work = work;
		this.workStation = workStation;
		this.vehicles = vehicles;
		this.carMechanic = carMechanic;
		this.markAsCompleted = false;
		super.endOfAppointment = super.dateOfAppointment.plusMinutes(work.getDuration());
	}

	// Getters & Setters

	public Work getWork() {
		return work;
	}

	@Override
	public WorkStation getWorkStation() {
		return workStation;
	}

	public Vehicles getVehicles() {
		return vehicles;
	}

	public CarMechanic getCarMechanic() {
		return carMechanic;
	}

	public boolean isMarkAsCompleted() {
		return markAsCompleted;
	}

	public void setMarkAsCompleted(boolean markAsCompleted) {
		this.markAsCompleted = markAsCompleted;
	}

	// MMP030
	public void checkIfWorkAppointmentIsMarkedAsCompleted() {
		if (this.markAsCompleted) {
			System.out.println(
					"Der Arbeitstermin mit der Termin-ID: " + super.numberAppointment + " wurde als erledigt markiert");
		} else {
			System.err.println("Der Arbeitstermin mit der Termin-ID: " + super.numberAppointment
					+ " wurde als abgebrochen markiert.");
		}
	}

	// Ausgabe

	@Override
	public String toString() {
		return "\n(Arbeitstermin): " + super.toString() + this.carMechanic + this.work + ", " + this.workStation + ", "
				+ ", " + this.vehicles;
	}

}
