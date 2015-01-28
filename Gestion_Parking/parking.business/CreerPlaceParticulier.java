package parking.business;

public class CreerPlaceParticulier implements PlaceFactory{

	public Place creerPlace(int numeroPlace) {
		return new PlaceTransporteur(numeroPlace, null);
	}
}