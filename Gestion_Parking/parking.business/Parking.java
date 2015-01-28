package parking.business;

import java.util.ArrayList;
import java.util.HashMap;

import parking.exception.NumeroPlaceException;
import parking.exception.PlaceDisponibleException;
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;
import parking.exception.VehiculePresentException;

	/**
	 * Parking est la classe principale du projet, elle gère presque toutes les données.
	 * 
	 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
	 * @version 1.0
	 */
public class Parking {
	
	//Liste des prix en fonction du véhicule (Collection d'objets "HashMap")
	private HashMap<String, Integer> constantePrixVehicule = new HashMap<String, Integer>();
	
	//Liste des places du parking (Collection d'objets "ArrayList")
	private ArrayList<Place> places = new ArrayList<Place>();
	
	//Liste des véhicules présents dans le parking (Collection d'objets "ArrayList")
	private ArrayList<Vehicule> vehiculesPresents = new ArrayList<Vehicule>();
	
	//Attributs généraux de la classe   
	private String nom;
	private int placesTransporteurs;
	
	
	/**
     * Constructeur de la classe "Parking".
     * 
     * @param nom
     *            Le nom du parking.
     *  
     * @param nbParticuliers
     *            Le nombre de places "particulier" contenues dans le parking.
     * 
     * @param nbTransporteur
     *            Le nombre de places "transporteur" contenues dans le parking.
     */
	public Parking(String nom, int nbParticuliers, int nbTransporteurs) {
		this.nom = nom;
		this.placesTransporteurs = nbTransporteurs;
		this.constantePrixVehicule.put("camion", new Integer(6));
		this.constantePrixVehicule.put("voiture", new Integer(3));
		  
		int totalPlaces = nbParticuliers + nbTransporteurs;
		
		//Création des places de parking "Particulier"
		for(int i = 0; i < nbTransporteurs; i++) 
				places.add(new PlaceTransporteur(i, null));
		
		//Création des places de parking "Transporteur"
		for(int i = nbTransporteurs; i < totalPlaces; i++)
				places.add(new PlaceParticulier(i, null));
	}
	
	/**
     * Vérifier si un véhicule est dans le parking ou pas.
     * 
     * @param vehicule
     *            Un véhicule à vérifier.
     *            
     * @return Une valeur de type "true" ou "false"
     */
	public boolean vehiculeExiste(Vehicule vehicule) {
		return vehiculesPresents.contains(vehicule);
	}
	
	/**
     * Garer un véhicule à une place donnée (si elle est libre).
     * 
     * @param vehicule
     *            Un véhicule à garer.
     *            
     * @param num_place 
     *            Un numéro de place pour garer un véhicule.
     *      
     * @throws PlaceOccupeeException(numeroPlace)
     * 			  Si la place est occupée ou si elle ne correspond pas au type de véhicule.
     * 
     * @throws NumeroPlaceException(numeroPlace)
     * 			  Si le numéro de place n'existe pas.
     * 
     * @throws VehiculePresentException
     * 			  Si le véhicule que l'on veut garer est déjà présent dans le parking.
     */
	public void park(Vehicule vehicule, int numeroPlace) throws PlaceOccupeeException, VehiculePresentException, NumeroPlaceException {
		if(!vehiculeExiste(vehicule)) 
		{
			if(numeroPlace >= 0 && numeroPlace < places.size())
			{
				if(places.get(numeroPlace).estLibre() && !places.get(numeroPlace).estReservee())
				{
					if(places.get(numeroPlace).getType() == "transporteur" && vehicule.getType() == "camion")
					{
						places.get(numeroPlace).attribuerVehicule(vehicule);
						vehiculesPresents.add(vehicule);
						System.out.println("Le véhicule (de type 'camion') a été garé à la place n." + numeroPlace + " "
								+ "(type: transporteur).");
		
					}
					else if(places.get(numeroPlace).getType() == "particulier" && vehicule.getType() == "voiture")
					{
						places.get(numeroPlace).attribuerVehicule(vehicule);
						vehiculesPresents.add(vehicule);
						System.out.println("Le véhicule (de type 'voiture') a été garé à la place n." + numeroPlace + " "
								+ "(type: particulier).");
					}
					else if(places.get(numeroPlace).getType() == "transporteur" && vehicule.getType() == "voiture")
					{
						places.get(numeroPlace).attribuerVehicule(vehicule);
						vehiculesPresents.add(vehicule);
						System.out.println("Le véhicule (de type 'voiture') a été garé à la place n." + numeroPlace + " "
								+ "(type    : transporteur).");
					}	
					else
						throw new PlaceOccupeeException(numeroPlace);
				}
				else
				{
					if(vehicule.getType() == "voiture")
					{
						//Variable en charge de la place
						Place place;
						for(int i = 0; i < places.size(); i++)
						{
							place = places.get(i);
							if(place.estLibre() && !place.estReservee())
							{
								place.attribuerVehicule(vehicule);
								vehiculesPresents.add(vehicule);
								System.out.println("La place n." + numeroPlace + " étant indisponible, "
										+ "le véhicule immatriculé '" + vehicule.getNumImmat() + "' (de type 'voiture') a été garé sur la place "
										+ "n." + place.getNum() + " (type: " + place.getType() + ").");
								return;
							}
						}
					}
					else
						throw new PlaceOccupeeException(numeroPlace);
				}
					
			}
			else
				throw new NumeroPlaceException(numeroPlace);
		}
		else
			throw new VehiculePresentException();
	}
	
