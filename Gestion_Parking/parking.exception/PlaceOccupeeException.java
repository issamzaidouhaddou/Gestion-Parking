package parking.exception;

public class PlaceOccupeeException extends Exception{

	public PlaceOccupeeException(int numeroPlace) {
		System.out.println("====> ERREUR : La place " + numeroPlace + " est déjà occupée ou elle ne correspond pas au type de vehicule.");
	}
}
