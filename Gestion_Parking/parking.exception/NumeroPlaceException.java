package parking.exception;

public class NumeroPlaceException extends Exception{

	public NumeroPlaceException(int numeroPlace) {
		System.out.println("====> ERREUR : Le numéro de place " + numeroPlace + " est incorrect.");
	}
	
}
