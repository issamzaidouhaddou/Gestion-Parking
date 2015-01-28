package parking.business;

import java.sql.Time;

/**
 * "PlaceParticulier" est une classe fille de "Place".
 * 
 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
 * @version 1.0
 */
public class PlaceParticulier extends Place{
	
	//Attributs généraux de la classe
	private Vehicule vehicule;
	private String type;
	
	/**
     * Constructeur de la classe "PlaceParticulier".
     * 
     * @param numeroPlace
     *            Le numéro de place du parking. 
     *            
     * @param vehicule
     *            Le véhicule associé à la place. 
     */
	public PlaceParticulier(int numeroPlace, Vehicule vehicule) {
		super(numeroPlace, vehicule);
		this.vehicule = vehicule;
		type = "particulier";
	}
	
	/**
     * Demander le type de la place de parking.
     *
     * @return Le type de la place de parking.            
     */
	public String getType() {
		return type;
	}
	
	/**
     * Demander le véhicule associé à la place.
     *
     * @return Le véhicule associé à la place.            
     */
	public Vehicule getVehicule() {
		return vehicule;
	}
	
	/**
     * Vérifier si la place est libre.
     *
     * @return "true" si aucun véhicule ne se trouve sur la place.           
     */
	public boolean estLibre() {
		return vehicule == null;
	}
	
	/**
     * Vérifier si la place est réservée.
     *
     * @return la valeur du booléen "reserve" (true ou fasle).           
     */
	public boolean estReservee() {
		return reservation == true;
	}
	
	/**
     * Modifier l'état de réservation de la place.
     *
     * @param reservation
     *            L'état de réservation (true ou false).         
     */
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}
	
	/**
     * Attribue un véhicule à une place et démarre le timer.
     * 
     * @param vehicule
     *            Le véhicule a attribuer. 
     */
	 public void attribuerVehicule(Vehicule vehicule) {
		 this.vehicule = vehicule;
		 this.debutStationnement = new Time(System.currentTimeMillis());
	 }
	
	/**
     * Libérer une place de parking.
     * 
     * @param cout
     *            Le coût à la minute du véhicule.
     */
	public void libererPlace(int cout) {
		try {
			   Facture newFacture = new Facture(numeroPlace, cout, this.getDebutStationnement().getTime() ,this.getType(), this.getVehicule().getProprietaire());
			   System.out.print(newFacture.toString());
		} catch (InterruptedException e) {
			   e.printStackTrace();
		}
		this.vehicule = null;		 
	}
}


