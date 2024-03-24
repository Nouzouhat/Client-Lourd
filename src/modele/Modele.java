package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import controleur.Client;
import controleur.Contrat;
import controleur.Entreprise;
import controleur.Intervention;
import controleur.Materiel;
import controleur.Technicien;
import controleur.User;

public class Modele {
	private static Bdd uneBdd = new Bdd("localhost:3307", "bts_ppe", "root", "");
	public static User selectWhereUser (String email, String mdp) {
		String requete = "select * from user where email ='"+email+"' and "
				+ "mdp = '"+mdp+"'; ";
		User unUser = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unUser = new User (
						unRes.getInt("iduser"), unRes.getString("nom"), 
						unRes.getString("prenom"), unRes.getString("email"),
						unRes.getString("mdp"), unRes.getString("role")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : "+ requete);
			
		}
		return unUser;
	}
	
/***********************GESTION DES Clients *******************/
	public static void insertClient(Client unClient) {
	    String requete = "INSERT INTO client (nom, email, telephone, acteur, adresse, cp, ville) VALUES ('"
	                    + unClient.getNom() + "', '"
	                    + unClient.getEmail() + "', '"
	                    + unClient.getTelephone() + "', '"
	                    + unClient.getActeur() + "', '"
	                    + unClient.getAdresse() + "', '"
	                    + unClient.getCp() + "', '"
	                    + unClient.getVille() + "');";
	    System.out.println(requete);
	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        unStat.execute(requete);
	        unStat.close();
	        uneBdd.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	    }
	}

public static void deleteClient (int idClient) {
	String requete ="delete from client where idclient ="+idClient+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch(SQLException exp) {
	System.out.println("Erreur de requete : "+ requete);
	}
}

