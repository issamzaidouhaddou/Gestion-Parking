package parking.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;

import parking.exception.NumeroPlaceException;

/**
 * Facture est la classe qui gère la création et la sauvegarde de factures.
 * 
 * @author Romain Semler, Simon Regnier, Cédric Vigne, Manon Saillard
 * @version 1.0
 */
public class Facture{
	
	private int numeroPlaceRelative, tempsEcoule, numeroFacture;
	private double valeurFacture;
	private String typeVehicule, proprietaire, debutStationnement, finStationnement;
	
	/**
     * Constructeur de la classe "Facture".
     * 
     * @param numeroPlaceRelative
     *            Le numéro de la place que le véhicule occupait ("numeroPlace" de la classe Place).
     *  
     * @param coefVehicule
     *            Le prix à la minute.
     *            
     * @param debutStationnement
     *            Le début du stationnement.
     *            
     * @param typeVehicule
     *            Le type du véhicule.
     *            
     * @param proprietaire
     *            Le propriétaire du véhicule.
     */
	public Facture(int numeroPlaceRelative, int coefVehicule , long debutStationnement, String typeVehicule, String proprietaire){
		try {
			int finished = 0;
			String StringValue ="";
			byte[] buf = new byte[8];
			if(new File("Configuration").exists() == false){
				File repertoire = new File("Configuration");
				repertoire.mkdirs();
			}
			
			if(new File("./Configuration/Conf.txt").exists() == false)
			{
				File newFile = new File("./Configuration/Conf.txt");
				StringValue="0";
				newFile.createNewFile();
				FileOutputStream outputFile = new FileOutputStream(newFile);
				outputFile.write(StringValue.getBytes());
				outputFile.flush();
				outputFile.close();
			}
			
			FileInputStream inputFile = new FileInputStream("./Configuration/Conf.txt");
			while ((finished = inputFile.read(buf))>=0) {
				for (byte bit : buf) {
					   StringValue += Character.toString((char)bit);
	            }
			}
			StringValue=StringValue.trim();
			this.numeroFacture = Integer.parseInt(StringValue);
			this.numeroFacture++;
			StringValue = String.valueOf(numeroFacture);
			FileOutputStream outputFile = new FileOutputStream("./Configuration/Conf.txt");
			outputFile.write(StringValue.getBytes());
			outputFile.flush();
			outputFile.close();
			inputFile.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.numeroPlaceRelative = numeroPlaceRelative;
		this.typeVehicule = typeVehicule;
		this.proprietaire = proprietaire;
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yy à H:mm:ss");
		this.debutStationnement = formater.format( new Time(debutStationnement));
		this.finStationnement = formater.format(new Time(System.currentTimeMillis()).getTime());
		if(new Time(System.currentTimeMillis()).getMinutes()-new Time(debutStationnement).getMinutes() == 0)
		{
			this.valeurFacture = 0;
		}
		else
		{
			this.valeurFacture = Math.rint((coefVehicule * (new Time(System.currentTimeMillis()).getMinutes()-new Time(debutStationnement).getMinutes())+ (coefVehicule * (new Time(System.currentTimeMillis()).getMinutes()-new Time(debutStationnement).getMinutes()) * 19.6/100)));
		}
		this.tempsEcoule = new Time(System.currentTimeMillis()).getMinutes()-new Time(debutStationnement).getMinutes();
	}

	/**
     * Permet d'enregistrer dans un fichier externe les données de la facture.
     */
	public void sauvegarderFacture(){
		String StringValue = this.toString();
		try {
			if(new File("Factures").exists() == false){
				File repertoire = new File("Factures");
				repertoire.mkdirs();
			}
			File newFile = new File("./Factures/facture_"+numeroFacture+".txt");
			newFile.createNewFile();
			FileOutputStream outputFile = new FileOutputStream(newFile);
			outputFile.write(StringValue.getBytes());
			outputFile.flush();
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * Récupérer les informations de la facture.
     * 
     * @return Une chaîne de caractères contenant les informations de la facture.
     */
	public String toString() {
		return "\nFacture de Madame/Monsieur " + proprietaire + "\nNuméro de facture : " + numeroFacture
				+ "\nNuméro de la place occupée : " + numeroPlaceRelative
				+"\nDébut du stationnement : " + debutStationnement + "\nFin du stationnement : " + finStationnement + "\nMontant à payer : " + valeurFacture + " euros \nCatégorie du véhicule : "
				+ typeVehicule + "\nDurée du stationnement : " + tempsEcoule + " minute(s)\n\n";
	}
	
}
