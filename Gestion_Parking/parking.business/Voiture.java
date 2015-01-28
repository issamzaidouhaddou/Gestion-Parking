package parking.business;

/**
 * "Voiture" est une classe fille de "Vehicule".
 * 
 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
 * @version 1.0
 */
public class Voiture extends Vehicule{
	
	private String type;
	
	/**
     * Constructeur de la classe "Voiture" (classe fille de "Vehicule").
     * 
     * @param proprietaire
     *            Le propriétaire de la voiture.
     *  
     * @param num_immat
     *            Le numéro d'immatriculation de la voiture.
     *            
     * @param marque
     *            La marque de la voiture.
     *            
     * @param modele
     *            Le modèle de la voiture.
     *            
     * @param hauteur
     *            La hauteur de la voiture.
     *            
     * @param longueur
     *            La longueur de la voiture.
     *            
     * @param masse
     *            La masse de la voiture.
     */
	public Voiture(String proprietaire, String num_immat, String marque, String modele, double hauteur, double longueur, double masse) {
		super(proprietaire, num_immat, marque, modele, hauteur, longueur, masse);
		type = "voiture";
	}

	/**
     * Récupérer le type d'une voiture.
     * 
     * @return Le type de véhicule (ici "voiture").
     */
	public String getType() {
		return type;
	}
	
	/**
     * Récupérer les informations d'une voiture.
     * 
     * @return Une chaîne de caractères contenant les informations d'une voiture.
     */
	public String toString() {
		return "\n Type de vehicule : " + type + "\n Proprietaire : " + proprietaire + "\n Immatriculation : " + num_immat + "\n "
				+ "Marque : " + marque + "\n Modele : " + modele + "\n Hauteur : " + hauteur + " m \n"
				+ "Longueur : " + longueur + " m \n Masse : " + masse + " kg \n";
	}
}
