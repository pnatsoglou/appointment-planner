package de.hs_kl.staab.planner.vehicles;

import java.util.List;

public interface VehiclesCRUD {

	void createNewVehicles(Vehicles vehicles);

	void updateVehicles(int ID, String licensePlate); // nur KFZ-Kennzeichen kann man updaten, der Rest ist auf final
														// gesetzt.

	Vehicles getVehiclesByID(int vehiclesID);

	List<Vehicles> getVehicles();

}
