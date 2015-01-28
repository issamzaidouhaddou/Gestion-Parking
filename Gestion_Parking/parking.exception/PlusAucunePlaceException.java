package parking.exception;

import parking.business.Vehicule;

public class PlusAucunePlaceException extends Exception{

	public PlusAucunePlaceException(Vehicule vehicule) {
		System.out.println("====> ERREUR : Aucune place n'est disponible pour "
				+ "le véhicule suivant : " + vehicule.toString());
	}
}
