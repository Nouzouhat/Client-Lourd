package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	public static User selectWhereUSer (String email, String mdp) {
		//tester email et la complexité mdp.
		return Modele.selectWhereUser(email, mdp);
	}
	
	/*********************gestion des clients ***************/
	public static void insertClient (Client unClient) {
		Modele.insertClient(unClient);
	}
	
	public static void deleteClient (int idClient) {
		Modele.deleteClient(idClient);
	}
	
	public static void updateClient (Client unClient) {
		Modele.updateClient(unClient);
	}
	
	public static ArrayList<Client> selectAllClients (String filtre) {
		return Modele.selectAllClients(filtre);
	}
	
	public static Client selectWhereClient (int idClient) {
		return Modele.selectWhereClient(idClient);
	}

	public static void updateuUser(User unUser) {
		Modele.updateuUser(unUser);
		
	}
	
	public static Client selectWhereClient (String nom, String email) {
		return Modele.selectWhereClient(nom, email);
	} 
	
	/**************************gestion des entreprises ***********/
	public static void insertEntreprise( Entreprise uneEntreprise) {
		Modele.insertEntreprise(uneEntreprise);
	}
	public static void deleteEntreprise(int idEntreprise) {
		Modele.deleteEntreprise(idEntreprise);
	}
	public static void updateEntreprise(Entreprise uneEntreprise) {
		Modele.updateEntreprise(uneEntreprise);
	}

	public static ArrayList<Entreprise> selectAllEntreprises( String filtre) {
		return Modele.selectAllEntreprises(filtre);
	}
	public static Entreprise selectWhereEntreprise(int idEntreprise) {
		return Modele.selectWhereEntreprise(idEntreprise);
	}


	public static Entreprise selectWhereEntreprise (String nom, String email, String adresse) {
		return Modele.selectWhereEntreprise(nom, email, adresse);
	 }
	
	
	/***************GESTION DES CONTRATS ************************/
	public static void insertContrat(Contrat unContrat) {
		Modele.insertContrat(unContrat);
	}
	public static void deleteContrat(int idContrat) {
		Modele.deleteContrat(idContrat);
	}
	public static void updateContrat(Contrat unContrat) {
		Modele.updateContrat(unContrat);
	}

	public static ArrayList<Contrat> selectAllContrats( String filtre) {
		return Modele.selectAllContrats(filtre);
	}
	public static Contrat selectWhereContrat(int idContrat) {
		return Modele.selectWhereContrat(idContrat);
	}


	public static Contrat selectWhereContrat (String debutcontrat, String fincontrat, String montant) {
		return Modele.selectWhereContrat(debutcontrat, fincontrat, montant);
	 }

	
	
	/***************GESTION DES TECHNICIENS ************************/
	public static void insertTechnicien (Technicien unTechnicien) {
		Modele.insertTechnicien(unTechnicien);
	}
	
	public static void deleteTechnicien (int idTechnicien) {
		Modele.deleteTechnicien(idTechnicien);
	}
	
	public static void updateTechnicien (Technicien unTechnicien) {
		Modele.updateTechnicien(unTechnicien);
	}
	
	public static ArrayList<Technicien> selectAllTechniciens (String filtre) {
		return Modele.selectAllTechniciens(filtre);
	}
	
	public static Technicien selectWhereTechnicien (int idTechnicien) {
		return Modele.selectWhereTechnicien(idTechnicien);
	}

	
	public static Technicien selectWhereTechnicien (String nom, String email) {
		return Modele.selectWhereTechnicien(nom, email);
	}
	
	
	
	/************gestion des materiels***********/

	public static void insertMateriel(Materiel unMateriel) {
		Modele.insertMateriel(unMateriel);
	}
	public static void deleteMateriel(int idMateriel) {
		Modele.deleteMateriel(idMateriel);
	}
	public static void updateMateriel(Materiel unMateriel) {
		Modele.updateMateriel(unMateriel);
	}

	public static ArrayList<Materiel> selectAllMateriels( String filtre) {
		return Modele.selectAllMateriels(filtre);
	}
	public static Materiel selectWhereMateriel(int idMateriel) {
		return Modele.selectWhereMateriel(idMateriel);
	}

	public static Materiel selectWhereMateriel (String type, String nbrejourloues, String montant) {
		return Modele.selectWhereMateriel(type, nbrejourloues, montant);
	 }
	
	
	/************gestion des interventions ***********/
	 
	public static void insertIntervention( Intervention uneIntervention) {
		Modele.insertIntervention(uneIntervention);
	}
	public static void deleteIntervention(int idIntervention) {
		Modele.deleteIntervention(idIntervention);
	}
	public static void updateIntervention(Intervention uneIntervention) {
		Modele.updateIntervention(uneIntervention);
	}
	 
	public static ArrayList<Intervention> selectAllInterventions( String filtre) {
		return Modele.selectAllInterventions(filtre);
	}
	public static Intervention selectWhereIntervention(int idIntervention) {
		return Modele.selectWhereIntervention(idIntervention);
	}
	 
	 
	public static Intervention selectWhereIntervention (String dateinter,String tempstotal, String motif) {
		return Modele.selectWhereIntervention (dateinter, tempstotal ,motif);
	}
	

	}


