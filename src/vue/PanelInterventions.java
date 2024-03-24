package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 
import controleur.Controleur;
import controleur.Intervention;
import controleur.Materiel;
import controleur.Tableau;
 
public class PanelInterventions extends PanelPrincipal implements ActionListener {
			private JPanel panelForm = new JPanel ();
			private JTextField txtDateInter = new JTextField();
			private JTextField txtTempsTotal = new JTextField();
			private JTextField txtCommentaire = new JTextField();
			private JTextField txtMotif = new JTextField();
			private JTextField txtEtatIntervention = new JTextField();
			private static JComboBox<String> txtIdmateriel = new JComboBox<String>();
			private JButton btAnnuler = new JButton("Annuler");
			private JButton btEnregistrer = new JButton("Enregistrer");
			
			//table des classes
			private JTable tableInterventions ;
			private int nbInterventions;
			private JLabel lbTitre = new JLabel("Nombre d'interventions : ");
			
			private JScrollPane uneScroll ;
			
			//panel de filtrage
			
			private JPanel panelFiltre = new JPanel();
			private JTextField txtFiltre = new JTextField();
			private JButton btFiltrer = new JButton("Filtrer");
			
			private Tableau unTableau ;
			
			public PanelInterventions() {
			super ();
			//Constructeur du formulaire Classe.
			this.panelForm.setLayout(new GridLayout(7,2));
			this.panelForm.setBackground(new Color (192, 57, 43));
			this.panelForm.setBounds(10, 10, 300, 300);
			this.panelForm.add(new JLabel("DateInter :"));
			this.panelForm.add(this.txtDateInter);
			this.panelForm.add(new JLabel("Tempstotal :"));
			this.panelForm.add(this.txtTempsTotal);
			this.panelForm.add(new JLabel("Commentaire  :"));
			this.panelForm.add(this.txtCommentaire);
			this.panelForm.add(new JLabel("Motif :"));
			this.panelForm.add(this.txtMotif);
			this.panelForm.add(new JLabel("Etat inter :"));
			this.panelForm.add(this.txtEtatIntervention);
			this.panelForm.add(new JLabel("idmateriel :"));
			this.panelForm.add(this.txtIdmateriel);
			this.panelForm.add(this.btAnnuler);
			this.panelForm.add(this.btEnregistrer);
			this.panelForm.setVisible(true);
			this.add(this.panelForm);
			
			
			//construction de la table des classes
			String entetes [] = {"ID", "Dateinter ", " Tempstotal "," Commentaire "," Motif "," EtatInter", " Materiel"};
			this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
			
			this.tableInterventions = new JTable(this.unTableau);
			this.tableInterventions.getTableHeader().setReorderingAllowed(false);
			this.uneScroll = new JScrollPane(this.tableInterventions);
			this.uneScroll.setBounds(350, 80, 460, 230);
			this.add(this.uneScroll);
			
			//construction du panel filtre
			this.panelFiltre.setBounds(400, 30, 400, 30);
			this.panelFiltre.setBackground(new Color (192, 57, 43));
			this.panelFiltre.setLayout(new GridLayout(1, 3));
			this.panelFiltre.add(new JLabel("Filtrer les interventions par :"));
			this.panelFiltre.add(this.txtFiltre);
			this.panelFiltre.add(btFiltrer);
			this.add(this.panelFiltre);
			
			
			//rendre les boutons ecoutables
		    this.btAnnuler.addActionListener(this);
		    this.btEnregistrer.addActionListener(this);
		    this.btFiltrer.addActionListener(this);
		    
		    //placement du titre
		    this.lbTitre.setBounds(200 , 330, 300, 20);
		    this.nbInterventions = this.unTableau.getRowCount();
		    this.lbTitre.setText("Nombre d'interventions: " + this.nbInterventions);
		    this.add(this.lbTitre);
		    
		    
		    //remplire les classes
		    this.remplireCBXClasses ();
		    
		    //suppresion d'une classe
		    this.tableInterventions.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {


					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					
					
				}
				
				public void mouseClicked(MouseEvent e) {
		        int numLigne ;
		        int idIntervention;
		        if (e.getClickCount()>= 2) {
		        	numLigne = tableInterventions.getSelectedRow();
		        	idIntervention = Integer.parseInt(tableInterventions.getValueAt(numLigne, 0).toString());
		        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression de l'intervention", JOptionPane.YES_NO_OPTION);
		        	if (reponse == 0) {
		        		// confirmation de la suppresion dans la BDD
		        		Controleur.deleteIntervention(idIntervention);
		        		//confirmation de la suppression dans l'affichage des classes
		        		unTableau.supprimerLigne(numLigne);
		        		nbInterventions = unTableau.getRowCount();
		        		lbTitre.setText("Nombre d'interventions : " + nbInterventions);
		        	}
		        }
		        else if (e.getClickCount()==1) {
		        	numLigne = tableInterventions.getSelectedRow();
		        	txtDateInter.setText(tableInterventions.getValueAt(numLigne,1).toString());
		        	txtTempsTotal.setText(tableInterventions.getValueAt(numLigne,2).toString());
		        	txtCommentaire.setText(tableInterventions.getValueAt(numLigne,3).toString());
		        	txtMotif.setText(tableInterventions.getValueAt(numLigne,4).toString());
		        	txtEtatIntervention.setText(tableInterventions.getValueAt(numLigne,5).toString());
		        	//txtStatut.setText(tableInterventions.getValueAt(numLigne,6).toString());
		        	//txtIdmateriel.setText(tableInterventions.getValueAt(numLigne,7).toString());
		        	btEnregistrer.setText("Modifier");
		        }
					
				}
			});
 
		  }
			public static void  remplireCBXClasses() {
				txtIdmateriel.removeAllItems();
				ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels("");
				for (Materiel unMateriel : lesMateriels) {
					txtIdmateriel.addItem(unMateriel.getIdmateriel()+"-"
							+ unMateriel.getType());
				}
				
			}
			public Object [][] remplirDonnees (String filtre){
				//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
				ArrayList<Intervention> lesInterventions = Controleur.selectAllInterventions(filtre);
				Object [][] matrice = new Object [lesInterventions.size()][7];
				int i =0;
				for (Intervention uneIntervention: lesInterventions) {
					matrice [i][0] = uneIntervention.getIdintervention();
					matrice [i][1] = uneIntervention.getDateinter();
					matrice [i][2] = uneIntervention.getTempstotal();
					matrice [i][3] = uneIntervention.getCommentaire();
					matrice [i][4] = uneIntervention.getMotif();
					matrice [i][5] = uneIntervention.getEtatintervention();
					matrice [i][6] = uneIntervention.getIdmateriel();
					
					i++;
				}
				return matrice;
			}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == this.btAnnuler) {
				this.txtDateInter.setText("");
				this.txtTempsTotal.setText("");
				this.txtCommentaire.setText("");
				this.txtMotif.setText("");
				this.txtEtatIntervention.setText("");
				
				this.btEnregistrer.setText("Enregistrer");
				}
			else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
				String dateinter = this.txtDateInter.getText();
				String tempstotal = this.txtTempsTotal.getText();
				String commentaire = this.txtCommentaire.getText();
				String motif = this.txtMotif.getText();
				String etatintervention = this.txtEtatIntervention.getText();
				String chaine = this.txtIdmateriel.getSelectedItem().toString();
				String tab [] = chaine.split("-");
				int idmateriel = Integer.parseInt(tab[0]);
				//instanciation d'une classe
				
				Intervention uneIntervention = new Intervention( dateinter, tempstotal , commentaire, motif, etatintervention, idmateriel);
				
				
				//insertion dans la base de données
				Controleur.insertIntervention(uneIntervention);
				JOptionPane.showMessageDialog(this, "Intervention ajouter avec succès");
				
				//recuperation de l'id de l'etudiant  de la BDD
				uneIntervention = Controleur.selectWhereIntervention (dateinter, tempstotal ,motif);
				
				
				//mettre à jour l'affichage
				Object ligne[]= {uneIntervention.getIdintervention(), dateinter, tempstotal , commentaire, motif, etatintervention, idmateriel};
				this.unTableau.ajouterLigne(ligne);
				this.nbInterventions = unTableau.getRowCount();
				this.lbTitre.setText("Nombre d'Intervention : " + this.nbInterventions);
				this.txtDateInter.setText("");
				this.txtTempsTotal.setText("");
				this.txtCommentaire.setText("");
				this.txtMotif.setText("");
				this.txtEtatIntervention.setText("");
				
			}
			else if (e.getSource()== this.btFiltrer) {
				String filtre = this.txtFiltre.getText();
				Object matrice[][] = this.remplirDonnees(filtre);
				this.unTableau.setDonnees(matrice);
			}
			else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
				String dateinter = this.txtDateInter.getText();
				String tempstotal = this.txtTempsTotal.getText();
				String commentaire = this.txtCommentaire.getText();
				String motif = this.txtMotif.getText();
				String etatintervention = this.txtEtatIntervention.getText();
				String chaine = this.txtIdmateriel.getSelectedItem().toString();
				String tab [] = chaine.split("-");
				int idmateriel = Integer.parseInt(tab[0]);
				
				int numLigne = this.tableInterventions.getSelectedRow();
				int idLigne = Integer.parseInt(this.tableInterventions.getValueAt(numLigne, 0).toString());
				//instaciation de la classe
				Intervention uneIntervention = new Intervention(idLigne, dateinter, tempstotal , commentaire, motif, etatintervention, idmateriel);
				//modification de la base de données :
				Controleur.updateIntervention(uneIntervention);
				//modification de la table d'affichage
				Object ligne[] = {idLigne,  dateinter, tempstotal , commentaire, motif, etatintervention, idmateriel};
				this.unTableau.modifierLigne(numLigne, ligne);
				//on vide le formulaire
				this.txtDateInter.setText("");
				this.txtTempsTotal.setText("");
				this.txtCommentaire.setText("");
				this.txtMotif.setText("");
				this.txtEtatIntervention.setText("");
				
				
				this.btEnregistrer.setText("Enregistrer");
				
			}
		 }
		
 
	
	}
 