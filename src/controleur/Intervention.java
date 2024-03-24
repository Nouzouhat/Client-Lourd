package controleur;
 
public class Intervention {
		private int idintervention;
		  private String dateinter, tempstotal, commentaire, motif, etatintervention ;
		  private int idmateriel;
		public Intervention(int idintervention, String dateinter, String tempstotal, String commentaire, String motif, String etatintervention,
				int idmateriel) {
			super();
			this.idintervention = idintervention;
			this.dateinter = dateinter;
			this.tempstotal = tempstotal;
			this.commentaire = commentaire;
			this.motif = motif;
			this.etatintervention = etatintervention;
			this.idmateriel = idmateriel;
		}
		  
		public Intervention(  String dateinter, String tempstotal, String commentaire, String motif, String etatintervention,
				int idmateriel) {
			super();
			this.idintervention = 0;
			this.dateinter = dateinter;
			this.tempstotal = tempstotal;
			this.commentaire = commentaire;
			this.motif = motif;
			this.etatintervention = etatintervention;
			this.idmateriel = idmateriel;
		}
 
		public int getIdintervention() {
			return idintervention;
		}
 
		public void setIdintervention(int idintervention) {
			this.idintervention = idintervention;
		}
 
		public String getDateinter() {
			return dateinter;
		}
 
		public void setDateinter(String dateinter) {
			this.dateinter = dateinter;
		}
 
		public String getTempstotal() {
			return tempstotal;
		}
 
		public void setTempstotal(String tempstotal) {
			this.tempstotal = tempstotal;
		}
 
		public String getCommentaire() {
			return commentaire;
		}
 
		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}
 
		public String getMotif() {
			return motif;
		}
 
		public void setMotif(String motif) {
			this.motif = motif;
		}
 
		public String getEtatintervention() {
			return etatintervention;
		}
 
		public void setEtatintervention(String etatintervention) {
			this.etatintervention = etatintervention;
		}
 
		public int getIdmateriel() {
			return idmateriel;
		}
 
		public void setIdmateriel(int idmateriel) {
			this.idmateriel = idmateriel;
		}
		
		
		
 
	}
