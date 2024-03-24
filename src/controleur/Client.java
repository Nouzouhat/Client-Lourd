package controleur;

public class Client {
	private int idclient;
	private String nom, email, telephone, acteur, adresse, cp, ville;
	
	public Client(int idclient, String nom, String email, String telephone, String acteur, String adresse, String cp, String ville) 
	{
		this.idclient = idclient;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.acteur = acteur;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
	}
	
	public Client ( String nom, String email, String telephone, String acteur, String adresse, String cp, String ville) 
	{
		this.idclient = 0;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.acteur = acteur;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getActeur() {
		return acteur;
	}

	public void setActeur(String acteur) {
		this.acteur = acteur;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
}