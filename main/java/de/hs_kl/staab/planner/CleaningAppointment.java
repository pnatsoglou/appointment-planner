package de.hs_kl.staab.planner;

import java.time.LocalDateTime;

import de.hs_kl.staab.planner.user.Dispatcher;
import de.hs_kl.staab.planner.workstation.WorkStation;

public class CleaningAppointment extends Appointment implements Collidable {

	private double cleaningTime;

	private final WorkStation workStation;
	private final Dispatcher dispatcher;

	public CleaningAppointment(LocalDateTime dateOfAppointment, String locationOfAppointment, WorkStation workStation,
			CleaningTypes cleaningTypes, Dispatcher dispatcher) {
		super(dateOfAppointment, locationOfAppointment);
		this.dispatcher = dispatcher;
		this.workStation = workStation;
		switch (cleaningTypes) {
		case FAST:
			this.cleaningTime = 30; // Angabe in Minuten
			break;
		case INTENSE:
			this.cleaningTime = 60; // Angabe in Minuten
			break;
		default:
			break;
		}
		// Hier speichern wir, bis wie lange der Termin gehen wird
		super.endOfAppointment = super.dateOfAppointment.plusMinutes((long) this.cleaningTime);
	}

	// Getter

	@Override
	public WorkStation getWorkStation() {
		return workStation;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setCleaningTime(double cleaningTime) {
		this.cleaningTime = cleaningTime;
	}

	public double getCleaningTime() {
		return cleaningTime;
	}

	@Override
	public String toString() {
		return "\n(Reinigungstermin) " + super.toString() + this.dispatcher + ", " + "cleaningTime: "
				+ this.cleaningTime + " Minuten" + ", " + this.workStation;
	}

}
