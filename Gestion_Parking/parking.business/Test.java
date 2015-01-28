package parking.business;

import parking.exception.NumeroPlaceException;
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.VehiculePresentException;

public class Test {

	private static Parking p = new Parking("Rom", 15, 5);
	private static Vehicule v = new Voiture("Romain.S", "123-AZE-456", "Audi", "A6", 1.50, 4.96, 1760);
	private static Vehicule v1 = new Voiture("Michael Jackson", "NUMBER#1", "Carosse", "Royal", 1.90, 10.0, 1000);
	private static Vehicule v2 = new Voiture("Daft Punk", "HARDER", "Robot", "Rock", 1.80, 4.96, 80);
	private static Vehicule v3 = new Camion("Homer Simpson", "DOH!", "Mercedes", "Truck", 4, 7, 5000);
	private static Vehicule v4 = new Camion("Un Gros Lard", "FAT-13", "Mercedes", "Truck", 4, 7, 7000);
	
	public static void afficherEtat() {
		p.etatParking();
	}
	
	public static void testParkVehicules() {
		try {
			p.park(v, 6);
			p.park(v1, 7);
			p.park(v2, 10);
			p.park(v3, 1);
			p.park(v4, 0);
		} catch (PlaceOccupeeException | VehiculePresentException | NumeroPlaceException e) {
			
		}
	}
	
	public static void testUnparkVehicules() {
		Vehicule vUnpark = null;
		testParkVehicules();
		System.out.println("=== AVANT UNPARK ===");
		afficherEtat();
		System.out.println("");
		try {
			vUnpark = p.unpark(1);
		} catch (PlaceLibreException | NumeroPlaceException e) {
			
		}
		
		System.out.println("===> Le véhicule suivant vient de quitter le parking : " + vUnpark.toString());
		System.out.println("");
		System.out.println("=== APRES UNPARK ===");
		afficherEtat();
	}
	
	public static void testSearchCar(String immat) {
		int num;
		testParkVehicules();
		num = p.getLocation(immat);
		System.out.println("Le véhicule ayant l'immatriculation '" + immat + "' se trouve é la place n." + num + ".");
	}
	
	public static void testRetirerVehicule(String immat) {
		Vehicule vRetirer = null;
		testParkVehicules();
		System.out.println("=== AVANT RETRAIT ===");
		afficherEtat();
		System.out.println("");
		vRetirer = p.retirerVehicule(immat);
		System.out.println("===> Le véhicule ayant l'immatriculation '" + immat + "' a été retiré du parking. "
						 + "Voici ses informations : " + vRetirer.toString());
		System.out.println("=== APRES RETRAIT ===");
		afficherEtat();
	}
	
	public static void main(String[] args) {
		//tester la fonction park()
		/*testParkVehicules();*/
		
		//tester la fonction unpark()
		/*testUnparkVehicules();*/
		
		//tester la fonction getLocation()
		/*testSearchCar("FAT-13");*/
		
		//tester la fonction retirerVehicule()
		/*testRetirerVehicule("FAT-13");*/
	}

}