public static void updateClient(Client unClient) {
    String requete = "UPDATE client SET nom = '"
                    +unClient.getNom() + "', email='"
                    +unClient.getEmail() + "', telephone='"
                    +unClient.getTelephone() + "', acteur='"
                    +unClient.getActeur() + "', adresse='"
                    +unClient.getAdresse() + "', cp='"
                    +unClient.getCp() + "', ville='"
                    +unClient.getVille() + "' WHERE idClient = "
                    +unClient.getIdclient() + ";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    } catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}


public static ArrayList<Client> selectAllClients (String filtre){
	ArrayList<Client> lesClients = new ArrayList<Client>();
	String requete = "";
	if (filtre.contentEquals("")) {
		requete = "select * from client; ";
	} else {
		requete = "select * from client where nom like '%" + filtre
		+ "%' or email like '%" +filtre
		+ "%' or telephone like '%" +filtre
		+ "%' or acteur like '%" +filtre
		+ "%' or adresse like '%" +filtre
		+ "%' or cp like '%" +filtre
		+ "%' or ville like '%" +filtre + "%' ;";
	}
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		while (desRes.next()) {
			Client unClient = new Client (desRes.getInt("idclient"),
					desRes.getString("nom"), desRes.getString("email"),
					desRes.getString("telephone"), desRes.getString("acteur"),
					desRes.getString("adresse"), desRes.getString("cp"), 
					desRes.getString("ville"));
			lesClients.add(unClient);
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return lesClients;
}


public static Client selectWhereClient (int idClient) {
	Client unClient = null;
	String requete = "select * from client where idclient = "+idClient+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			unClient = new Client(desRes.getInt("idClient"),
					desRes.getString("nom"),
					desRes.getString("email"), 
					desRes.getString("telephone"),
					desRes.getString("acteur"),
					desRes.getString("adresse"),
					desRes.getString("cp"),
					desRes.getString("ville"));
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return unClient;
}

public static Client selectWhereClient (String nom, String email) {
	Client unClient = null;
	String requete = "select * from client where nom = '" +nom+ "' and email = '" +email+"';";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			unClient = new Client(desRes.getInt("idClient"),
					desRes.getString("nom"),
					desRes.getString("email"), 
					desRes.getString("telephone"), 
					desRes.getString("acteur"), 
					desRes.getString("adresse"), 
					desRes.getString("cp"), 
					desRes.getString("ville"));
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return unClient;
}

public static void updateuUser(User unUser) {
	String requete = "update user set nom '"
			+unUser.getNom()+"' , prenom = "
			+unUser.getPrenom()+ "' , email = '"
			+unUser.getEmail()+ "' , role='"
			+unUser.getRole()+ "' , mdp='"
			+unUser.getMdp()+ "' where iduser ="
			+unUser.getIduser()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	
}
/*************** Gestion des entreprises ***************************/
public static void insertEntreprise (Entreprise uneEntreprise) {
    String requete ="insert into entreprise values (null, '"
            +uneEntreprise.getNom()+"','"
            +uneEntreprise.getNum_Siret()+"','"
            +uneEntreprise.getAdresse()+"','"
            +uneEntreprise.getVille()+"','"
            +uneEntreprise.getCp()+"','"
            +uneEntreprise.getContact()+"','"
            +uneEntreprise.getIdclient()+"');";
   // System.out.println(requete);
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}

public static void deleteEntreprise (int idEntreprise) {
    String requete ="delete from entreprise where identreprise = "+idEntreprise+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static void updateEntreprise(Entreprise uneEntreprise) {
    String requete ="update entreprise set nom='" 
            +uneEntreprise.getNom()+"', num_siret ='"
            +uneEntreprise.getNum_Siret()+"', email ='"
            +uneEntreprise.getEmail()+"', adresse ='"
            +uneEntreprise.getAdresse()+"', ville ='"
            +uneEntreprise.getVille()+"', cp ='"
            +uneEntreprise.getCp()+"', contact ='"
             +uneEntreprise.getContact()+"', idclient ='"
            +uneEntreprise.getIdclient()+"' where identreprise = "
            +uneEntreprise.getIdentreprise()+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static ArrayList<Entreprise> selectAllEntreprises (String filtre){
    ArrayList<Entreprise> lesEntreprises = new  ArrayList<Entreprise>(); 
    String requete ="" ; 
    if (filtre.contentEquals("")) {
        requete = "select * from  entreprise ; ";
    }else {
        requete = "select * from  entreprise where nom like '%" + filtre 
                + "%'  or  num_siret like '%"+filtre
                + "%'  or  email like '%"+filtre
                + "%'  or  adresse like '%"+filtre
                + "%'  or  ville like '%"+filtre
                + "%'  or  cp like '%"+filtre
                + "%'  or  contact like '%"+filtre+ "%'";
                
    }
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        while (desRes.next()) {
        	Entreprise uneEntreprise = new Entreprise(desRes.getInt("identreprise"),
                    desRes.getString("nom"), 
                    desRes.getString("num_siret"),desRes.getString("email"),
                    desRes.getString("adresse"),desRes.getString("ville"),
                    desRes.getString("cp"),desRes.getString("contact"),
                    desRes.getInt("idclient"));
                    
            lesEntreprises.add(uneEntreprise);
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return lesEntreprises; 
}
public static Entreprise selectWhereEntreprise (int idEntreprise) {
	Entreprise uneEntreprise = null; 
    String requete = "select * from entreprise where identreprise = " +idEntreprise+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            uneEntreprise = new Entreprise(desRes.getInt("identreprise"),
            		 desRes.getString("nom"), 
                     desRes.getString("num_siret"),desRes.getString("email"),
                     desRes.getString("adresse"),desRes.getString("ville"),
                     desRes.getString("cp"),desRes.getString("contact"),
                     desRes.getInt("idclient"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return uneEntreprise; 
}
public static Entreprise selectWhereEntreprise (String nom,String email, String adresse) {
	Entreprise uneEntreprise = null; 
    String requete = "select * from entreprise where nom='"+nom+"' and email='"+email+"' and adresse='"+adresse+ "';";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            uneEntreprise = new Entreprise(desRes.getInt("identreprise"),
            		 desRes.getString("nom"), 
                     desRes.getString("num_siret"),desRes.getString("email"),
                     desRes.getString("adresse"),desRes.getString("ville"),
                     desRes.getString("cp"),desRes.getString("contact"),
                     desRes.getInt("idclient"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return uneEntreprise; 
}


/**********************GESTION DES CONTRATS********/
public static void insertContrat (Contrat unContrat) {
    String requete ="insert into contrat values (null, '"
            +unContrat.getDebutcontrat()+"','"
            +unContrat.getFincontrat()+"','"
            +unContrat.getMontant()+"','"
            +unContrat.getStatut()+"','"
            +unContrat.getIdclient()+"');";
   System.out.println(requete);
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}

public static void deleteContrat (int idContrat) {
    String requete ="delete from contrat where idcontrat = "+idContrat+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static void updateContrat(Contrat unContrat) {
    String requete ="update contrat set debutcontrat='" 
            +unContrat.getDebutcontrat()+"', fincontrat ='"
            +unContrat.getFincontrat()+"', montant ='"
            +unContrat.getMontant()+"', statut ='"
            +unContrat.getStatut()+"', idclient ='"
            +unContrat.getIdclient()+"' where idcontrat = "
            +unContrat.getIdcontrat()+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static ArrayList<Contrat> selectAllContrats (String filtre){
    ArrayList<Contrat> lesContrats = new  ArrayList<Contrat>(); 
    String requete ="" ; 
    if (filtre.contentEquals("")) {
        requete = "select * from  contrat ; ";
    }else {
        requete = "select * from  contrat where debutcontrat like '%" + filtre 
                + "%'  or  fincontrat like '%"+filtre
                + "%'  or  montant like '%"+filtre
                + "%'  or  statut like '%"+filtre+ "%'";
                
    }
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        while (desRes.next()) {
        	Contrat unContrat = new Contrat(desRes.getInt("idcontrat"),
                    desRes.getString("debutcontrat"), 
                    desRes.getString("fincontrat"),desRes.getString("montant"),
                    desRes.getString("statut"),
                    desRes.getInt("idclient"));
                    
            lesContrats.add(unContrat);
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return lesContrats; 
}
public static Contrat selectWhereContrat (int idContrat) {
	Contrat unContrat = null; 
    String requete = "select * from contrat where idcontrat = " +idContrat+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unContrat = new Contrat(desRes.getInt("idcontrat"),
                    desRes.getString("debutcontrat"), 
                    desRes.getString("fincontrat"),desRes.getString("montant"),
                    desRes.getString("statut"),
                    desRes.getInt("idclient"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unContrat; 
}
public static Contrat selectWhereContrat (String debutcontrat,String fincontrat, String montant) {
	Contrat unContrat = null; 
    String requete = "select * from contrat where debutcontrat='"+debutcontrat+"' and fincontrat='"+fincontrat+"' and montant='"+montant+ "';";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unContrat = new Contrat(desRes.getInt("idcontrat"),
                    desRes.getString("debutcontrat"), 
                    desRes.getString("fincontrat"),desRes.getString("montant"),
                    desRes.getString("statut"),
                    desRes.getInt("idclient"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unContrat; 
}




/**********************GESTION DES TECHNICIENS ********/
public static void insertTechnicien (Technicien unTechnicien) {
	String requete ="insert into technicien values (null, '"
			+unTechnicien.getNom()+"','"
	+unTechnicien.getPrenom()+"','"
    +unTechnicien.getAdresse()+"','"
	+unTechnicien.getCp()+"','"
	+unTechnicien.getVille()+"','"
	+unTechnicien.getTelephone()+"','"
    +unTechnicien.getEmail()+"');";
	
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
       unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	
}
 
public static void deleteTechnicien(int idTechnicien) {
	String requete ="delete from technicien where idtechnicien ="+idTechnicien+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
       unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
}
 
public static void updateTechnicien(Technicien unTechnicien) {
	String requete = "update classe set nom='"
			+unTechnicien.getNom()+"', prenom ='"
			+unTechnicien.getPrenom()+"', adresse ='"
			+unTechnicien.getAdresse()+"', cp ='"
			+unTechnicien.getCp()+"', ville ='"
			+unTechnicien.getVille()+"', telephone ='"
			+unTechnicien.getTelephone()+"', email ='"
			+unTechnicien.getEmail()+"' where idtechnicien ="
			+unTechnicien.getIdtechnicien()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
        unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
  }
public static ArrayList<Technicien> selectAllTechniciens (String filtre){
	ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
	String requete ="";
	if (filtre.contentEquals("")) {
		requete = "select * from technicien ; ";	
	}else {
		requete = "select * from technicien where nom like '%" +filtre
				+"%' or prenom like '%"+filtre
				+"%' or adresse like '%"+filtre
				+"%' or cp like '%"+filtre
				+"%' or ville like '%"+filtre
				+"%' or telephone like '%"+filtre
				+"%' or email like '%"+filtre +"%' ;";
	}
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		while (desRes.next()) {
			Technicien unTechnicien = new Technicien(desRes.getInt("idtechnicien"),
					desRes.getString("nom"),
					desRes.getString("prenom"),desRes.getString("adresse"),
					desRes.getString("cp"),desRes.getString("ville"),
					desRes.getString("telephone"),desRes.getString("email"));
			lesTechniciens.add(unTechnicien);
		}
        unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return lesTechniciens;
}
public static Technicien selectWhereTechnicien (int idTechnicien) {
	Technicien unTechnicien = null;
	String requete = "select * from technicien where idtechnicien ="+idTechnicien+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			 unTechnicien = new Technicien(desRes.getInt("idtechnicien"),
					 desRes.getString("nom"),
						desRes.getString("prenom"),desRes.getString("adresse"),
						desRes.getString("cp"),desRes.getString("ville"),
						desRes.getString("telephone"),desRes.getString("email"));
		}
        unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return unTechnicien;
}
public static Technicien selectWhereTechnicien (String nom, String prenom) {
	Technicien unTechnicien = null;
	String requete = "select * from technicien where nom ='"+nom+"'and prenom ='"+prenom+"';";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			 unTechnicien = new Technicien(desRes.getInt("idtechnicien"),
					 desRes.getString("nom"),
						desRes.getString("prenom"),desRes.getString("adresse"),
						desRes.getString("cp"),desRes.getString("ville"),
						desRes.getString("telephone"),desRes.getString("email"));
			
		}
        unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return unTechnicien;
}

/*************** Gestion des Materiels ***************************/

public static void insertMateriel (Materiel  unMateriel) {
    String requete ="insert into materiel values (null, '"
            +unMateriel.getType()+"','"
            +unMateriel.getNbrejourloues()+"','"
            +unMateriel.getMontant()+"','"
            +unMateriel.getNoticeentretien()+"','"
            +unMateriel.getIdcontrat()+"');";
   // System.out.println(requete);
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}

public static void deleteMateriel (int idMateriel) {
    String requete ="delete from materiel where idmateriel = "+idMateriel+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static void updateMateriel(Materiel unMateriel) {
    String requete ="update materiel set type='" 
            +unMateriel.getType()+"', nbrejourloues ='"
            +unMateriel.getNbrejourloues()+"', montant ='"
            +unMateriel.getMontant()+"', noticeentretien ='"
            +unMateriel.getNoticeentretien()+"', idcontrat ='"
            +unMateriel.getIdcontrat()+"' where idmateriel = "
            +unMateriel.getIdmateriel()+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static ArrayList<Materiel> selectAllMateriels (String filtre){
    ArrayList<Materiel> lesMateriels = new  ArrayList<Materiel>(); 
    String requete ="" ; 
    if (filtre.contentEquals("")) {
        requete = "select * from  materiel ; ";
    }else {
        requete = "select * from  materiel where type like '%" + filtre 
                + "%'  or  nbrejourloues like '%"+filtre
                + "%'  or  montant like '%"+filtre
                + "%'  or  noticeentretien like '%"+filtre+ "%'";
                
    }
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        while (desRes.next()) {
        	Materiel unMateriel = new Materiel(desRes.getInt("idmateriel"),
                    desRes.getString("type"), 
                    desRes.getString("nbrejourloues"),desRes.getString("montant"),
                    desRes.getString("noticeentretien"),
                    desRes.getInt("idcontrat"));
                    
            lesMateriels.add(unMateriel);
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return lesMateriels; 
}
public static Materiel selectWhereMateriel (int idMateriel) {
	Materiel unMateriel = null; 
    String requete = "select * from materiel where idmateriel = " +idMateriel+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unMateriel = new Materiel(desRes.getInt("idmateriel"),
                    desRes.getString("type"), 
                    desRes.getString("nbrejourloues"),desRes.getString("montant"),
                    desRes.getString("noticeentretien"),
                    desRes.getInt("idcontrat"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unMateriel; 
}
public static Materiel selectWhereMateriel (String type, String nbrejourloues, String montant) {
	Materiel unMateriel = null; 
    String requete = "select * from materiel where type='"+type+"' and nbrejourloues='"+nbrejourloues+"' and montant='"+montant+ "';";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unMateriel = new Materiel(desRes.getInt("idmateriel"),
                    desRes.getString("type"), 
                    desRes.getString("nbrejourloues"),desRes.getString("montant"),
                    desRes.getString("noticeentretien"),
                    desRes.getInt("idcontrat"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unMateriel; 
}




 
/*************** Gestion des Interventions ***************************/

public static void insertIntervention (Intervention uneIntervention) {
    String requete ="insert into intervention values (null, '"
            +uneIntervention.getDateinter()+"','"
            +uneIntervention.getTempstotal()+"','"
            +uneIntervention.getCommentaire()+"','"
            +uneIntervention.getMotif()+"','"
            +uneIntervention.getEtatintervention()+"','"
            +uneIntervention.getIdmateriel()+"');";
            
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
 
public static void deleteIntervention (int idIntervention ) {
    String requete ="delete from intervention  where idintervention  = "+idIntervention +";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static void updateIntervention (Intervention  uneIntervention) {
    String requete ="update intervention  set dateinter='"
            +uneIntervention.getDateinter()+"', tempstotal ='"
            +uneIntervention.getTempstotal()+"', commentaire ='"
            +uneIntervention.getCommentaire()+"', motif='"
            +uneIntervention.getMotif()+"', etatintervention ='"
            +uneIntervention.getEtatintervention()+"', idmateriel ='"
            +uneIntervention.getIdmateriel()+"' where idintervention = "
            +uneIntervention.getIdintervention()+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static ArrayList<Intervention> selectAllInterventions (String filtre){
    ArrayList<Intervention> lesInterventions = new  ArrayList<Intervention>();
    String requete ="" ;
    if (filtre.contentEquals("")) {
        requete = "select * from  intervention ; ";
    }else {
        requete = "select * from  Intervention where dateinter like '%" + filtre
                + "%'  or  tempstotal like '%"+filtre
                + "%'  or  commentaire like '%"+filtre
                + "%'  or  motif like '%"+filtre
                + "%'  or  etatintervention like '%"+filtre+ "%'";
                
    }
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        ResultSet desRes = unStat.executeQuery(requete);
        while (desRes.next()) {
        	Intervention uneIntervention = new Intervention(desRes.getInt("idintervention"),
                    desRes.getString("dateinter"),
                    desRes.getString("tempstotal"),desRes.getString("commentaire"),
                    desRes.getString("motif"),desRes.getString("etatintervention"),
                    desRes.getInt("idmateriel"));
                    
            lesInterventions.add(uneIntervention);
        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return lesInterventions;
}
public static Intervention selectWhereIntervention (int idIntervention) {
	Intervention uneIntervention = null;
    String requete = "select * from intervention where idintervention = " +idIntervention+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            uneIntervention = new Intervention(desRes.getInt("idintervention"),
            		desRes.getString("dateinter"),
                    desRes.getString("tempstotal"),desRes.getString("commentaire"),
                    desRes.getString("motif"),desRes.getString("etatintervention"),
                    desRes.getInt("idmateriel"));
        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return uneIntervention;
}
public static Intervention selectWhereIntervention (String dateinter,String tempstotal, String motif) {
	Intervention uneIntervention = null;
    String requete = "select * from intervention where dateinter='"+dateinter+"' and tempstotal='"+tempstotal+"' and motif='"+motif+ "';";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement();
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            uneIntervention = new Intervention (desRes.getInt("idintervention"),
            		desRes.getString("dateinter"),
                    desRes.getString("tempstotal"),desRes.getString("commentaire"),
                    desRes.getString("motif"),desRes.getString("etatintervention"),
                    desRes.getInt("idmateriel"));
        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return uneIntervention;
}
 


}