	/**
     * Retirer un véhicule d'une place de parking.
     *
     * @param num_place
     *            Un numéro de place pour retirer un véhicule.
     *            
     * @return L'objet "Vehicule" récupéré.
     * 
     * @throws PlaceLibreException
     * 			  Si la place est vide.
     * 
     * @throws NumeroPlaceException(numeroPlace)
     * 			  Si le numéro de place n'existe pas.
     */
	public Vehicule unpark(int numeroPlace) throws PlaceLibreException, NumeroPlaceException {
		//Si le numéro de place est correct
		if(numeroPlace >= 0 && numeroPlace < places.size())
		{			
			//Si la place n'est pas libre
			if(!places.get(numeroPlace).estLibre())
			{
				//Variable qui prend pour valeur le véhicule qui va être retiré
				Vehicule vehiculeUnpark = places.get(numeroPlace).getVehicule();
				
				//Enlever le véhicule de la liste des véhicules présents
				vehiculesPresents.remove(vehiculeUnpark);
				
				//Rendre la place libre
				places.get(numeroPlace).libererPlace(constantePrixVehicule.get(places.get(numeroPlace).getType()));
				
				//Retourner l'objet "Vehicule" récupéré
				return vehiculeUnpark;	
			}
			else
				throw new PlaceLibreException(numeroPlace);
		}
		else
			throw new NumeroPlaceException(numeroPlace);
	}

	/**
     * Récupérer le numéro de place occupée.
     *
     * @param num_immat
     *            Un numéro d'immatriculation.
     *            
     * @return Le numéro de place où se trouve le véhicule ou -1 (sinon).
     */
	public int getLocation(String num_immat) {
		//Variable en charge de la place
		Place place;
		
		//Parcours du parking
		for(int i = 0; i < places.size(); i++)
		{
			place = places.get(i);
			if(!place.estLibre())
			{
				if(place.getVehicule().getNumImmat() == num_immat)
				{
					//Retourner le numéro de place
					return place.getNum();
				}
			}
		}
		return -1;
	}
	
	/**
     * Retirer un véhicule du parking (avec son numéro d'immatriculation).
     *
     * @param num_immat
     *            Un numéro d'immatriculation.
     *            
     * @return Le véhicule qui a été retiré ou "NULL" (sinon).
     */
	public Vehicule retirerVehicule(String num_immat) {
		//Variable de retour
		Vehicule vehiculeRetire;
		
		//Variable en charge de la place
		Place place;
		
		//Parcours du parking
		for(int i = 0; i < places.size(); i++)
		{
			place = places.get(i);
			if(!place.estLibre())
			{
				if(place.getVehicule().getNumImmat() == num_immat)
				{				
					//Retirer le véhicule des véhicules présents
					vehiculesPresents.remove(place.getVehicule());
				
					//Affectation à la variable de retour
					vehiculeRetire =  place.getVehicule();
					
					//Assigner la valeur "NULL" à la place
					place.libererPlace(constantePrixVehicule.get(place.getType()));
					
					//Retourner la variable contenant le véhicule
					return vehiculeRetire;
				}
			}
		}
		return null;
	}
	
	/**
     * Affiche l'état du parking
     */
	public void etatParking() {
		//Parcours du parking
		for(int i = 0; i < places.size(); i++)
		{
			if(places.get(i).estLibre() && !places.get(i).estReservee())
				System.out.println("La place n." + i + " (" + places.get(i).getType() + ") est libre.");
			
			else if(places.get(i).estReservee())
				System.out.println("La place n." + i + " (" + places.get(i).getType() + ") est réservée.");
				
			else
				System.out.println("La place n." + i + " (" + places.get(i).getType() + ") "
						+ "est occupée par le véhicule suivant : " + places.get(i).getVehicule().toString());
			System.out.println("");
		}
		System.out.println("NOMBRE TOTAL DE VEHICULES : " + vehiculesPresents.size());
	}
	
	/**
     * Réserver une place de parking.
     *
     * @param vehicule
     *            Le véhicule qui réserve la place.
     *            
     * @return La place qui a été réservée.
     * 
     * @throws PlusAucunePlaceException(vehicule)
     * 			  Si aucune place n'est dispoinible pour le véhicule.
     */
	public Place bookPlace(Vehicule vehicule) throws PlusAucunePlaceException {
		String typeVehicule = vehicule.getType();
		for(int i = 0; i < places.size(); i++)
		{
			if(typeVehicule == "voiture")
			{
				if(!places.get(i).estReservee() && places.get(i).estLibre())
				{ 
					places.get(i).setReservation(true);
					return places.get(i);
				}
			}
			else
			{
				if(places.get(i).getType()=="transporteur")
				{
					places.get(i).setReservation(true);
					return places.get(i);
				}
			}
		}
		
		throw new PlusAucunePlaceException(vehicule);
	}
	
	/**
     * Rendre libre une place de parking réservée.
     *
     * @param placeALiberer
     *            La place à libérer.
     *            
     * @throws PlaceDisponibleException(placeALiberer.getNum())
     * 			  Si la place à libérer est déjà libre.
     */
	public void freePlace(Place placeALiberer) throws PlaceDisponibleException {
		if(placeALiberer.estReservee())
			placeALiberer.setReservation(false);
		
		else
			throw new PlaceDisponibleException(placeALiberer.getNum());
	}
	
	/**
     * Réorganiser les places de parking.
     */
	/*public void reorganiserPlace() {
		for(int i = 0; i < plac; i++)
		{
			
		}
	}*/
}

