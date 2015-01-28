package parking.exception;

public class PlaceDisponibleException extends Exception{

	public PlaceDisponibleException(int numeroPlace) {
		System.out.println("====> ERREUR : La place " + numeroPlace + " n'est pas réservé.");
	}
}
