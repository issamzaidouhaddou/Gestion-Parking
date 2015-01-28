package parking.business;

/**
 * Camion est une classe fille de "Vehicule".
 * 
 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
 * @version 1.0
 */
public class Camion extends Vehicule{
	
	private String type;

	/**
     * Constructeur de la classe "Camion".
     * 
     * @param proprietaire
     *            Le propriétaire du camion.
     *  
     * @param num_immat
     *            Le numéro d'immatriculation du camion.
     *            
     * @param marque
     *            La marque du camion.
     *            
     * @param modele
     *            Le modèle du camion.
     *            
     * @param hauteur
     *            La hauteur du camion.
     *            
     * @param longueur
     *            La longueur du camion.
     *            
     * @param masse
     *            La masse du camion.
     */
	public Camion(String proprietaire, String num_immat, String marque, String modele, double hauteur, double longueur, double masse) {
		super(proprietaire, num_immat, marque, modele, hauteur, longueur, masse);
		type = "camion";
	}
	
	/**
     * Récupérer le type d'un camion.
     * 
     * @return Le type de véhicule (ici "camion").
     */
	public String getType() {
		return type;
	}
	
	/**
     * Récupérer les informations d'un camion.
     * 
     * @return Une chaîne de caractères contenant les infos d'un camion.
     */
	public String toString() {
		return "\n Type de vehicule : " + type + "\n Proprietaire : " + proprietaire + "\n Immatriculation : " + num_immat + "\n "
				+ "Marque : " + marque + "\n Modele : " + modele + "\n Hauteur : " + hauteur + " m \n"
				+ "Longueur : " + longueur + " m \n Masse : " + masse + " kg \n";
	}	
}
