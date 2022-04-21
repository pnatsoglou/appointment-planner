package de.hs_kl.staab.planner.vehicles;

import java.util.ArrayList;
import java.util.List;

public class VehiclesService implements VehiclesCRUD {

	private List<Vehicles> vehiclesList = new ArrayList<>();

	@Override
	public void createNewVehicles(Vehicles vehicles) {
		vehiclesList.add(vehicles); // fügt neue Auto-Instanzen in der Liste hinzu
	}

	@Override
	public void updateVehicles(int ID, String licensePlate) { // updated das KFZ-Kennzeichen einer Auto-Instanz über
																// Streams
		vehiclesList.stream().filter(vehicle -> vehicle.getNumberVehicles() == ID)
				.forEach(vehicle -> vehicle.setLicensePlate(licensePlate));
	}

	@Override
	public Vehicles getVehiclesByID(int vehiclesID) { // gibt eine Auto-Instanz nach der ID aus
		if (vehiclesID <= 0) {
			throw new IndexOutOfBoundsException(
					"Es gibt keine Fahrzeug-ID die mit 0 beginnt. Bitte geben Sie eine Zahl > 0 ein.");
		}
		if (vehiclesID > vehiclesList.size()) {
			throw new IndexOutOfBoundsException("Es gibt keine Fahrzeug-ID mit dieser Nummer. Es gibt insgesamt "
					+ vehiclesList.size() + " Fahrzeuge.");
		}
		return vehiclesList.get(vehiclesID - 1); // fängt nicht bei der 0ten, sondern bei der ersten ID an.
	}

	@Override
	public List<Vehicles> getVehicles() { // gibt die gesamte Liste der Auto-Instanzen aus
		if (vehiclesList.isEmpty()) {
			System.err.println("Die Liste für Fahrzeuge ist leer.");
		}
		return vehiclesList;
	}
}
