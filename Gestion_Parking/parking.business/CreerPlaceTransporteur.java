package parking.business;

public class CreerPlaceTransporteur implements PlaceFactory{

	public Place creerPlace(int numeroPlace) {
		return new PlaceTransporteur(numeroPlace, null);
	}
}
