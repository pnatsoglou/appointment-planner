package de.hs_kl.staab.planner.vehicles;

import java.time.LocalDateTime; //Siehe Line 13

// MVP030 Improved Version 4.0
public class Vehicles {

	// Klassenvariable
	public static int ID_VEHICLES = 1;

	// Instanzvariablen
	private String licensePlate; // KFZ-Kennzeichen
	private final String brandName; // Automarke
	private final String modelName; // Automodell
	private final LocalDateTime constructionYear; // Baujahr
	private final LocalDateTime dateOfApproval; // Erstzulassung
	private final int numberVehicles; // ID der jeweiligen Auto-Instanz

	public Vehicles(String licensePlate, String brandName, String modelName, LocalDateTime constructionYear,
			LocalDateTime dateOfApproval) { // Konstruktor
		this.numberVehicles = ID_VEHICLES++;
		this.licensePlate = licensePlate;
		this.brandName = brandName;
		this.modelName = modelName;
		this.constructionYear = constructionYear;
		this.dateOfApproval = dateOfApproval;
	}

	@Override
	// Ausgabe
	public String toString() {
		return "(Fahrzeug) numberVehicles: " + this.numberVehicles + ", " + "licensePlate: " + this.licensePlate + ", "
				+ "brandName: " + this.brandName + ", " + "modelName: " + this.modelName + ", " + "constructionYear: "
				+ this.constructionYear + ", " + "dateOfApproval: " + this.dateOfApproval;
	}

	// Getters & Setters
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) { // Setter für KFZ-Kennzeichen, da sich das KFZ-Kennzeichen des
														// Autos verändern kann.
		this.licensePlate = licensePlate;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public LocalDateTime getConstructionYear() {
		return constructionYear;
	}

	public LocalDateTime getDateOfApproval() {
		return dateOfApproval;
	}

	public int getNumberVehicles() {
		return numberVehicles;
	}

}
