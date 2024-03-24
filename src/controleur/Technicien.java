package controleur;
 
public class Technicien {
	
 
	
		 private int idtechnicien;
		  private String nom, prenom, adresse, cp, ville, telephone, email ;
		public Technicien(int idtechnicien, String nom, String prenom, String adresse, String cp, String ville, String telephone, String email) {
			super();
			this.idtechnicien = idtechnicien;
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.cp = cp;
			this.ville = ville;
			this.telephone = telephone;
			this.email = email;
		}
		public Technicien( String nom, String prenom, String adresse, String cp, String ville, String telephone, String email) {
			super();
			this.idtechnicien = 0;
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.cp = cp;
			this.ville = ville;
			this.telephone = telephone;
			this.email = email;
		}
		public int getIdtechnicien() {
			return idtechnicien;
		}
		public void setIdtechnicien(int idtechnicien) {
			this.idtechnicien = idtechnicien;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
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
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		  
	}