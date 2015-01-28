package parking.exception;

public class VehiculePresentException extends Exception{
	public VehiculePresentException() {
		System.out.println("====> ERREUR : Le véhicule est déjà présent.");
	}
}
