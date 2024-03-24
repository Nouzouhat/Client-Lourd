package controleur;
 
public class Entreprise {
		private int identreprise;
		private String  nom, num_Siret, email, adresse, ville, cp, contact;
		private int idclient;
		
		public Entreprise (int identreprise, String nom, String num_Siret, String email, String adresse , String ville , String cp, String contact, int idclient) {
			super();
			this.identreprise = identreprise;
			this.nom = nom;
			this.num_Siret = num_Siret;
			this.email = email;
			this.adresse =  adresse;
			this.ville = ville;
			this.cp = cp;
			this.contact = contact;
			this.idclient = idclient;
		}
		
		public Entreprise ( String nom, String num_Siret, String email, String adresse , String ville , String cp, String contact, int idclient) {
			this.idclient = 0;
			this.nom = nom;
			this.num_Siret = num_Siret;
			this.email = email;
			this.adresse = adresse;
			this.ville = ville;
			this.cp = cp;
			this.contact = contact;
			this.idclient = idclient;
		}

		public int getIdentreprise() {
			return identreprise;
		}

		public void setIdentreprise(int identreprise) {
			this.identreprise = identreprise;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getNum_Siret() {
			return num_Siret;
		}

		public void setNum_Siret(String num_Siret) {
			this.num_Siret = num_Siret;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public int getIdclient() {
			return idclient;
		}

		public void setIdclient(int idclient) {
			this.idclient = idclient;
		}
		
		  
}