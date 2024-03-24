package controleur;
 
public class Contrat {
	private int idcontrat;
	private String debutcontrat, fincontrat, montant, statut;
	private int idclient;
	public Contrat(int idcontrat, String debutcontrat, String fincontrat, String montant, String statut,
			int idclient) {
		this.idcontrat = idcontrat;
		this.debutcontrat = debutcontrat;
		this.fincontrat = fincontrat;
		this.montant = montant;
		this.statut = statut;
		this.idclient = idclient;
	}
	
	public Contrat (String debutcontrat, String fincontrat, String montant, String statut,
			int idclient) {
		this.idcontrat = 0;
		this.debutcontrat = debutcontrat;
		this.fincontrat = fincontrat;
		this.montant = montant;
		this.statut = statut;
		this.idclient = idclient;
	}
 
	public int getIdcontrat() {
		return idcontrat;
	}
 
	public void setIdcontrat(int idcontrat) {
		this.idcontrat = idcontrat;
	}
 
	public String getDebutcontrat() {
		return debutcontrat;
	}
 
	public void setDebutcontrat(String debutcontrat) {
		this.debutcontrat = debutcontrat;
	}
 
	public String getFincontrat() {
		return fincontrat;
	}
 
	public void setFincontrat(String fincontrat) {
		this.fincontrat = fincontrat;
	}
 
	public String getMontant() {
		return montant;
	}
 
	public void setMontant(String montant) {
		this.montant = montant;
	}
 
	public String getStatut() {
		return statut;
	}
 
	public void setStatut(String statut) {
		this.statut = statut;
	}
 
	public int getIdclient() {
		return idclient;
	}
 
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	
	
}