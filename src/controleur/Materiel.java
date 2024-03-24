package controleur;

public class Materiel {
	private int idmateriel;
	private String type, nbrejourloues , montant, noticeentretien;
	private int idcontrat;
	
	public Materiel(int idmateriel, String type, String nbrejourloues, String montant, String noticeentretien,
			int idcontrat) {
		this.idmateriel = idmateriel;
		this.type = type;
		this.nbrejourloues = nbrejourloues;
		this.montant = montant;
		this.noticeentretien = noticeentretien;
		this.idcontrat = idcontrat;
	}
	
	public Materiel(String type, String nbrejourloues, String montant, String noticeentretien, 
			int idcontrat) {
		this.idmateriel = 0;
		this.type = type;
		this.nbrejourloues = nbrejourloues;
		this.montant = montant;
		this.noticeentretien = noticeentretien;
		this.idcontrat = idcontrat;
	}

	public int getIdmateriel() {
		return idmateriel;
	}

	public void setIdmateriel(int idmateriel) {
		this.idmateriel = idmateriel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNbrejourloues() {
		return nbrejourloues;
	}

	public void setNbrejourloues(String nbrejourloues) {
		this.nbrejourloues = nbrejourloues;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getNoticeentretien() {
		return noticeentretien;
	}

	public void setNoticeentretien(String noticeentretien) {
		this.noticeentretien = noticeentretien;
	}

	public int getIdcontrat() {
		return idcontrat;
	}

	public void setIdcontrat(int idcontrat) {
		this.idcontrat = idcontrat;
	}
	
}