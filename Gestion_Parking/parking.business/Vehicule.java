package parking.business;

/**
 * Vehicule est une classe abstraite, mère des classes "Voiture" et "Camion".
 * 
 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
 * @version 1.0
 */
public abstract class Vehicule {
	
	protected String proprietaire, num_immat, marque, modele;
	protected double hauteur, longueur, masse;
	
	/**
     * Constructeur de la classe "Vehicule".
     * 
     * @param proprietaire
     *            Le propriétaire du véhicule.
     *  
     * @param num_immat
     *            Le numéro d'immatriculation du véhicule.
     *            
     * @param marque
     *            La marque du véhicule.
     *            
     * @param modele
     *            Le modèle du véhicule.
     *
     * @param hauteur
     *            La hauteur du véhicule.
     *            
     * @param longueur
     *            La longueur du véhicule.
     *            
     * @param masse
     *            La masse du véhicule.
     */
	public Vehicule(String proprietaire, String num_immat, String marque, String modele, double hauteur, double longueur, double masse) {
		this.proprietaire = proprietaire;
		this.num_immat = num_immat;
		this.marque = marque;
		this.modele = modele;	
		this.hauteur = hauteur;
		this.longueur = longueur;
		this.masse = masse;
	}
	
	/**
     * Récupérer le numéro d'immatriculation de véhicule (camion ou voiture).
     * 
     * @return Le numéro d'immatriculation du véhicule.
     */
	public String getNumImmat() {
		return this.num_immat;
	}
	
	/**
     * Récupérer le propriétaire d'un véhicule (camion ou voiture).
     * 
     * @return Le propriétaire du véhicule.
     */
	public String getProprietaire() {
		return this.proprietaire;
	}
	
	/**
     * Récupérer le type de véhicule (méthode implémentée dans les classes filles).
     * 
     * @return Le type de véhicule.
     */
	abstract String getType();
}